package 测试三;

import com.aug3.storage.mongoclient.MongoUtils;
import com.aug3.storage.passage.client.PassageClient;
import com.aug3.storage.passage.client.action.GetObjectAction;
import com.aug3.storage.passage.thrift.SObject;
import com.aug3.storage.passage.thrift.Storage;
import com.aug3.storage.passage.thrift.Strategy;
import com.aug3.storage.passage.util.ConfigManager;
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

/**
 * Created by junmeng.xu on 2016/12/24.
 */
public class Main {

    public static final String PASSAGE_SERVER_HOST_S3_CN = ConfigManager.getConfigProperties().getProperty("passage.server", "122.144.134.96");

    private static PassageClient passageClient = new PassageClient(PASSAGE_SERVER_HOST_S3_CN, 8888);

    public static void main(String[] args) throws Exception {

        Integer skip = 0;

        test2(skip);

    }

    public static void test2(Integer skip) throws  Exception{
        Mongo mg = new Mongo("122.144.134.95", 27017);
        DB db = mg.getDB("news");
        DBCollection coll = db.getCollection("announcement_fund");
        BasicDBObject query = new BasicDBObject();
        ArrayList<String> list = Lists.newArrayList("txt", "TXT");
        query.put("file.ext", new BasicDBObject("$in", list));
        BasicDBObject field = new BasicDBObject();
        field.put("sid", 1);
        field.put("pub", 1);
        field.put("file.fn", 1);
        field.put("file.url", 1);
        field.put("file.md5", 1);
        BasicDBObject sort = new BasicDBObject();
        sort.put("pdt", -1);
        long count = coll.count(query);
        System.out.println(count);
        DBCursor cur = coll.find(query, field).sort(sort).skip(skip).limit(10000);
        DBObject dto = null;
        int i = 0;
        while(cur.hasNext()){
            long begin = System.currentTimeMillis();
            dto = cur.next();
            String sid = MongoUtils.getStringByFieldNameChain(dto, "sid");
            if(StringUtils.isBlank(sid)) continue;
            String pub = MongoUtils.getStringByFieldNameChain(dto, "pub");
            if(StringUtils.isBlank(pub)) continue;
            String url = MongoUtils.getStringByFieldNameChain(dto, "file.url");
            if(StringUtils.isBlank(url)) continue;
            String md5 = MongoUtils.getStringByFieldNameChain(dto, "file.md5");
            if(StringUtils.isBlank(md5)) continue;
            String downUrl = "http://www.cninfo.com.cn/finalpage/"+pub+"/cninfo"+sid+".js";
            System.out.println("开始处理,这是第 : " + i + " 条记录 。文件路径 : " + url+md5);
            downLoadFromUrl2(downUrl, "txt", url, sid, coll, md5);
            System.out.println("处理结束,这是第 : " + i + " 条记录 。文件路径 : " + url+md5 + " 花费时间 : " + (System.currentTimeMillis() - begin));
            i++;
        }


    }

    public static void  downLoadFromUrl2(String downUrl, String fileType, String savePath, String sid, DBCollection coll, String md5) throws IOException {

        String fileAddress = savePath + md5 + "." + fileType;

        //文件保存位置
        Strategy strategy = new Strategy();
        strategy.setBucketName("cn.com.chinascope.dfs");
        strategy.setSType(Storage.S3CN);
        GetObjectAction getObjectAction = new GetObjectAction();
        getObjectAction.setKey(fileAddress);
        getObjectAction.setStrategy(strategy);

        try {
            SObject object = (SObject) passageClient.perform(getObjectAction);

            byte[] getData = object.getData();


            InputStream sbs = new ByteArrayInputStream(getData);

            Charset charset1 = guessCharset(sbs);

            String s = charset1.toString();

            String str = new String(getData, s);

            InputStream inputStream = new ByteArrayInputStream(str.getBytes());

            makePDF(inputStream, savePath, md5, s);
        } catch (Exception e){
            File file = new File("/app/log/announceFund/error.log");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileWriter wrFA = new FileWriter(file, true);
            wrFA.write(sid + "\r\n");
            if (wrFA != null) {
                wrFA.close();
            }
            e.printStackTrace();
        }



    }

    public static Charset guessCharset(InputStream is) throws IOException {
        return Charset.forName(new TikaEncodingDetector().guessEncoding(is));
    }

    public static void makePDF(InputStream inputStream, String savePath, String md5, String charset) {

        try {

            //首先创建一个字体

            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);

            Font FontChinese = new Font(bfChinese);

            String line;

            Document document;

            document = new Document(PageSize.A4);

            InputStreamReader read = new InputStreamReader(inputStream, charset);//考虑到编码格式
            BufferedReader in = new BufferedReader(read);

            File file = new File(savePath+md5+".pdf");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            PdfWriter.getInstance(document, new FileOutputStream(savePath + md5 + ".pdf"));

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
