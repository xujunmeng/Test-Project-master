package redis.dao.实例三;

/**
 * Created by james on 2017/8/10.
 */
public interface DistributeLockHelper {

    DistributeLockHolder getLock(String hashCode);

    void release(DistributeLockHolder lockHelper);

    Boolean isLocked(String hashCode);

}
