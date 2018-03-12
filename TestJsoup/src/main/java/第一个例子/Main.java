package 第一个例子;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Map;
import java.util.Set;

/**
 * Created by junmeng.xu on 2016/11/7.
 */
public class Main {

    public static void main(String[] args) throws Exception {

        Document doc = Jsoup.connect("http://www.cninfo.com.cn/finalpage/2016-11-07/cninfo1202817435.js")
                .timeout(3000)           // 设置连接超时时间
                .get();

        Element body = doc.body();



    }

    @Test
    public void tset33(){
        try {
            downLoadFromUrl2("http://www.cninfo.com.cn/finalpage/2015-03-04/1200668549.PDF",
                    "b0472cdc51c1f127e72219c97a573df0", "pdf", "e:/resource/announce/diaodiao/bushuju/");

            downLoadFromUrl2("http://www.cninfo.com.cn/finalpage/2015-03-26/1200745432.PDF",
                    "f3f30a06bf7a0b5532bfa31136a0a0f2", "pdf", "e:/resource/announce/diaodiao/bushuju/");

            downLoadFromUrl2("http://www.cninfo.com.cn/finalpage/2015-04-24/1200903651.PDF",
                    "12f4613b361c544d2ccbcf91753bfb71", "pdf", "e:/resource/announce/diaodiao/bushuju/");

            downLoadFromUrl2("http://www.cninfo.com.cn/finalpage/2015-05-08/1200980035.PDF",
                    "89e1496f6dcc678d8e5db13c0e631ea", "pdf", "e:/resource/announce/diaodiao/bushuju/");

            downLoadFromUrl2("http://www.cninfo.com.cn/finalpage/2015-05-26/1201065580.PDF",
                    "d4db7d40883875cb8cabe45f3f9071e2", "pdf", "e:/resource/announce/diaodiao/bushuju/");

            downLoadFromUrl2("http://www.cninfo.com.cn/finalpage/2015-06-26/1201196480.PDF",
                    "e79957a403519ef19251c3a692e2125e", "pdf", "e:/resource/announce/diaodiao/bushuju/");

            downLoadFromUrl2("http://www.cninfo.com.cn/finalpage/2015-08-11/1201421912.PDF",
                    "9567248e23c8fee925a9f02adcd937b8", "pdf", "e:/resource/announce/diaodiao/bushuju/");

            downLoadFromUrl2("http://www.cninfo.com.cn/finalpage/2015-08-19/1201458527.PDF",
                    "242f7587d952c21a5e63f06b712fe61e", "pdf", "e:/resource/announce/diaodiao/bushuju/");

            downLoadFromUrl2("http://www.cninfo.com.cn/finalpage/2015-10-09/1201672120.PDF",
                    "701815cbd5069b0fc2045a8b36bd1fd8", "pdf", "e:/resource/announce/diaodiao/bushuju/");

            downLoadFromUrl2("http://www.cninfo.com.cn/finalpage/2015-11-12/1201765976.PDF",
                    "f5f565e0ea3e3ec325d1ec62cbbf8ae0", "pdf", "e:/resource/announce/diaodiao/bushuju/");

            downLoadFromUrl2("http://www.cninfo.com.cn/finalpage/2015-10-23/1201716939.PDF",
                    "1b5c00fae861b550c964c87f38341d31", "pdf", "e:/resource/announce/diaodiao/bushuju/");

            downLoadFromUrl2("http://www.cninfo.com.cn/finalpage/2016-12-30/1202973235.PDF",
                    "5636022f349c31480e3d02462cd9c19b", "pdf", "e:/resource/announce/diaodiao/bushuju/");

            downLoadFromUrl2("http://www.cninfo.com.cn/finalpage/2017-01-05/1202993079.PDF",
                    "63fd4ee1ce7db5423cb79843b22ae655", "pdf", "e:/resource/announce/diaodiao/bushuju/");

            downLoadFromUrl2("http://www.cninfo.com.cn/finalpage/2017-01-03/1202984715.PDF",
                    "1149ad987e0445bab5b1170510086567", "pdf", "e:/resource/announce/diaodiao/bushuju/");

            downLoadFromUrl2("http://www.cninfo.com.cn/finalpage/2017-01-03/1202984642.PDF",
                    "f46b19ca0b62cc391c5324457336d863", "pdf", "e:/resource/announce/diaodiao/bushuju/");

            downLoadFromUrl2("http://www.cninfo.com.cn/finalpage/2015-03-05/1200670023.PDF",
                    "b3cda7c5c56b9f56d54f885b98a91128", "pdf", "e:/resource/announce/diaodiao/bushuju/");

        }catch (Exception e){
            e.printStackTrace();
        }
    }



    @Test
    public void test22(){
        try {
            downLoadFromUrl("http://www.cninfo.com.cn/finalpage/2014-10-30/cninfo1200355099.js",
                    "asd232ASASDFASDFDFASDFdf", "txt", "e:/resource/announce/diaodiao/");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void downLoadFromUrl2(String urlStr,String fileName, String fileType, String savePath){
        try {
            URL website = new URL(urlStr);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream(savePath+fileName+"."+fileType);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        } catch (Exception e){
            e.printStackTrace();
        }

    }


    /**
     * 从网络Url中下载文件
     * @param urlStr
     * @param fileName
     * @param savePath
     * @throws IOException
     */
    public static void  downLoadFromUrl(String urlStr,String fileName, String fileType, String savePath) throws IOException{
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //设置超时间为3秒
        conn.setConnectTimeout(5*1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //得到输入流
        InputStream inputStream = conn.getInputStream();

        String string = IOUtils.toString(inputStream, "GBK");

        String s = parserTXT2(string);

        //获取自己数组
        byte[] getData = s.getBytes("UTF-8");

        //文件保存位置
        File file = new File(savePath+fileName+"."+fileType);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if(fos!=null){
            fos.close();
        }
        if(inputStream!=null){
            inputStream.close();
        }


        System.out.println("info:"+url+" download success");

    }
    /**
     * 从输入流中获取字节数组
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[2048];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

    /**
     * 对str进行解析 2
     * @param string
     */
    public static String parserTXT2(String string){
        javax.script.ScriptEngineManager sem = new javax.script.ScriptEngineManager();
        ScriptEngine engine = sem.getEngineByName("JavaScript");
        String contentEnd = "";
        try {
            engine.eval(string);
            if(engine instanceof Invocable){
                Map<Object, Map<Object, Object>> affiches = (Map)engine.get("affiches");
                Map<Object, Object> map = affiches.get(0);
                Set<Map.Entry<Object, Object>> entries = map.entrySet();
                for(Map.Entry<Object, Object> entity : entries){
                    String key = (String)entity.getKey();
                    String value = (String)entity.getValue();
                    if("Title".equals(key) || "Time".equals(key) || "Zw".equals(key)){
                        String result = "";
                        if("Zw".equals(key)){
                            String[] split = value.split("<br>");
                            for(String ss : split){
                                result = result + ss + "\r\n";
                            }
                            contentEnd = contentEnd + result + "\r\n";
                        } else {
                            contentEnd = contentEnd + value + "\r\n";
                        }
                    }
                }
            }
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        System.out.println(contentEnd);
        return contentEnd;
    }

    /**
     * 对str进行解析
     * @param string
     */
    public static String parserTXT(String string){
        String[] split = string.split("affiches=");
        String s = split[1];

        String substring = s.substring(1, s.length()-3);

        JsonParser parser=new JsonParser();  //创建JSON解析器
        JsonElement jsonElement = parser.parse(substring);
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        String title = asJsonObject.get("Title").getAsString();
        String time = asJsonObject.get("Time").getAsString();
        String zw = asJsonObject.get("Zw").getAsString();
        String[] split1 = zw.split("<br>");
        String contentEnd = "";
        for (String ss : split1){
            contentEnd = contentEnd + ss + "\r\n";
        }
        String result = title + "\r\n" + time + "\r\n" + contentEnd;
        return  result;
    }


    @Test
    public void tesst22(){
        String str = "var affiches=[{\"Title\":\"双林股份自2016年11月7日开市起停牌\",\"Time\":\"2016-11-07 09:09:00\",\"Zw\":\"    宁波双林汽车部件股份有限公司拟披露重大事项，根据本所《创业板股票上市规则》的有关规定，经公司申请，公司股票（证券简称：双林股份，证券代码：300100）于2016年11月7日开市起停牌，待公司通过指定媒体披露相关公告后复牌，敬请投资者密切关注。<br>    <br>    <br>    深圳证券交易所   <br>    2016年11月7日   \",\"website\":\"cninfo\",\"webpath\":\"/home/apache/htdocs/finalpage/2016-11-07/cninfo1202817435.js\",\"webTxtID\":\"1202817435\"}];";

        String str2 = "var affiches=[{\"Title\":\"关于常州快克锡焊股份有限公司人民币普通股股票上市交易的公告\",\"Time\":\"2016-11-07 09:08:00\",\"Zw\":\"    上证公告（股票）〔2016〕68号<br>    <br>    常州快克锡焊股份有限公司A股股票将在本所交易市场上市交易。该公司A股股本为9200万股，其中2300万股于2016年11月8日起上市交易。证券简称为\\\"快克股份\\\"，证券代码为\\\"603203\\\"。<br>    <br>    特此公告。<br>    <br>    <br>    上海证券交易所<br>    <br>    二〇一六年十一月七日\",\"website\":\"cninfo\",\"webpath\":\"/home/apache/htdocs/finalpage/2016-11-07/cninfo1202817441.js\",\"webTxtID\":\"1202817441\"}];\n";

//        String sss = "{\"cat\":\"it\",\"dog\":\"it\"}";

        String[] split = str.split("=");
        String s = split[1];
//        String substring1 = s.substring(0, s.length());

        String substring = s.substring(1, s.length()-2);

        JsonParser parser=new JsonParser();  //创建JSON解析器
        JsonElement jsonElement = parser.parse(substring);
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        String title = asJsonObject.get("Title").getAsString();
        String time = asJsonObject.get("Time").getAsString();
        String zw = asJsonObject.get("Zw").getAsString();
        String[] split1 = zw.split("<br>");
        String contentEnd = "";
        for (String ss : split1){
            contentEnd = contentEnd + ss + "\r\n";
        }
        System.out.println(title + "\r\n" + time + "\r\n" + contentEnd);
    }

    @Test
    public void tes2225(){

        String str = "TXT";
        boolean txt = str.equalsIgnoreCase("txt");
        System.out.println(txt);

        String str2 = "http://www.cninfo.com.cn/unit/cninfo.html?s=finalpage%2F2016-06-08%2Fcninfo1202362704.js";
        String str3 = "http://www.cninfo.com.cn/finalpage/2016-04-22/1202232241.PDF";

        dealStr(str2);

    }

    public void dealStr(String string){
        String substring = string.substring(string.length() - 2, string.length());
        if("js".equals(substring)){
            System.out.println(substring);
        }




    }

}
