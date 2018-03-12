package 测试二;

import com.aug3.storage.passage.client.PassageClient;
import com.aug3.storage.passage.client.action.PutObjectAction;
import com.aug3.storage.passage.thrift.SObject;
import com.aug3.storage.passage.thrift.Storage;
import com.aug3.storage.passage.thrift.Strategy;
import com.google.common.collect.Lists;
import com.mongodb.Mongo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by junmeng.xu on 2016/12/24.
 */
public class DealLocalFundPdf {

    public static final String PASSAGE_SERVER_HOST_S3_CN = "192.168.100.34";

    private static PassageClient passageClient = new PassageClient(PASSAGE_SERVER_HOST_S3_CN, 8883);

    static ExecutorService executor = Executors.newFixedThreadPool(15);

    private static Mongo mg = new Mongo("122.144.134.95", 27017);

    public static void main(String[] args) {

        List<String> list = getLIst();


        dealFile(list);

    }

    public static List<String> getLIst() {

        List<String> result = Lists.newArrayList();
        String path="d:\\testAnnounce\\announce\\fund\\";
        File file=new File(path);
        File[] tempList = file.listFiles();
        System.out.println("该目录下对象个数："+tempList.length);
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isDirectory()) {  //文件夹
                File file1 = tempList[i];
                File[] files = file1.listFiles();
                for (int j = 0; j < files.length; j++) {
                    File f = files[j];
                    String path1 = f.getPath();
                    result.add(path1);
                }
            }
        }
        return result;
    }


    public static void dealFile(List<String> list) {

        //向list存放数据，对这个数据进行分页
        int size = list.size();
        int head = 0;
        int times = size / 100 + (size % 100 == 0 ? 0 : 1);
        List<Callable<Integer>> calls = new ArrayList<>();
        for (int i = 0; i < times; i++) {
            int end = head + 100;

            if(end > size) end = size;

            List<String> subSids = list.subList(head, end);
            Callable<Integer> callable = () -> {
                //处理subSids的程序
                return dealTest(subSids);
            };
            calls.add(callable);
            head += 100;
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

    private static Integer dealTest(List<String> list){

        for (String path1 : list) {
            String keykey = path1;
            int length2 = keykey.length();
            String key1 = keykey.substring(4, length2-4) + ".pdf";
            String[] split1 = key1.split("\\\\");
            String key = "/" + split1[1] + "/" + split1[2] + "/" + split1[3] + "/" + split1[4];

            BufferedInputStream in = null;
            ByteArrayOutputStream out = null;
            try {
                in = new BufferedInputStream(new FileInputStream(path1));
                out = new ByteArrayOutputStream();
                byte[] temp = new byte[1024];
                int size = 0;
                while ((size = in.read(temp)) != -1) {
                    out.write(temp, 0, size);
                }
                byte[] content = out.toByteArray();


                Strategy strategy = new Strategy();
                strategy.setBucketName("cn.com.chinascope.jd");
                strategy.setSType(Storage.S3CN);
                PutObjectAction putObjectAction = new PutObjectAction();
                SObject sObject = new SObject();
                sObject.setData(content);
                sObject.setKey(key);
                putObjectAction.setsObj(sObject);
                putObjectAction.setStrategy(strategy);
                passageClient.perform(putObjectAction);

            } catch (Exception e){
                File file = new File("/testAnnounce/announce/uploaderror/error.log");
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                    try {
                        file.createNewFile();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                FileWriter wrFA = null;
                try {
                    wrFA = new FileWriter(file, true);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                try {
                    wrFA.write(path1 + "\r\n");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                if (wrFA != null) {
                    try {
                        wrFA.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return list.size();
    }


    public static void test(String url, String fn, byte[] data){

        //由本地文件决定
        int length2 = url.length();
        String fileAddress = url.substring(0, length2-4) + ".pdf";
//        DB db = mg.getDB("news");
//        DBCollection coll = db.getCollection("announcement_fund");
//        BasicDBObject query = new BasicDBObject();
//        query.put("file.fn", fn);
//        coll.update(query, new BasicDBObject("$set", new BasicDBObject("file.ext", "pdf").append("stat", 2).append("upt", new Date())));


        Strategy strategy = new Strategy();
        strategy.setBucketName("cn.com.chinascope.jd");
        strategy.setSType(Storage.S3CN);

        //把 相应的 txt 文件删除
//        String delFile = fileAddress;
//        int length = delFile.length();
//        String bb = delFile.substring(0, length - 4);
//        String cc = bb + ".txt";
//        DeleteObjectAction deleteObjectAction = new DeleteObjectAction();
//        deleteObjectAction.setKey(cc);
//        deleteObjectAction.setStrategy(strategy);
//        passageClient.perform(deleteObjectAction);

        //把 相应的  pdf 文件上传
        PutObjectAction putObjectAction = new PutObjectAction();
        SObject sObject = new SObject();
        sObject.setData(data);
        sObject.setKey(fileAddress);
        putObjectAction.setsObj(sObject);
        putObjectAction.setStrategy(strategy);
        passageClient.perform(putObjectAction);

    }


}
