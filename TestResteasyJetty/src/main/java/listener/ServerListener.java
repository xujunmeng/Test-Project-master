package listener;

/**
 * Created by junmeng.xu on 2016/10/21.
 * Description : 服务器监听状态
 */
public interface ServerListener {

    /**
     * 服务器运行状态变更响应
     * @param serverStatus 服务器运行状态
     */
    void onServerStatusChanged(int serverStatus);

}
