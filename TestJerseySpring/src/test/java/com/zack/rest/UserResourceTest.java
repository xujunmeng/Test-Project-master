package com.zack.rest;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.zack.rest.domain.User;
import org.junit.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

/**
 * Created by james on 2017/5/31.
 */

public class UserResourceTest {

    @BeforeClass
    public static void beforeClass() throws Exception{

    }

    @Before
    public void before() throws Exception {

    }

    @After
    public void after() throws Exception {

    }

    @AfterClass
    public static void afterClass() throws Exception{

    }

    private static WebResource getWebResource(String USER_URL) {
        Client client = Client.create(); // 创建一个 com.sun.jersey .api.client.Client类的实例
        WebResource webResource = client.resource(USER_URL); // 建了一个 WebResponse 对象
        return webResource;
    }

    @Ignore
    @Test
    public void testShowUser() {
        String USER_URL = "http://localhost:8080/rest/user/showUser";
        System.out.println(getWebResource(USER_URL).get(String.class));
    }

    @Ignore
    @Test
    public void testList() {
        String USER_URL = "http://localhost:8080/rest/user/list";
        System.out.println(getWebResource(USER_URL).get(String.class));
    }

    @Ignore
    @Test
    public void testShowByIdOfPath() {
        String USER_URL = "http://localhost:8080/rest/user/show";
        System.out.println(getWebResource(USER_URL).path("123").get(String.class));
    }

    @Ignore
    @Test
    public void testShowByIdOfQuery() {
        String USER_URL = "http://localhost:8080/rest/user/show";
        System.out.println(getWebResource(USER_URL).queryParam("id", "109").get(String.class));
    }

    @Ignore
    @Test
    public void testAdd() {
        User user = new User();
        user.setUserName("Jim");
        user.setAge(39);

        String USER_URL = "http://localhost:8080/rest/user/add";
        WebResource webResource = getWebResource(USER_URL);
        WebResource.Builder entity = webResource.entity(user, MediaType.APPLICATION_JSON);
        String result = entity.post(String.class);
        System.out.println(result);
    }

    @Ignore
    @Test
    public void testAdd2() {
        String USER_URL = "http://localhost:8080/rest/user/add";
        String userJson = "{\"userName\":\"Jim\",\"age\":\"6\"}";
        String result = getWebResource(USER_URL).entity(userJson,MediaType.APPLICATION_JSON).post(String.class);
        System.out.println(result);
    }

    @Ignore
    @Test
    public void testUpdate() {
        String USER_URL = "http://localhost:8080/rest/user/update";
        User user = new User();
        user.setUserName("Jim");
        user.setAge(39);
        System.out.println(getWebResource(USER_URL).entity(user,MediaType.APPLICATION_JSON).put(String.class));
    }

    @Ignore
    @Test
    public void testUpdate2() {
        String USER_URL = "http://localhost:8080/rest/user/update";
        String json = "{\"userName\":\"Jim\",\"age\":\"6\"}";
        System.out.println(getWebResource(USER_URL).entity(json,MediaType.APPLICATION_JSON).put(String.class));
    }

    @Ignore
    @Test
    public void testAddFromForm() {
        String USER_URL = "http://localhost:8080/rest/user/addFromForm";
        Form form = new Form();
        form.add("username", "Jim");
        form.add("age",39);
        //ClientResponse 对象代表了一个客户端收到的 HTTP 响应。
        ClientResponse response = getWebResource(USER_URL).type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, form);
        int status = response.getStatus();   //获取对应请求的 HTTP 状态码
        System.out.println("status:"+status);
        System.out.println(response.getEntity(String.class));

    }

    @Ignore
    @Test
    public void testMultivalued(){
        MultivaluedMap<String, String> params = new MultivaluedMapImpl();
        params.add("who", "Jim");
        params.add("what","do house work");
        params.add("how","use machine");
        params.add("where","Xue Lang");
        params.add("when","all day");

        String USER_URL = "http://localhost:8080/rest/user/multivalued";
        String result = getWebResource(USER_URL).post(String.class,params);
        System.out.println(result);
    }

    @Ignore
    @Test
    public void testDelete(){
        String USER_URL = "http://localhost:8080/rest/user/delete";
        String result = getWebResource(USER_URL).path("109").delete(String.class);
        System.out.println(result);
    }
}
