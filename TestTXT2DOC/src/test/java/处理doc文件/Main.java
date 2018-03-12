package 处理doc文件;

import com.aug3.storage.passage.client.PassageClient;
import com.google.common.collect.Lists;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.mongodb.Mongo;
import org.apache.any23.encoding.TikaEncodingDetector;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by junmeng.xu on 2016/12/25.
 */
public class Main {

    public static final String PASSAGE_SERVER_HOST_S3_CN = "192.168.100.34";

    private static PassageClient passageClient = new PassageClient(PASSAGE_SERVER_HOST_S3_CN, 8883);

    static ExecutorService executor = Executors.newFixedThreadPool(15);

    private static Mongo mg = new Mongo("122.144.134.95", 27017);

    public static void main(String[] args) {

        getLIst();
    }

    public static List<String> getLIst() {
        ActiveXComponent app = null;
        Dispatch doc = null;
        app = new ActiveXComponent("Word.Application");
        app.setProperty("Visible", false);
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

                    try {

                        String docFileName = path1;  //doc文件路径

                        String pdfUrl = path1.substring(0, path1.length() - 4) + "pdf";  //docx
                        //String pdfUrl = path1.substring(0, path1.length() - 3) + "pdf";    //doc

                        String pdfFileName = pdfUrl;  //生成的pdf文件路径


                        Dispatch docs = app.getProperty("Documents").toDispatch();
                        doc = Dispatch.call(docs, "Open", docFileName, false, true).toDispatch();
                        Dispatch.call(doc, "SaveAs", pdfFileName, 17);
                        Dispatch.call(doc, "Close", false);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }



                }
            }
        }
        //如果没有这句话,winword.exe进程将不会关闭
        ComThread.Release();
        Dispatch.call(doc, "Close", false);
        if (app != null) app.invoke("Quit", 0);

        return result;
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











    public static void wordToPDF(List<String> urlNotExts){

        ActiveXComponent app = null;
        Dispatch doc = null;

        try {
            app = new ActiveXComponent("Word.Application");
            app.setProperty("Visible", false);

            String docFileName = "E:\\resource\\announce\\temp\\590c8754cb753831d76376f32d6991e6.docx";  //doc文件路径
            String pdfFileName = "E:\\resource\\announce\\temp\\590c8754cb753831d76376f32d6991e6.pdf";  //生成的pdf文件路径


            Dispatch docs = app.getProperty("Documents").toDispatch();
            doc = Dispatch.call(docs, "Open", docFileName, false, true).toDispatch();
            Dispatch.call(doc, "SaveAs", pdfFileName, 17);
            Dispatch.call(doc, "Close", false);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Dispatch.call(doc, "Close", false);
            if (app != null)
                app.invoke("Quit", 0);
        }
        //如果没有这句话,winword.exe进程将不会关闭
        ComThread.Release();

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
