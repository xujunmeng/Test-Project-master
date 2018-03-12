package 将doc转为pdf;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import org.junit.Test;

/**
 * Created by junmeng.xu on 2016/12/22.
 */
public class Main {

    @Test
    public void test2(){
        long begin = System.currentTimeMillis();
        wordToPDF();
        long end = System.currentTimeMillis();
        System.out.println(end - begin);

    }


    public static void wordToPDF(){

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

}
