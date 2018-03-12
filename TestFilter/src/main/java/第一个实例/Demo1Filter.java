package 第一个实例;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by james on 2017/5/27.
 */
public class Demo1Filter implements Filter {

    private FilterConfig filterConfig;

    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化了");
        this.filterConfig = filterConfig;
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Demo1过滤前");
        System.out.println(filterConfig.getInitParameter("param1"));
        filterChain.doFilter(servletRequest, servletResponse);  //放行。让其走到下个链或目标资源中
        System.out.println("Demo1过滤后");
    }

    public void destroy() {
        System.out.println("销毁了");
    }
}
