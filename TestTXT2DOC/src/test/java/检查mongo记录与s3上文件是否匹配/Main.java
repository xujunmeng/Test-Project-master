package 检查mongo记录与s3上文件是否匹配;

import com.aug3.storage.mongoclient.MongoUtils;
import com.aug3.storage.passage.client.PassageClient;
import com.aug3.storage.passage.client.action.ListKeysAction;
import com.aug3.storage.passage.thrift.Storage;
import com.aug3.storage.passage.thrift.Strategy;
import com.google.common.collect.Lists;
import com.mongodb.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by junmeng.xu on 2016/12/27.
 */
public class Main {

    static ExecutorService executor = Executors.newFixedThreadPool(20);
    private static PassageClient passageClient = new PassageClient("192.168.100.34", 8883);
    private static Mongo mg = new Mongo("122.144.134.95", 27017);
    /**
     * 检查mongo记录与s3上文件是否匹配
     */
    //创建索引
    @Test
    public void testBuild(){
        Mongo mg = new Mongo("122.144.134.95", 27017);
        DB db = mg.getDB("news");
        DBCollection coll = db.getCollection("announcement_fund");
        BasicDBObject keys = new BasicDBObject();
        keys.put("file.url", 1);
        keys.put("file.fn", 1);
        keys.put("sid", 1);
        keys.put("pdt", 1);
        coll.createIndex(keys);
    }
    @Test
    public void testasdfasd() throws Exception {

        List<String> urlFnNotExts = Lists.newArrayList();

        DB db = mg.getDB("news");
        DBCollection coll = db.getCollection("announcement_fund");
        DBCursor cursor = coll.find(new BasicDBObject(), new BasicDBObject("file.url", 1).append("file.fn", 1).append("sid", 1)).sort(new BasicDBObject("pdt", -1)).skip(35000).limit(5000);
        List<String> list = Lists.newArrayList();
        DBObject dto = null;
        while(cursor.hasNext()) {
            dto = cursor.next();
            String sid = MongoUtils.getStringByFieldNameChain(dto, "sid");
            if (StringUtils.isBlank(sid)) continue;
            String url = MongoUtils.getStringByFieldNameChain(dto, "file.url");
            if (StringUtils.isBlank(url)) continue;
            String fn = MongoUtils.getStringByFieldNameChain(dto, "file.fn");
            if (StringUtils.isBlank(fn)) continue;
            String filePathNotExt = url + fn;
            list.add(sid + "&&" + filePathNotExt);
        }
        for (String filePath : list) {
            File file = new File("/testAnnounceFund/needConfirmationFile/paper.log");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileWriter wrFA = new FileWriter(file, true);
            wrFA.write(filePath + "\r\n");
            if (wrFA != null) {
                wrFA.close();
            }
        }




        List<Callable<List<String>>> calls = new ArrayList<>();
        int size = urlFnNotExts.size();
        int head = 0;
        int times = size / 900 + (size % 900 == 0 ? 0 : 1);
        for (int i = 0; i < times; i++) {
            int end = head + 900;
            if (end > size) end = size;
            List<String> urlFnNotExt = urlFnNotExts.subList(head, end);

            Callable<List<String>> callable = () -> {
                //处理subSids的程序
                return dealLostFile(urlFnNotExt);
            };
            calls.add(callable);

            head += 900;
        }
        try {
            List<String> allResult = Lists.newArrayList();
            List<Future<List<String>>> futures = executor.invokeAll(calls);
            for (Future<List<String>> future : futures) {
                List<String> strings = future.get();
                allResult.addAll(strings);
            }
            for (String filePath : allResult) {
                File file = new File("/testAnnounceFund/lostFilePath/paper.log");
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }
                FileWriter wrFA = new FileWriter(file, true);
                wrFA.write(filePath + "\r\n");
                if (wrFA != null) {
                    wrFA.close();
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<String> dealLostFile(List<String> urlFnNotExt) {
        List<String> result = Lists.newArrayList();
        for (String urlFnNotExtObj : urlFnNotExt) {
            String url = urlFnNotExtObj.split("&&")[1];
            String fileAddress = url + ".pdf";
            Strategy strategy = new Strategy();
            strategy.setBucketName("cn.com.chinascope.jd");
            strategy.setSType(Storage.S3CN);
            ListKeysAction listKeysAction = new ListKeysAction();
            listKeysAction.setKey(fileAddress);
            listKeysAction.setStrategy(strategy);
            List<String> list = (List<String>)passageClient.perform(listKeysAction);
            if(CollectionUtils.isEmpty(list)){
                result.add(urlFnNotExtObj);
            }
            if(!list.contains(getUrl(fileAddress))){
                result.add(urlFnNotExtObj);
            }
        }
        return result;
    }


    private String getUrl(String url){
        if (url.startsWith("/")) {
            url = url.substring(1);
        }
        return url;
    }

}
