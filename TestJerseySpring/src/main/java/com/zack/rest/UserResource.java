package com.zack.rest;

import com.zack.rest.domain.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by james on 2017/5/31.
 */
@Component
@Scope("prototype")
@Path("/user")
public class UserResource {

    // 无参数返回json格式，如返回xml，将Produces参数修改为MediaType.APPLICATION_XML
    @GET
    @Path("/showUser")
    @Produces(MediaType.APPLICATION_JSON)
    public User showUser() {
        User user = new User();
        user.setUserName("sed");
        user.setAge(29);
        return user;
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> list() {
        List<User> userList = new ArrayList<User>();
        userList.add(new User("Lucy",29));
        userList.add(new User("Lily",30));
        return userList;
    }


    // @PathParam("id")获取URI中指定规则的参数
    @GET
    @Path("/show/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String showByIdOfPath(@PathParam("id") String id) {
        return id;
    }

    // @QueryParam 用于获取GET请求中的查询参数
    @GET
    @Path("/show")
    @Produces(MediaType.TEXT_PLAIN)
    public String showByIdOfQuery(@QueryParam("id") String id)
    {
        return id;
    }

    // 输入参数为xml格式，输出为json格式。可以根据需要切换produces和consumes的类型
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON) // 指定响应所能接受的 MIME 类型
    @Produces(MediaType.APPLICATION_JSON) // 指定发送请求的 MIME 类型
    public User add(User user) {
        int age = user.getAge();
        String userName = user.getUserName();
        User result = new User(userName + "result", age + 10);
        return result;
    }

    // BeanParam 当请求参数很多时，比如客户端提交一个修改用户的PUT请求，请求中包含很多项用户信息
    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User update(User user) {
        return user;
    }

    // @FormParam 从POST请求的表单参数中获取数据
    @POST
    @Path("/addFromForm")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.TEXT_PLAIN)
    public String addFromForm(@FormParam("username") String userName,@FormParam("age") int age) {
        User user = new User();
        user.setUserName(userName);
        user.setAge(age);
        return user.toString();
    }

    // 多参数传递
    @POST
    @Path("/multivalued")
    @Produces(MediaType.TEXT_PLAIN)
    public String multivalued(MultivaluedMap<String, String> formParams) {
        String result = "who:" + formParams.getFirst("who") + " ; what:" + formParams.getFirst("what");
        return formParams.toString();
    }

    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String delete(@PathParam("id") String id) {
        return "delete : " + id;
    }
}
