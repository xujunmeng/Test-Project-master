package 第一个实例.Interceptor;

import com.google.common.base.Strings;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.util.Map;
import java.util.Properties;

/**
 * Created by james on 2017/7/14.
 * 通过拦截StatementHandler的prepare方法，重写sql语句实现物理分页。
 * 详情请见：
 * 指定该拦截器拦截StatementHandler对象的prepare方法，且方法参数类型为Connection
 */
@Intercepts({@Signature(
        type = StatementHandler.class,
        method = "prepare",
        args = {Connection.class})})
public class PageHandlerInterceptor implements Interceptor {

    //日志
    private static final Logger logger = LoggerFactory.getLogger(PageHandlerInterceptor.class);

    //默认ObjectFactory
    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();

    //默认ObjectWrapperFactory
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //获得拦截的对象
        StatementHandler statementHandler = (StatementHandler)invocation.getTarget();

        //待执行的sql包装对象
        BoundSql boundSql = statementHandler.getBoundSql();

        //待执行的sql
        String sql = boundSql.getSql();

        //判断是否是查询语句
        if (isSelect(sql)) {
            //获得参数集合
            Object params = boundSql.getParameterObject();

            if (params instanceof Map) {  //请求为多个参数，参数采用Map封装
                return complexParamsHandler(invocation, boundSql, (Map<?, ?>) params);
            } else if (params instanceof Page) {  //单个参数且为Page，则表示该操作需要进行分页处理
                return simpleParamHandler(invocation, boundSql, (Page)params);
            }
        }
        return invocation.proceed();
    }

    //拦截器对应的封装原始对象的方法
    @Override
    public Object plugin(Object o) {
        if (o instanceof StatementHandler) {
            return Plugin.wrap(o, this);
        } else {
            return o;
        }
    }

    @Override
    public void setProperties(Properties properties) {

    }
    //当单个参数时，执行此方法
    private Object simpleParamHandler(Invocation invocation, BoundSql boundSql, Page page) throws Throwable {
        return pageHandlerExecutor(invocation, boundSql, page);
    }

    //当多个参数时，执行此方法
    private Object complexParamsHandler(Invocation invocation, BoundSql boundSql, Map<?, ?> params) throws InvocationTargetException, IllegalAccessException {
        //判断参数中是否指定分页
        if (containsPage(params)) {
            return pageHandlerExecutor(invocation, boundSql, (Page)params.get("page"));
        } else {
            return invocation.proceed();
        }
    }

    //执行带有分页信息的查询语句
    private Object pageHandlerExecutor(Invocation invocation, BoundSql boundSql, Page page) throws InvocationTargetException, IllegalAccessException {

        //生成分页sql
        String wrapperSql = createPageSql(boundSql.getSql(), page);

        MetaObject boundSqlMeta = MetaObject.forObject(boundSql, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
        //修改boundSql的sql
        boundSqlMeta.setValue("sql", wrapperSql);
        return invocation.proceed();

    }

    private String createPageSql(String sql, Page page) {
        if (page == null) {
            page = new Page(0, 10);
        }

        int pageSize = page.getPageSize();
        int startIndex = page.getStartLine();

        return sql + " limit " + startIndex + "," + pageSize;
    }

    //判断是否是select语句
    private boolean isSelect(String sql) {
        return !Strings.isNullOrEmpty(sql) && sql.toUpperCase().trim().startsWith("SELECT");
    }

    private boolean containsPage(Map<?, ?> params) {
        return params != null && params.get("page") != null && params.get("page") instanceof Page;
    }
}
