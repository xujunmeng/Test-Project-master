package 第三个例子对HTML页面解析;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by junmeng.xu on 2016/12/27.
 */
public class Main {

    public static void main(String[] args) throws Exception{

        Document doc = Jsoup.connect("http://www.cninfo.com.cn/finalpage/2010-12-28/cninfo_oefund58837472.html")
                .timeout(3000)           // 设置连接超时时间
                .get();
        String zbt = doc.select(".zbt").text();
        String zcc = doc.select(".zcc").text();
        String zw = doc.select(".zw").text();
        String result = zbt + "\n" + zcc + "\n" + zw;
        System.out.println(result);
    }

    @Test
    public void test2() throws IOException {

        Document doc = Jsoup.connect("http://www.cninfo.com.cn/finalpage/2002-01-11/szse_disclosure_jjzqgg_jjgg_list535043.html")
                .timeout(3000)           // 设置连接超时时间
                .get();
        Elements table = doc.select("table");
        if (!table.isEmpty()){
            String text = table.get(0).text();
            System.out.println(text);
        }
    }

    @Test
    public void test3() throws IOException {

        Document doc = Jsoup.connect("http://www.cninfo.com.cn/finalpage/2008-01-11/cninfo_jjzq_jjgg_kfsjjgg36620933.html")
                .timeout(3000)           // 设置连接超时时间
                .get();
        Elements table = doc.select("table");
        if (!table.isEmpty()){
            String text = table.get(0).text();
            System.out.println(text);
        }
    }

}
