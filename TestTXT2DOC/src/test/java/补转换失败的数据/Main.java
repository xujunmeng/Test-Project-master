package 补转换失败的数据;

import com.aug3.storage.mongoclient.MongoUtils;
import com.aug3.storage.passage.client.PassageClient;
import com.aug3.storage.passage.client.action.DeleteObjectAction;
import com.aug3.storage.passage.client.action.GetObjectAction;
import com.aug3.storage.passage.client.action.PutObjectAction;
import com.aug3.storage.passage.thrift.SObject;
import com.aug3.storage.passage.thrift.Storage;
import com.aug3.storage.passage.thrift.Strategy;
import com.google.common.collect.Lists;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.mongodb.*;
import org.apache.any23.encoding.TikaEncodingDetector;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by junmeng.xu on 2016/12/24.
 */
public class Main {

    public static final String PASSAGE_SERVER_HOST_S3_CN = "192.168.100.34";

    private static PassageClient passageClient = new PassageClient(PASSAGE_SERVER_HOST_S3_CN, 8883);

    private static Mongo mg = new Mongo("122.144.134.95", 27017);

    static ExecutorService executor = Executors.newFixedThreadPool(15);

    public static void main(String[] args) {


        String path = "d:\\announce\\error\\error.log";
        File file = new File(path);
        try {
            InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file));
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            List<String> sids = Lists.newArrayList();
            int i = 0;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                String sid = lineTxt;
                sids.add(sid);
            }

            List<String> urlNotExts = test2(sids);

            dealFile(urlNotExts);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static List<String> test2(List<String> list) {
        BasicDBObject query = new BasicDBObject();
        query.put("sid", new BasicDBObject("$in", list));
        BasicDBObject field = new BasicDBObject();
        field.put("file.url", 1);
        field.put("file.md5", 1);
        DB db = mg.getDB("news");
        DBCollection coll = db.getCollection("announcement_fund");
        DBCursor cur = coll.find(query);
        List<String> urlNotExts = Lists.newArrayList();
        DBObject dto = null;
        while (cur.hasNext()) {
            dto = cur.next();
            String url = MongoUtils.getStringByFieldNameChain(dto, "file.url");
            if (StringUtils.isBlank(url)) continue;
            String md5 = MongoUtils.getStringByFieldNameChain(dto, "file.md5");
            if (StringUtils.isBlank(md5)) continue;
            String urlNotExt = url + md5;
            urlNotExts.add(urlNotExt);
        }
        return urlNotExts;
    }


    public static void dealFile(List<String> list) {

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
                return downLoadFromUrl2(subSids);
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

    public static Integer  downLoadFromUrl2(List<String> urls){

        for (String url : urls) {

            String pdfUrl = url + ".pdf";

            String txtUrl = url + ".txt";

            //文件保存位置
            Strategy strategy = new Strategy();
            strategy.setBucketName("cn.com.chinascope.dfs");
            strategy.setSType(Storage.S3CN);
            GetObjectAction getObjectAction = new GetObjectAction();
            getObjectAction.setKey(txtUrl);
            getObjectAction.setStrategy(strategy);

            try {
                SObject object = (SObject) passageClient.perform(getObjectAction);

                byte[] getData = object.getData();


                InputStream sbs = new ByteArrayInputStream(getData);

                Charset charset1 = guessCharset(sbs);

                String s = charset1.toString();

                String str = new String(getData, s);

                InputStream inputStream = new ByteArrayInputStream(str.getBytes(s));

                makePDF(inputStream, pdfUrl, s);
            } catch (Exception e){
                File file = new File("/testAnnounce/error22/error.log");
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
                    wrFA.write(pdfUrl + "\r\n");
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
            }
        }
        return urls.size();





    }

    public static void test(String url, String fn, byte[] data){

        //由本地文件决定
        String fileAddress = "/" + url;

        //通过  fn  查找到 mongo 中的记录，把相应的ext改为 pdf

        DB db = mg.getDB("news");
        DBCollection coll = db.getCollection("announcement_fund");
        BasicDBObject query = new BasicDBObject();
        query.put("file.fn", fn);
        coll.update(query, new BasicDBObject("$set", new BasicDBObject("file.ext", "pdf").append("stat", 2).append("upt", new Date())));


        Strategy strategy = new Strategy();
        strategy.setBucketName("cn.com.chinascope.jd");
        strategy.setSType(Storage.S3CN);

        //把 相应的 txt 文件删除
        String delFile = fileAddress;
        int length = delFile.length();
        String bb = delFile.substring(0, length - 4);
        String cc = bb + ".txt";
        DeleteObjectAction deleteObjectAction = new DeleteObjectAction();
        deleteObjectAction.setKey(cc);
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












    public static Charset guessCharset(InputStream is) throws IOException {
        return Charset.forName(new TikaEncodingDetector().guessEncoding(is));
    }

    public static void makePDF(InputStream inputStream, String savePath, String charset) {

        try {

            //首先创建一个字体

            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);

            Font FontChinese = new Font(bfChinese);

            String line;

            Document document;

            document = new Document(PageSize.A4);

            InputStreamReader read = new InputStreamReader(inputStream, charset);//考虑到编码格式
            BufferedReader in = new BufferedReader(read);

            File file = new File("/testAnnounce" + savePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            PdfWriter.getInstance(document, new FileOutputStream("/testAnnounce" + savePath));

            document.open();

            while ((line = in.readLine()) != null){
                document.add(new Paragraph(line, FontChinese));
            }

            document.close();

        }catch(Exception e) {

            System.err.println(e.getMessage());

        }

    }

}
