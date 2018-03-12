package server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.File;
import java.net.InetSocketAddress;

/**
 * Created by junmeng.xu on 2016/10/21.
 * Description : Jetty服务器
 */
public class JettyServer {


    /**
     * 服务器启动状态
     */
    public static final int SERVER_STATUS_STARTED = 1;

    /**
     * 服务器停止状态
     */
    public static final int SERVER_STATUS_STOPPED = 0;

    /**
     * 服务器
     */
    private Server server = null;

    /**
     * 私有构造方法
     */
    private JettyServer(){
        this.init();
    }

    /**
     * 服务器初始化
     */
    private void init(){
        //以本机IP+8989端口构造服务器实例
        InetSocketAddress addr = new InetSocketAddress("192.168.1.43", 8989);
        this.server = new Server(addr);

        WebAppContext context = new WebAppContext();
        // Application访问路径
        context.setContextPath("/JettyServer");
        // Application资源目录
        File resDir = new File("./webapp");
        try {
            context.setResourceBase(resDir.getCanonicalPath());
        } catch (Exception e){
            e.printStackTrace();
        }
        // 将Application注册到服务器
        this.server.setHandler(context);
    }

    /**
     * 获取单实例公共静态方法
     * @return 单实例
     */
    public static JettyServer getInstance()
    {
        return Singletone.INSTANCE;
    }

    /**
     * 启动服务器
     * @throws Exception
     */
    public void start() throws Exception
    {
        if (null != this.server)
        {
            if (this.server.isStarted() || this.server.isStarting())
            {
                this.server.stop();
            }

            this.server.start();

            // 通知服务器状态改变
            ServerHandler.getInstance()
                    .onServerStatusChanged(SERVER_STATUS_STARTED);

            this.server.join();
        }
    }

    /**
     * 停止服务器
     * @throws Exception
     */
    public void stop() throws Exception
    {
        if (null != this.server)
        {
            if (this.server.isStopped() || this.server.isStopping())
            {
                return;
            }

            this.server.stop();

            // 通知服务器状态改变
            ServerHandler.getInstance()
                    .onServerStatusChanged(SERVER_STATUS_STOPPED);
        }
    }













    /**
     * 静态内部类实现单例
     *
     */
    private static class Singletone
    {
        /**
         * 单实例
         */
        private static final JettyServer INSTANCE = new JettyServer();
    }
}
