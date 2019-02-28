package list;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.ToIntFunction;

/**
@author junmeng.xu
@date  2016年7月19日下午2:17:21
 */
public class Main2 {

	@Test
	public void test1(){
		Junmeng obj1 = new Junmeng();
		obj1.setName("name1");
		obj1.setAge("age1");
		Junmeng obj2 = new Junmeng();
		obj2.setName("name2");
		obj2.setAge("age2");
		Junmeng obj3 = new Junmeng();
		obj3.setName("name3");
		obj3.setAge("age3");
		Junmeng obj4 = new Junmeng();
		obj4.setName("name4");
		obj4.setAge("age4");
		Junmeng obj5 = new Junmeng();
		obj5.setName("name5");
		obj5.setAge("age5");
		Junmeng obj6 = new Junmeng();
		obj6.setName("name6");
		obj6.setAge("age6");
		List<Junmeng> list = Lists.newArrayList();
		list.add(obj1);
		list.add(obj2);
		list.add(obj3);
		list.add(obj4);
		list.add(obj5);
		list.add(obj6);

		list.stream()
			.filter((a) -> (!StringUtils.equals("name4", a.getName())))
			.forEach((b) -> {
				System.out.println(b);
			});


//		list.stream()
//		.filter((m) -> (!StringUtils.equals("", m.getY()) && !StringUtils.equals(null, m.getY()) && !CollectionUtils.isEmpty(m.getItems())))
//		.forEach((c) -> {
//					String y = c.getY();
//					List<Items> items = c.getItems();
//					items.stream().filter((n) -> (StringUtils.equals(n.getCd(), cd)))
//						 .forEach((d) -> map.put(y, d.getLv()));
//	});
//	if(!map.isEmpty()){
//		map.entrySet().stream()
//					  .filter((p) -> !StringUtils.equals(p.getValue(), "NA") && !StringUtils.equals(p.getValue(), "") && !StringUtils.equals(p.getValue(), null))
//					  .forEach((l) -> {
//						  String date = l.getKey();
//						  double lv = Double.parseDouble(BigDecimal.valueOf(Double.parseDouble(l.getValue())).divide(new BigDecimal(100)).toString());
//						  commIdxQuantDao.updateDate(stock, date, lv,tbField, flag);
//					  });
//	}
	}


	@Test
	public void teasdst(){
		List<String> codes = new ArrayList<String>();
		codes.add("xxx");
		codes.add("jjj");
		codes.add("mmm");
		StringBuffer buffer = new StringBuffer("abc").append("123");
		if(CollectionUtils.isNotEmpty(codes)) {

			codes.stream().forEach(a -> buffer.append(a));
		}

		System.out.println(buffer);


	}

	//stream().mapToInt()
	@Test
	public void test(){
		List<String> list = new ArrayList<String>();
		list.add("111");
		list.add("222");
		list.add("333");
		list.add("444");
		int[] array = list.stream().mapToInt(new ToIntFunction<String>() {

			@Override
			public int applyAsInt(String value) {
				return Integer.valueOf(value) + 5;
			}
		}).toArray();
		for (int i : array) {
			System.out.println(i);
		}
	}

	@Test
	public void test2(){
		List<String> list = new ArrayList<String>();
		list.add("111");
		list.add("222");
		list.add("333");
		list.add("444");
		int sum = list.stream().mapToInt(new ToIntFunction<String>() {

			@Override
			public int applyAsInt(String value) {
				return Integer.valueOf(value) + 5;
			}
		}).sum();
		int sum2 = list.stream().mapToInt((a) -> Integer.valueOf(a) + 5).sum();
		System.out.println(sum);
		System.out.println(sum2);
	}

	@Test
	public void test3(){
		List<String> list = new ArrayList<String>();
		list.add("111");
		list.add("222");
		list.add("333");
		list.add("444");
	}

	@Test
	public void test234() {
		LocalDate nowDate = LocalDate.now();

		LocalDate localDate = nowDate.plusDays(2);
		System.out.println(localDate);
	}

	@Test
	public void tset234() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, 2);
		Date time = calendar.getTime();
		System.out.println(time);
	}


















	class Junmeng{
		private String name;
		private String age;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAge() {
			return age;
		}
		public void setAge(String age) {
			this.age = age;
		}
		@Override
		public String toString() {
			return "Junmeng [name=" + name + ", age=" + age + "]";
		}
	}


}
