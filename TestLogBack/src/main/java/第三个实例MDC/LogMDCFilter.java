package 第三个实例MDC;

import org.slf4j.MDC;

import javax.servlet.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by james on 2017/5/27.
 */
public class LogMDCFilter implements Filter {

    public final static String IPKey = "ip";
    public final static String HostKey = "host";
    public static String HostName = "";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        HostName = getHostName(getInetAddress());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //把网络地址放入MDC中，那么在layout pattern 中通过使用 %X , 就可在每条日志中增加网络地址的信息
        MDC.put(HostKey, HostName);
        MDC.put(IPKey, servletRequest.getRemoteAddr());

        //处理其他的filter链
        filterChain.doFilter(servletRequest, servletResponse);

        //从NDC的堆栈中删除网络地址
        MDC.remove(HostKey);
        MDC.remove(IPKey);
    }

    @Override
    public void destroy() {

    }

    public static InetAddress getInetAddress() {
        try {
            return InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getHostName(InetAddress inetAddress) {
        if (null == inetAddress) {
            return "";
        }
        String hostName = inetAddress.getHostName();
        return hostName;
    }

    public static void main(String[] args) {
        HostName = getHostName(getInetAddress());
        System.out.println(HostName);
    }


}
