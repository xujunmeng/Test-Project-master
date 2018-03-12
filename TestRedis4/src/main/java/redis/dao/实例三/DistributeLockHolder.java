package redis.dao.实例三;

/**
 * Created by james on 2017/8/10.
 */
public class DistributeLockHolder {

    private String lock;

    public String getLock() {
        return lock;
    }

    public void setLock(String lock) {
        this.lock = lock;
    }
}
