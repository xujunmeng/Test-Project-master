package 第一个例子;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.junit.Test;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author junmeng.xu
 * @date 2016年6月30日上午11:44:25
 */
public class Main {

	@Test
	public void tset2(){
		try {
			// 打开文件
			WritableWorkbook book = Workbook.createWorkbook(new File(
					"E:/testSheet2.xls"));
			// 生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("第一页", 0);
			WritableSheet sheet2 = book.createSheet("第二页", 1);
			Label label = new Label(0, 0, "test");
			Label label2 = new Label(1, 0, "test1");
			Label label3 = new Label(2, 0, "test2");
			Label label4 = new Label(1, 0, "test1");
			Label label5 = new Label(2, 0, "test2");
			// 将定义好的单元格添加到工作表中
			sheet.addCell(label);
			sheet.addCell(label2);
			sheet.addCell(label3);
			// 将定义好的单元格添加到工作表中
			sheet2.addCell(label4);
			sheet2.addCell(label5);
			// 写入数据并关闭文件
			book.write();
			book.close();
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			// 打开文件
			WritableWorkbook book = Workbook.createWorkbook(new File(
					"E:/A股公告分析.xls"));
			// 生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("第一页", 0);
			// 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
			// 以及单元格内容为test
			Label label = new Label(0, 0, "test");
			Label label2 = new Label(1, 0, "test1");
			Label label3 = new Label(2, 0, "test2");

			// 将定义好的单元格添加到工作表中
			sheet.addCell(label);
			sheet.addCell(label2);
			sheet.addCell(label3);

			/**//*
				 * 生成一个保存数字的单元格 必须使用Number的完整包路径，否则有语法歧义 单元格位置是第二列，第一行，值为789.123
				 */
			jxl.write.Number number = new jxl.write.Number(3, 0, 555.12541);
			jxl.write.DateTime date = new jxl.write.DateTime(4, 0, new Date());
			Date t = new Date();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = format.format(t);
			Label label4 = new Label(5, 0, time);
			
			java.sql.Date dd = new java.sql.Date((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2016-4-11 20:01:47")).getTime());
			
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
	    	Date fromDate = sdf.parse(dd.toLocaleString());
			String dddd = format.format(fromDate);
			
			Label lable5 = new Label(6, 0, dddd);
			

			sheet.addCell(number);
			sheet.addCell(date);
			sheet.addCell(label4);
			sheet.addCell(lable5);
			// 写入数据并关闭文件
			book.write();
			book.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
