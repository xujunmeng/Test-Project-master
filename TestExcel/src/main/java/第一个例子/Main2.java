package 第一个例子;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;

/**
@author junmeng.xu
@date  2016年6月30日下午1:59:55
 */
public class Main2 {


	@Test
	public void test1() throws Exception {
		Mongo mg = new Mongo("192.168.250.200", 27017);
		DB db = mg.getDB("quant");
		DBCollection coll = db.getCollection("quant_dict_factors");

		// 打开文件
		File file = new File("E:/京东策略因子库定义2.xls");
		FileInputStream fis = new FileInputStream(file);
		Workbook rwb = Workbook.getWorkbook(fis);
		Sheet[] sheet = rwb.getSheets();
		for (int i = 0; i < sheet.length; i++) {
			Sheet rs = rwb.getSheet(i);
			for (int j = 0; j < rs.getRows(); j++) {
				Cell[] cells = rs.getRow(j);
				int length = cells.length;
				String suanfa = "";
				String detail = "";
				String desc = "";
				String canshu = "";
				String code = cells[0].getContents();
				if(length >= 6){
					suanfa = cells[5].getContents();
				}
				if(length >= 4){
					desc = cells[3].getContents();
				}
				if(length >= 5){
					detail = cells[4].getContents();
				}
				if(length >= 7){
					canshu = cells[6].getContents();
				}


				System.out.println("length : " + length + " code : " + code + " desc : " + desc + " suanfa : " + suanfa + " detail : " + detail);
				BasicDBObject query = new BasicDBObject();
				query.put("code", code);

				BasicDBObject field = new BasicDBObject();

				BasicDBObject dd = new BasicDBObject("$set", field.append("desc.szh", desc).append("detail", detail).append("algorithm", new BasicDBObject("content", suanfa).append("param", canshu)));
				if(coll.count(query) == 1){
					coll.update(query, dd);
				}

			}
		}

	}

	public static void main(String[] args) throws Exception {
		// 打开文件
		WritableWorkbook book = Workbook.createWorkbook(new File(
				"E:/A股公告分析bak.xls"));
		// 生成名为“第一页”的工作表，参数0表示这是第一页
		WritableSheet sheet = book.createSheet("第一页", 0);
		
		// 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
		int m = 0;
		for (int i = 0; i < 10; i++) {
			Label lable6 = new Label(0, m, "SDF");
			Label lable7 = new Label(1, m, "SDF");
			Label lable8 = new Label(2, m, "SDF");
			Label lable9 = new Label(3, m, "SDF");
			Label lable10 = new Label(4, m, "SDF");
			sheet.addCell(lable6);
			sheet.addCell(lable7);
			sheet.addCell(lable8);
			sheet.addCell(lable9);
			sheet.addCell(lable10);
			m = m+1;
		}
		book.write();
		book.close();
	}
	
}
