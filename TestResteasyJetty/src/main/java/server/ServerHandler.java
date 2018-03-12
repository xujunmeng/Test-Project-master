package server;

import listener.ServerListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by junmeng.xu on 2016/10/21.
 * Description : 服务器Handler
 */
public class ServerHandler {

    /**
     * 服务器监听器集合
     */
    private Set<ServerListener> serverListeners = null;

    private ServerHandler()
    {
        this.serverListeners = new HashSet<ServerListener>();
    }

    /**
     * 服务器状态变更响应
     * @param serverStatus 服务器状态
     */
    public void onServerStatusChanged(int serverStatus)
    {
        List<ServerListener> sls = new ArrayList<ServerListener>();
        sls.addAll(this.serverListeners);

        for (ServerListener sl : sls)
        {
            sl.onServerStatusChanged(serverStatus);
        }
    }

    /**
     * 添加服务器监听器
     * @param listener 服务器监听器
     */
    public void addListener(ServerListener listener)
    {
        this.serverListeners.add(listener);
    }

    /**
     * 移除服务器监听器
     * @param listener 服务器监听器
     */
    public void rmvListener(ServerListener listener)
    {
        this.serverListeners.remove(listener);
    }

    /**
     * 获取单实例公共静态方法
     * @return 单实例
     */
    public static ServerHandler getInstance()
    {
        return Singletone.INSTANCE;
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
        private static final ServerHandler INSTANCE = new ServerHandler();
    }

}
