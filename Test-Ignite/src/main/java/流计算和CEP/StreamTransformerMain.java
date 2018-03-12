package 流计算和CEP;

import java.util.List;
import java.util.Random;

import javax.cache.processor.MutableEntry;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteDataStreamer;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.stream.StreamTransformer;

/**
@author junmeng.xu
@date  2016年6月12日下午3:19:13
 */
public class StreamTransformerMain {
    /** Random number generator. */
    private static final Random RAND = new Random();

    /** Range within which to generate numbers.   产生数字的范围。  */   
    private static final int RANGE = 1000;

    public static void main(String[] args) throws Exception {
        // Mark this cluster member as client.   标记此群集成员为客户端。
        Ignition.setClientMode(true);

        try (Ignite ignite = Ignition.start("D:/javaWorkLianxi/Test-Ignite/src/main/java/example-ignite.xml")) {
            if (!hasServerNodes(ignite))
                return;

            CacheConfiguration<Integer, Long> cfg = new CacheConfiguration<>("randomNumbers");

            // Index key and value.  索引键和值。
            cfg.setIndexedTypes(Integer.class, Long.class);

            // Auto-close cache at the end of the example.  自我关闭的缓存  在这个例子的结尾
            try (IgniteCache<Integer, Long> stmCache = ignite.getOrCreateCache(cfg)) {
                try (IgniteDataStreamer<Integer, Long> stmr = ignite.dataStreamer(stmCache.getName())) {
                    // Allow data updates.   允许数据更新。
                    stmr.allowOverwrite(true);

                    // Configure data transformation to count random numbers added to the stream.  配置数据转换以将随机数添加到流中。
                    stmr.receiver(new StreamTransformer<Integer, Long>() {
                        @Override public Object process(MutableEntry<Integer, Long> e, Object... args) {
                            // Get current count.
                            Long val = e.getValue();

                            // Increment count by 1.
                            e.setValue(val == null ? 1L : val + 1);

                            return null;
                        }
                    });

                    // Stream 10 million of random numbers into the streamer cache.   1000万的随机数进入缓存。
                    for (int i = 1; i <= 10_000_000; i++) {
                        stmr.addData(RAND.nextInt(RANGE), 1L);

                        if (i % 500_000 == 0)
                            System.out.println("Number of tuples streamed into Ignite: " + i);    //这么多的元组进入到ignite
                    }
                }

                // Query top 10 most popular numbers every.
                SqlFieldsQuery top10Qry = new SqlFieldsQuery("select _key, _val from Long order by _val desc limit 100");

                // Execute queries.
                List<List<?>> top10 = stmCache.query(top10Qry).getAll();

                System.out.println("Top 10 most popular numbers:");

                // Print top 10 words.
                printQueryResults(top10);
            }
            finally {
                // Distributed cache could be removed from cluster only by #destroyCache() call.
                ignite.destroyCache(cfg.getName());
            }
        }
    }
    public static void printQueryResults(List<?> res) {
        if (res == null || res.isEmpty())
            System.out.println("Query result set is empty.");
        else {
            for (Object row : res) {
                if (row instanceof List) {
                    System.out.print("(");

                    List<?> l = (List)row;

                    for (int i = 0; i < l.size(); i++) {
                        Object o = l.get(i);

                        if (o instanceof Double || o instanceof Float)
                            System.out.printf("%.2f", o);
                        else
                            System.out.print(l.get(i));

                        if (i + 1 != l.size())
                            System.out.print(',');
                    }

                    System.out.println(')');
                }
                else
                    System.out.println("  " + row);
            }
        }
    }
    public static boolean hasServerNodes(Ignite ignite) {
        if (ignite.cluster().forServers().nodes().isEmpty()) {
            System.err.println("Server nodes not found (start data nodes with ExampleNodeStartup class)");

            return false;
        }

        return true;
    }
}
