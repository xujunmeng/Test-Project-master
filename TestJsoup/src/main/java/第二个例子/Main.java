package 第二个例子;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by junmeng.xu on 2016/12/1.
 */
public class Main {

    private static int m = 0;

    @Test
    public void test88() throws Exception {

        // 打开文件
        WritableWorkbook book = Workbook.createWorkbook(new File(
                "E:/A股公告分析bak.xls"));
        // 生成名为“第一页”的工作表，参数0表示这是第一页
        WritableSheet sheet = book.createSheet("第一页", 0);

        int n = 0;
        //1231
        //http://www.shjjw.gov.cn/fg/wygl/xq/?id=30001363   每个小区的详细情况   id为小区code
        for (int i = 1 ; i <= 1231 ; i++){
            String urlStr = "http://www.shjjw.gov.cn/fg/wygl/wyxq/s?pn=" + i + "&ps=10&name=&ad=&co=&qx=";
            downLoadFromUrl2(urlStr, sheet);
        }

        book.write();
        book.close();

    }


    public static void downLoadFromUrl2(String urlStr, WritableSheet sheet) throws Exception {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //设置超时间为3秒
        conn.setConnectTimeout(5*1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //得到输入流
        InputStream inputStream = conn.getInputStream();

        String string = IOUtils.toString(inputStream, "GBK");

        parserTXT3(string, sheet);


        if(inputStream!=null){
            inputStream.close();
        }


        System.out.println("info:" + url + " download success");


    }

    public static String parserTXT3(String string, WritableSheet sheet) throws Exception {

        JSONObject obj = new JSONObject(string.toString());

        JSONObject data = obj.getJSONObject("data");

        JSONArray list = data.getJSONArray("list");

        for (int i = 0; i < list.length(); i++){
            JSONObject temp = list.getJSONObject(i);
            String keyid = temp.getString("keyid");
            String rownum = temp.getString("rownum");
            String rn = temp.getString("rn");
            String concretaddr  = temp.getString("concretaddr");
            String tel = temp.getString("tel");
            String projectname = temp.getString("projectname");
            String distname = temp.getString("distname");
            String companyname = temp.getString("companyname");
            String areamanager = temp.getString("areamanager");
            writerExcel(keyid, rownum, rn, concretaddr, tel, projectname, distname, companyname, areamanager, sheet);
        }


        return null;

    }


    public static void writerExcel(String keyid, String rownum, String rn, String concretaddr, String tel,
                            String projectname,  String distname, String companyname, String areamanager ,WritableSheet sheet) throws Exception {


        // 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)

            Label lable1 = new Label(0, m, keyid);
            Label lable2 = new Label(1, m, rownum);
            Label lable3 = new Label(2, m, rn);
            Label lable4 = new Label(3, m, concretaddr);
            Label lable5 = new Label(4, m, tel);
            Label lable6 = new Label(5, m, projectname);
            Label lable7 = new Label(6, m, distname);
            Label lable8 = new Label(7, m, companyname);
            Label label9 = new Label(8, m, areamanager);
            sheet.addCell(lable1);
            sheet.addCell(lable2);
            sheet.addCell(lable3);
            sheet.addCell(lable4);
            sheet.addCell(lable5);
            sheet.addCell(lable6);
            sheet.addCell(lable7);
            sheet.addCell(lable8);
            sheet.addCell(label9);
            m++;
    }

}
