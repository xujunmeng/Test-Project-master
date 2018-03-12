package table;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.common.collect.Table.Cell;
import org.junit.Test;

import java.util.*;

/**
 * @author junmeng.xu
 * @date 2016年4月20日下午5:18:02
 */
public class Main {

	@Test
	public void catest(){
		HashBasedTable<String, String, Object[]> dataTickVal = HashBasedTable.create();
		dataTickVal.put("2001-01-01", "000001", new Object[]{"000001", 3.4D, 5.2D});
		dataTickVal.put("2001-01-02", "000002", new Object[]{"000002", 3.3D, 6.2D});
		dataTickVal.put("2001-01-03", "000003", new Object[]{"000003", 3.2D, 7.2D});
		dataTickVal.put("2001-01-04", "000004", new Object[]{"000004", 3.1D, 8.2D});
		dataTickVal.put("2001-01-05", "000005", new Object[]{"000005", 3.0D, 9.2D});
		dataTickVal.put("2001-01-06", "000006", new Object[]{"000006", 2.9D, 10.2D});
		Map<String, Map<String, Object[]>> map = dataTickVal.rowMap();
		Map<String, Object[][]> result = new HashMap<>();
		Set<Map.Entry<String, Map<String, Object[]>>> set = map.entrySet();
		for (Map.Entry<String, Map<String, Object[]>> entry : set) {
			String dt = entry.getKey();
			Map<String, Object[]> tv = entry.getValue();
			int len = tv.size();
			Object[][] arr = new Object[len][];
			int i = 0;
			Collection<Object[]> values = tv.values();
			for (Object[] o : values){
				arr[i++] = o;
			}
			result.put(dt, arr);
		}

//		map.forEach((dt, tv) -> {
//			int len = tv.size();
//			Object[][] arr = new Object[len][];
//			int i = 0;
//			for (Object[] o : tv.values()){
//				arr[i++] = o;
//			}
//			result.put(dt, arr);
//
//		});
		System.out.println(result);
	}

	@Test
	public void test22(){
		Table<String, String, String> aTable = HashBasedTable.create();
		aTable.put("IBM", "101", "Mahesh");
		aTable.put("IBM", "102", "Ramesh");
		aTable.put("IBM", "103", "Suresh");
		aTable.put("Microsoft", "111", "Sohan");
		aTable.put("Microsoft", "112", "Mohan");
		aTable.put("Microsoft", "113", "Rohan");
		aTable.put("TCS", "121", "Ram");
		aTable.put("TCS", "122", "Shyam");
		aTable.put("TCS", "123", "Sunil");
	}


	@Test
	public void TableTest() {
		Table<String, String, String> aTable = HashBasedTable.create();
		aTable.put("IBM", "101", "Mahesh");
		aTable.put("IBM", "102", "Ramesh");
		aTable.put("IBM", "103", "Suresh");
		aTable.put("Microsoft", "111", "Sohan");
		aTable.put("Microsoft", "112", "Mohan");
		aTable.put("Microsoft", "113", "Rohan");
		aTable.put("TCS", "121", "Ram");
		aTable.put("TCS", "122", "Shyam");
		aTable.put("TCS", "123", "Sunil");
		
		Iterator<Cell<String, String, String>> iterator = aTable.cellSet().iterator();
		
		while(iterator.hasNext()){
			Cell<String, String, String> next = iterator.next();
			String rowKey = next.getRowKey();
			System.out.println(rowKey);
			String columnKey = next.getColumnKey();
			System.out.println(columnKey);
			String value = next.getValue();
			System.out.println(value);
			System.out.println("==============");
			
		}
		
		aTable.cellSet().stream().forEach((a) ->{
			System.out.println("==============");
			System.out.println(a.getRowKey());
			System.out.println(a.getColumnKey());
			System.out.println(a.getValue());
			System.out.println("==============");
		});
		
//		System.out.println("==============");
//		Map<String, String> bTable = aTable.row("IBM");
//		for(Map.Entry<String, String> entry : bTable.entrySet()){
//			System.out.println("Emp id : " + entry.getKey() + " , Name : " + entry.getValue());
//		}
//		System.out.println("==============");
//		Map<String, String> cTable = aTable.column("101");
//		for(Map.Entry<String, String> entry : cTable.entrySet()){
//			System.out.println(entry.getKey() + ":" + entry.getValue());
//		}
//		System.out.println("==============");
//		Set<String> rowKeySet = aTable.rowKeySet();
//		for (String string : rowKeySet) {
//			System.out.print(string + " ");
//		}
//		System.out.println();
//		System.out.println("==============");
//		Set<String> columnKeySet = aTable.columnKeySet();
//		for (String string : columnKeySet) {
//			System.out.print(string + " ");
//		}
		
//		System.out.println(aTable.get("IBM", "102"));
		
		
	}

}
