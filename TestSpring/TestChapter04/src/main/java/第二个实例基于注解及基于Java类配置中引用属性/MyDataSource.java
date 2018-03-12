package 第二个实例基于注解及基于Java类配置中引用属性;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by james on 2017/5/26.
 */
@Component
public class MyDataSource {

    @Value("${driverClassName}")
    private String driverClassName;

    @Value("${url}")
    private String url;

    @Value("${userName}")
    private String userName;

    @Value("${password}")
    private String password;

    @Override
    public String toString() {
        return "MyDataSource{" +
                "driverClassName='" + driverClassName + '\'' +
                ", url='" + url + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
