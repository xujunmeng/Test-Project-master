package 使用MyBatis对表执行CRUD操作是基于注解的实现;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.junmeng.entity.User;

/**
 * @author junmeng.xu
 * @date 2016年8月19日下午2:54:51
 * 
 * 		   使用MyBatis对表执行CRUD操作是基于注解的实现
 * 		 
 *       定义sql映射的接口，使用注解指明方法要执行的sql
 */
public interface UserMapperI {

	// 使用@Insert注解指明add方法要执行的sql
	@Insert("insert into users(name, age) values(#{name}, #{age})")
	public int add(User user);

	// 使用@Delete注解指明deleteByID方法要执行的sql
	@Delete("delete from users where id=#{id}")
	public int deleteById(int id);

	// 使用@Update注解指明update方法要执行的SQL
	@Update("update users set name=#{name},age=#{age} where id=#{id}")
	public int update(User user);

	// 使用@Select注解指明getById方法要执行的SQL
	@Select("select * from users where id=#{id}")
	public User getById(int id);

	// 使用@Select注解指明getAll方法要执行的SQL
	@Select("select * from users")
	public List<User> getAll();

}
