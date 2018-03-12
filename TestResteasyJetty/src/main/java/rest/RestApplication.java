package rest;

import rest.service.RestService;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by junmeng.xu on 2016/10/21.
 */
public class RestApplication extends Application {

    private Set<Object> singletons = new HashSet<Object>();

    public RestApplication(){
        //构造方法中注册REST服务类
        this.singletons.add(new RestService());
    }

    @Override
    public Set<Object> getSingletons() {
        return this.singletons;
    }
}
