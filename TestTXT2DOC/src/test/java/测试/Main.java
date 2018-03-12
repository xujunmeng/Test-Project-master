package 测试;

import com.aug3.storage.passage.client.PassageClient;
import com.aug3.storage.passage.client.action.GetObjectAction;
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
import com.mongodb.Mongo;
import org.apache.any23.encoding.TikaEncodingDetector;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by junmeng.xu on 2016/12/23.
 */
public class Main {

    public static final String PASSAGE_SERVER_HOST_S3_CN = "192.168.100.34";

    private static PassageClient passageClient = new PassageClient(PASSAGE_SERVER_HOST_S3_CN, 8883);

    static ExecutorService executor = Executors.newFixedThreadPool(10);

    private static Mongo mg = new Mongo("122.144.134.95", 27017);

    public static void main(String[] args) throws Exception {

        List<String> strings = getLIst();

        dealFile(strings);

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

    public static List<String> getLIst() {

        List<String> result = Lists.newArrayList();
        String path="d:\\announce\\fund\\";
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

    public static Integer  downLoadFromUrl2(List<String> urls){

        for (String url : urls) {

            int length = url.length();
            String strstr = url.substring(2, length - 4);
            String[] split = strstr.split("\\\\");
            String str2 = "/" + split[1] + "/" + split[2] + "/" + split[3] + "/" + split[4];

            String pdfUrl = str2 + ".pdf";

            String txtUrl = str2 + ".txt";

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
        return urls.size();





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
