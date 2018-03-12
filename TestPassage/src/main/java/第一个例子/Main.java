package 第一个例子;

import com.aug3.storage.mongoclient.MongoUtils;
import com.aug3.storage.passage.client.PassageClient;
import com.aug3.storage.passage.client.action.GetObjectAction;
import com.aug3.storage.passage.thrift.SObject;
import com.aug3.storage.passage.thrift.Storage;
import com.aug3.storage.passage.thrift.Strategy;
import com.google.common.collect.Lists;
import com.itextpdf.text.pdf.PdfReader;
import com.mongodb.*;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by junmeng.xu on 2017/1/17.
 */
public class Main {

    private static PassageClient passageClient = new PassageClient("192.168.100.34", 8883);
    static ExecutorService executor = Executors.newFixedThreadPool(20);

    /**
     * 修改  announcement_fund 表中 file.pn 为 0 的情况
     * @throws Exception
     */
    @Test
    public void testsdf() throws Exception{
        Strategy strategy = new Strategy();
        strategy.setBucketName("cn.com.chinascope.jd");
        strategy.setSType(Storage.S3CN);
        Mongo mg = new Mongo("192.168.250.200", 27017);
        DB db = mg.getDB("news");
        DBCollection coll = db.getCollection("announcement_fund");

        Mongo mg2 = new Mongo("192.168.250.200", 27017);
        DB db2 = mg2.getDB("news");
        DBCollection coll2 = db2.getCollection("announcement_fund");
        BasicDBObject query = new BasicDBObject();
        query.put("file.pn", 0);
        DBCursor cur = coll.find(query);
        DBObject dto = null;
        List<String> list = Lists.newArrayList();
        while (cur.hasNext()) {
            dto = cur.next();
            String sid = MongoUtils.getStringByFieldNameChain(dto, "sid");
            String title = MongoUtils.getStringByFieldNameChain(dto, "title");
            String ext = MongoUtils.getStringByFieldNameChain(dto, "file.ext");
            String fn = MongoUtils.getStringByFieldNameChain(dto, "file.fn");
            String url = MongoUtils.getStringByFieldNameChain(dto, "file.url");
            String fileAddress = url + fn + "." + ext;
            if (StringUtils.equalsIgnoreCase(ext, "pdf")) {
                list.add(sid + "&" + title + "&" + fileAddress);
            }
        }
        //向list存放数据，对这个数据进行分页
        int size = list.size();
        int head = 0;
        int times = size / 900 + (size % 900 == 0 ? 0 : 1);
        List<Callable<Integer>> calls = new ArrayList<>();
        for (int i = 0; i < times; i++) {
            int end = head + 900;

            if(end > size) end = size;

            List<String> subSids = list.subList(head, end);
            Callable<Integer> callable = () -> {
                //处理subSids的程序
                dealdeal(subSids, coll2, strategy);
                return null;
            };
            calls.add(callable);
            head += 900;
        }
        int total = 0;
        try {
            List<Future<Integer>> futures = executor.invokeAll(calls);
            for (Future<Integer> future : futures) {
                total += future.get();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void dealdeal(List<String> str, DBCollection coll2, Strategy strategy){
        for (String s : str) {
            String[] split = s.split("&");
            String sid = split[0];
            String title = split[1];
            String fileAddress = split[2];

            GetObjectAction getAction = new GetObjectAction();
            getAction.setKey(fileAddress);
            getAction.setStrategy(strategy);
            SObject object = (SObject) passageClient.perform(getAction);
            byte[] data = object.getData();

            int pn = 0;
            try {
                PdfReader pdfReader = new PdfReader(data);
                pn = pdfReader.getNumberOfPages();
            } catch (Exception e){
                e.printStackTrace();
            }
            coll2.update(new BasicDBObject("sid", sid).append("title", title), new BasicDBObject("$set", new BasicDBObject("file.pn", pn)));
        }

    }

}
