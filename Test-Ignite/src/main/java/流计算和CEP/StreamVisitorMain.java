package 流计算和CEP;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteDataStreamer;
import org.apache.ignite.Ignition;
import org.apache.ignite.binary.BinaryObject;
import org.apache.ignite.binary.BinaryObjectBuilder;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.cache.query.annotations.QuerySqlField;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.stream.StreamVisitor;

/**
 * @author junmeng.xu
 * @date 2016年6月12日下午4:09:07
 */

/**
 * This examples demonstrates the stream visitor which allows to customize the processing
 * of the streamed data on the server side. 
 * 这个例子演示了流的访问者，允许在服务器端自定义的处理数据流
 * Instead of populating the cache for which the
 * streamer is created, we will calculate aggregated data on the fly and save results in
 * another cache.
 * 而不是填充到被创建的缓存中，我们将计算汇总的数据对于正在跑的，在另一个缓存保存结果。
 * <p>
 * Remote nodes should always be started with special configuration file which
 * enables P2P class loading: {@code 'ignite.{sh|bat} examples/config/example-ignite.xml'}.
 * <p>
 * Alternatively you can run {@link ExampleNodeStartup} in another JVM which will
 * start node with {@code examples/config/example-ignite.xml} configuration.
 */
public class StreamVisitorMain {
	/** Random number generator. */
	private static final Random RAND = new Random();

	/** The list of instruments.  工具清单 */
	private static final String[] INSTRUMENTS = { "IBM", "GOOG", "MSFT", "GE",
			"EBAY", "YHOO", "ORCL", "CSCO", "AMZN", "RHT" };

	/** The list of initial instrument prices. */
	private static final double[] INITIAL_PRICES = { 194.9, 893.49, 34.21,
			23.24, 57.93, 45.03, 44.41, 28.44, 378.49, 69.50 };

	/** Caches' names. */
	private static final String INSTRUMENTS_CACHE_NAME = "instCache";
	private static final String MARKET_TICKS_CACHE_NAME = "marketTicks";

	public static void main(String[] args) throws Exception {
		// Mark this cluster member as client.
		Ignition.setClientMode(true);

		try (Ignite ignite = Ignition
				.start("D:/javaWorkLianxi/Test-Ignite/src/main/java/example-ignite.xml")) {
			if (!hasServerNodes(ignite))
				return;

			// Financial instrument cache configuration.  金融的工具配置的缓存。
			CacheConfiguration<String, Instrument> instCfg = new CacheConfiguration<>(
					INSTRUMENTS_CACHE_NAME);

			// Index key and value for querying financial instruments. 金融工具查询的索引键和值。
			// Note that Instrument class has @QuerySqlField annotation for
			// secondary field indexing.
			instCfg.setIndexedTypes(String.class, Instrument.class);

			// Auto-close caches at the end of the example.
			try (IgniteCache<String, Double> mktCache = ignite
					.getOrCreateCache(MARKET_TICKS_CACHE_NAME); // Default
																// config.
					IgniteCache<String, Instrument> instCache = ignite
							.getOrCreateCache(instCfg)) {
				try (IgniteDataStreamer<String, Double> mktStmr = ignite
						.dataStreamer(mktCache.getName())) {
					// To achieve proper indexing we should use fully-qualified
					// name 
					// 要实现适当的索引，我们应该使用完全合格的名称
					// of the class as a type name when binary object is
					// created.
					// 当二进制对象创建时，类为一个类型名称。
					final String instTypeName = Instrument.class.getName();

					// Note that we receive market data, but do not populate
					// 'mktCache' (it remains empty).
					// 值得注意的是，我们收到的市场数据，但不填充的‘mktCache’(他仍然是空的)。
					// Instead we update the instruments in the 'instCache'.
					// 而我们更新的金融工具是在‘instCache’
					// Since both, 'instCache' and 'mktCache' use the same key,
					// updates are collocated.
					// 因为，‘instaCache’和‘mktCache’使用相同的密钥，更新配置
					mktStmr.receiver(new StreamVisitor<String, Double>() {
						@Override
						public void apply(IgniteCache<String, Double> cache,
								Map.Entry<String, Double> e) {
							String symbol = e.getKey();
							Double tick = e.getValue();

							IgniteCache<String, BinaryObject> binInstCache = ignite
									.cache(INSTRUMENTS_CACHE_NAME)
									.withKeepBinary();

							BinaryObject inst = binInstCache.get(symbol);

							BinaryObjectBuilder instBuilder;

							if (inst == null) {
								instBuilder = ignite.binary().builder(
										instTypeName);

								// Constructor logic.
								instBuilder.setField("symbol", symbol);
							} else
								instBuilder = inst.toBuilder();

							// Instrument.update() logic.
							Double open = instBuilder.<Double> getField("open");

							if (open == null || open == 0)
								instBuilder.setField("open", tick);

							instBuilder.setField("latest", tick);

							// Build instrument object.
							inst = instBuilder.build();

							binInstCache.put(symbol, inst);
						}
					});

					// Stream 10 million market data ticks into the system.
					for (int i = 1; i <= 10_000_000; i++) {
						int idx = RAND.nextInt(INSTRUMENTS.length);

						// Use gaussian distribution to ensure that
						// numbers closer to 0 have higher probability.
						double price = round2(INITIAL_PRICES[idx]
								+ RAND.nextGaussian());

						mktStmr.addData(INSTRUMENTS[idx], price);

						if (i % 500_000 == 0)
							System.out
									.println("Number of tuples streamed into Ignite: "
											+ i);
					}
				}

				// Select top 3 best performing instruments.
				SqlFieldsQuery top3qry = new SqlFieldsQuery(
						"select symbol, (latest - open) from Instrument order by (latest - open) desc limit 3");

				
				// Execute queries.
				List<List<?>> top3 = instCache.query(top3qry).getAll();

				System.out.println("Top performing financial instruments: ");

				// Print top 10 words.
				printQueryResults(top3);
				
				System.out.println("==============");
				
				SqlFieldsQuery inQuery = new SqlFieldsQuery("select symbol, (latest - open) from Instrument where symbol in ('GOOG','IBM','YHOO')");
				
				List<List<?>> list = instCache.query(inQuery).getAll();
				
				printQueryResults(list);
				
			} finally {
				// Distributed cache could be removed from cluster only by
				// #destroyCache() call.
				ignite.destroyCache(INSTRUMENTS_CACHE_NAME);
				ignite.destroyCache(MARKET_TICKS_CACHE_NAME);
			}
		}
	}

	/**
	 * Rounds double value to two significant signs.
	 *
	 * @param val
	 *            value to be rounded.
	 * @return rounded double value.
	 */
	private static double round2(double val) {
		return Math.floor(100 * val + 0.5) / 100;
	}

	/**
	 * Financial instrument.  金融工具。
	 */
	public static class Instrument implements Serializable {
		/** Instrument symbol.   工具符号 */
		@QuerySqlField(index = true)
		private final String symbol;

		/** Open price. */
		@QuerySqlField(index = true)
		private double open;

		/** Close price. */
		@QuerySqlField(index = true)
		private double latest;

		/**
		 * @param symbol
		 *            Symbol.
		 */
		public Instrument(String symbol) {
			this.symbol = symbol;
		}

		/**
		 * Updates this instrument based on the latest market tick price.
		 *
		 * @param price
		 *            Latest price.
		 */
		public void update(double price) {
			if (open == 0)
				open = price;

			this.latest = price;
		}
	}

	public static void printQueryResults(List<?> res) {
		if (res == null || res.isEmpty())
			System.out.println("Query result set is empty.");
		else {
			for (Object row : res) {
				if (row instanceof List) {
					System.out.print("(");

					List<?> l = (List) row;

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
				} else
					System.out.println("  " + row);
			}
		}
	}

	public static boolean hasServerNodes(Ignite ignite) {
		if (ignite.cluster().forServers().nodes().isEmpty()) {
			System.err
					.println("Server nodes not found (start data nodes with ExampleNodeStartup class)");

			return false;
		}

		return true;
	}
}
