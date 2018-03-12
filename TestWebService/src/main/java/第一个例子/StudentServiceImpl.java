package 第一个例子;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
@author junmeng.xu
@date  2016年5月31日下午1:27:16
 */
public class StudentServiceImpl implements IStudentService {

	public Student getStudent(long id, String name) {
		Student s = new Student();
		s.setId(id);
		s.setName(name);
		try {
			s.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("1983-04-26"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return s;
	}

	public Student getStudent(String name) {
		Student s = new Student();
		s.setId(1);
		s.setName(name);
		try {
			s.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("1983-04-26"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return s;
	}

}
