package 测试;

import com.aug3.storage.passage.client.PassageClient;
import com.aug3.storage.passage.client.action.DeleteObjectAction;
import com.aug3.storage.passage.client.action.PutObjectAction;
import com.aug3.storage.passage.thrift.SObject;
import com.aug3.storage.passage.thrift.Storage;
import com.aug3.storage.passage.thrift.Strategy;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import org.junit.Test;

import java.io.*;
import java.util.Date;

/**
 * Created by junmeng.xu on 2016/12/24.
 */
public class Main3 {

    public static final String PASSAGE_SERVER_HOST_S3_CN = "192.168.250.206";

    private static PassageClient passageClient = new PassageClient(PASSAGE_SERVER_HOST_S3_CN, 8888);

    public static void main(String[] args) {

        String path="d:\\announce\\fund\\";
        File file=new File(path);
        File[] tempList = file.listFiles();
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isDirectory()) {  //文件夹
                File file1 = tempList[i];
                File[] files = file1.listFiles();
                for (int j = 0; j < files.length; j++){
                    File f = files[j];
                    String path1 = f.getPath();
                    String[] split = path1.split("\\\\");
                    int length = split.length;
                    String fnExt = split[length-1];
                    int length1 = fnExt.length();
                    String fn = fnExt.substring(0, length1 - 4);
                    String url = split[length - 4] + "/" + split[length - 3] + "/" + split[length - 2] + "/" + split[length-1];
                    BufferedInputStream in = null;
                    ByteArrayOutputStream out = null;
                    try {
                        in = new BufferedInputStream(new FileInputStream(f));
                        out = new ByteArrayOutputStream();
                        byte[] temp = new byte[1024];
                        int size = 0;
                        while ((size = in.read(temp)) != -1) {
                            out.write(temp, 0, size);
                        }
                        byte[] content = out.toByteArray();
                        test(url, fn, content);


                    } catch (Exception e){
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
            }
        }

    }

    public static void test(String url, String fn, byte[] data){

        //由本地文件决定
        String fileAddress = "/" + url + fn + ".pdf";

        //通过  fn  查找到 mongo 中的记录，把相应的ext改为 pdf
        Mongo mg = new Mongo("122.144.134.95", 27017);
        DB db = mg.getDB("news");
        DBCollection coll = db.getCollection("announcement_fund");
        BasicDBObject query = new BasicDBObject();
        query.put("file.fn", fn);
        coll.update(query, new BasicDBObject("$set", new BasicDBObject("file.ext", "pdf").append("stat", 2).append("upt", new Date())));


        Strategy strategy = new Strategy();
        strategy.setBucketName("cn.com.chinascope.jd");
        strategy.setSType(Storage.S3CN);

        //把 相应的 txt 文件删除
        DeleteObjectAction deleteObjectAction = new DeleteObjectAction();
        deleteObjectAction.setKey(fileAddress);
        deleteObjectAction.setStrategy(strategy);
        passageClient.perform(deleteObjectAction);

        //把 相应的  pdf 文件上传
        PutObjectAction putObjectAction = new PutObjectAction();
        SObject sObject = new SObject();
        sObject.setData(data);
        sObject.setKey(fileAddress);
        putObjectAction.setsObj(sObject);
        putObjectAction.setStrategy(strategy);
        passageClient.perform(putObjectAction);

    }

    @Test
    public void test1(){
        String fn = "75a963c299f309261e22cc22dfa68f88";
        //通过  fn  查找到 mongo 中的记录，把相应的ext改为 pdf
        Mongo mg = new Mongo("192.168.250.200", 27017);
        DB db = mg.getDB("news");
        DBCollection coll = db.getCollection("announcement_fund");
        BasicDBObject query = new BasicDBObject();
        query.put("file.fn", fn);
        coll.update(query, new BasicDBObject("$set", new BasicDBObject("file.ext", "pdf")));
    }

    @Test
    public void test2(){
        String fileAddress = "/test/announce/cn/20161026/fff0afa8dea9d130dec49028c81fca12.pdf";
        //把 相应的 txt 文件删除
        Strategy strategy = new Strategy();
        strategy.setBucketName("cn.com.chinascope.dfs");
        strategy.setSType(Storage.S3CN);
        DeleteObjectAction deleteObjectAction = new DeleteObjectAction();
        deleteObjectAction.setKey(fileAddress);
        deleteObjectAction.setStrategy(strategy);
        Object perform = passageClient.perform(deleteObjectAction);
        System.out.println(perform);
    }

    @Test
    public void test3(){
        String fileAddress = "/test/announce/cn/20161026/fff0afa8dea9d130dec49028c81fca12.pdf";
        String str = "asdfasdf";
        byte[] data = str.getBytes();
        //把 相应的  pdf 文件上传
        Strategy strategy = new Strategy();
        strategy.setBucketName("cn.com.chinascope.dfs");
        strategy.setSType(Storage.S3CN);
        PutObjectAction putObjectAction = new PutObjectAction();
        SObject sObject = new SObject();
        sObject.setData(data);
        sObject.setKey(fileAddress);
        putObjectAction.setsObj(sObject);
        putObjectAction.setStrategy(strategy);
        passageClient.perform(putObjectAction);
    }

    @Test
    public void test4(){
        File f = new File("d:\\asdf324dsfg34.pdf");
        BufferedInputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            in = new BufferedInputStream(new FileInputStream(f));
            out = new ByteArrayOutputStream();
            byte[] temp = new byte[1024];
            int size = 0;
            while ((size = in.read(temp)) != -1) {
                out.write(temp, 0, size);
            }
            byte[] content = out.toByteArray();




            //把 相应的  pdf 文件上传
            Strategy strategy = new Strategy();
            strategy.setBucketName("cn.com.chinascope.dfs");
            strategy.setSType(Storage.S3CN);
            PutObjectAction putObjectAction = new PutObjectAction();
            SObject sObject = new SObject();
            sObject.setData(content);
            sObject.setKey("/test/junmeng/fff0afa8dea9d130dec49028c81fca12.pdf");
            putObjectAction.setsObj(sObject);
            putObjectAction.setStrategy(strategy);
            passageClient.perform(putObjectAction);

        } catch (Exception e){
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

    @Test
    public void test5(){
        String path="d:\\announce\\fund\\";
        File file=new File(path);
        File[] tempList = file.listFiles();
        System.out.println("该目录下对象个数："+tempList.length);
        for (int i = 0; i < tempList.length; i++) {
//            if (tempList[i].isFile()) {
//                System.out.println("文     件："+tempList[i]);
//            }
            if (tempList[i].isDirectory()) {  //文件夹
                File file1 = tempList[i];
                File[] files = file1.listFiles();
                for (int j = 0; j < files.length; j++){
                    File f = files[j];
                    String path1 = f.getPath();
                    String[] split = path1.split("\\\\");
                    int length = split.length;
                    String fnExt = split[length-1];
                    int length1 = fnExt.length();
                    String fn = fnExt.substring(0, length1 - 4);
                    String url = split[length - 4] + "/" + split[length - 3] + "/" + split[length - 2] + "/" + split[length-1];
                    BufferedInputStream in = null;
                    ByteArrayOutputStream out = null;
                    try {
                        in = new BufferedInputStream(new FileInputStream(f));
                        out = new ByteArrayOutputStream();
                        byte[] temp = new byte[1024];
                        int size = 0;
                        while ((size = in.read(temp)) != -1) {
                            out.write(temp, 0, size);
                        }
                        byte[] content = out.toByteArray();



                    } catch (Exception e){
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
            }
        }
    }

    @Test
    public void test6(){
        //删除掉本地的文件
        String urlFile = "d:\\asdf324dsfg34.pdf";
        File f2 = new File(urlFile);
        f2.delete();
    }

}
