package 将txt转为pdf;


import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import org.junit.Test;

import java.io.*;

/**
 * Created by junmeng.xu on 2016/12/22.
 */
public class Main2 {


    @Test
    public void test2(){
        long begin = System.currentTimeMillis();
        String str = "E:\\resource\\announce\\temp\\e9d6813bd3040455ae20dc2f53aee3b8.txt";  //txt文件
        makePDF(str);
        long end = System.currentTimeMillis();
        System.out.println(end - begin);
    }

    public static void makePDF(String fileName) {

        try {

            //首先创建一个字体

            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);

            Font FontChinese = new Font(bfChinese);

            String line;

            Document document;

            document = new Document(PageSize.A4);

            InputStreamReader read = new InputStreamReader(
                    new FileInputStream(fileName),"GBK");//考虑到编码格式
            BufferedReader in = new BufferedReader(read);

            PdfWriter.getInstance(document, new FileOutputStream(fileName.substring(0, fileName.indexOf(".")) + ".pdf"));

            document.open();

            while ((line = in.readLine()) != null)

                document.add(new Paragraph(line, FontChinese));

            document.close();

        }catch(Exception e) {

            System.err.println(e.getMessage());

        }

    }


}
