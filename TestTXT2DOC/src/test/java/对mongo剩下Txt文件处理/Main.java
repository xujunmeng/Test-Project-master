package 对mongo剩下Txt文件处理;

import com.aug3.storage.mongoclient.MongoUtils;
import com.aug3.storage.passage.client.PassageClient;
import com.aug3.storage.passage.client.action.GetObjectAction;
import com.aug3.storage.passage.client.action.ListKeysAction;
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

    static ExecutorService executor = Executors.newFixedThreadPool(15);

    static ExecutorService executor2 = Executors.newFixedThreadPool(15);

    private static Mongo mg = new Mongo("122.144.134.95", 27017);

    public static void main(String[] args) {

        List<String> urlPathNotExts = Lists.newArrayList();
        for(int i = 0; i < 100000; i=i+5000){
            List<String> list = test2(i);
            urlPathNotExts.addAll(list);
        }


        List<String> listNeedToDFSLoad = dealFile2(urlPathNotExts);

        dealFile(listNeedToDFSLoad);



    }

    public static void dealFile(List<String> urlPathNotExts) {

        //向list存放数据，对这个数据进行分页
        int size = urlPathNotExts.size();
        int head = 0;
        int times = size / 900 + (size % 900 == 0 ? 0 : 1);
        List<Callable<Integer>> calls = new ArrayList<>();
        for (int i = 0; i < times; i++) {
            int end = head + 900;

            if(end > size) end = size;

            List<String> subUrlPathNotExts = urlPathNotExts.subList(head, end);
            Callable<Integer> callable = () -> {
                //处理subSids的程序
                return downLoad(subUrlPathNotExts);
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

    public static List<String> dealFile2(List<String> urlPathNotExts) {

        //向list存放数据，对这个数据进行分页
        int size = urlPathNotExts.size();
        int head = 0;
        int times = size / 900 + (size % 900 == 0 ? 0 : 1);
        List<Callable<List<String>>> calls = new ArrayList<>();
        for (int i = 0; i < times; i++) {
            int end = head + 900;

            if(end > size) end = size;

            List<String> subUrlPathNotExts = urlPathNotExts.subList(head, end);
            Callable<List<String>> callable = () -> {
                //处理subSids的程序
                return downLoadFromUrl2(subUrlPathNotExts);
            };
            calls.add(callable);
            head += 900;
        }
        List<String> result = Lists.newArrayList();
        try {
            List<Future<List<String>>> futures = executor2.invokeAll(calls);
            for (Future<List<String>> future : futures) {
                List<String> list = future.get();
                result.addAll(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static Integer  downLoad(List<String> urlPathNotExts){

        for (String urlPathNotExt : urlPathNotExts) {


            String pdfUrl = urlPathNotExt + ".pdf";

            String txtUrl = urlPathNotExt + ".txt";

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
                File file = new File("/testAnnounce/error/error.log");
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
        return urlPathNotExts.size();
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

    public static List<String> test2(Integer skip){
        try {
            DB db = mg.getDB("news");
            DBCollection coll = db.getCollection("announcement_fund");
            BasicDBObject query = new BasicDBObject();
            ArrayList<String> list = Lists.newArrayList("txt", "TXT");
            query.put("file.ext", new BasicDBObject("$in", list));
            BasicDBObject field = new BasicDBObject();
            field.put("file.fn", 1);
            field.put("file.url", 1);
            BasicDBObject sort = new BasicDBObject();
            sort.put("pdt", -1);
            long count = coll.count(query);
            System.out.println(count);
            List<String> result = Lists.newArrayList();
            DBCursor cur = coll.find(query, field).sort(sort).skip(skip).limit(5000);
            DBObject dto = null;
            while(cur.hasNext()){
                dto = cur.next();
                String url = MongoUtils.getStringByFieldNameChain(dto, "file.url");
                if(StringUtils.isBlank(url)) continue;
                String fn = MongoUtils.getStringByFieldNameChain(dto, "file.fn");
                if(StringUtils.isBlank(fn)) continue;
                String urlPathNotExt = url + fn;
                result.add(urlPathNotExt);
            }
            return result;
        } catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static List<String>  downLoadFromUrl2(List<String> urlPathNotExts){

        List<String> result = Lists.newArrayList();
        for (String urlPathNotExt : urlPathNotExts) {
            String pdfUrl = urlPathNotExt + ".pdf";
            Strategy strategy = new Strategy();
            strategy.setBucketName("cn.com.chinascope.jd");
            strategy.setSType(Storage.S3CN);
            ListKeysAction listKeysAction = new ListKeysAction();
            listKeysAction.setKey(pdfUrl);
            listKeysAction.setStrategy(strategy);
            List<String> list = (List<String>) passageClient.perform(listKeysAction);
            if (!list.contains(getUrl(pdfUrl))) {
                result.add(urlPathNotExt);
            }
        }
        return result;
    }

    private static String getUrl(String url){
        if (url.startsWith("/")) {
            url = url.substring(1);
        }
        return url;
    }
}
