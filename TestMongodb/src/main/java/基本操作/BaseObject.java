package 基本操作;

import java.io.Serializable;

/**
 * Created by junmeng.xu on 2016/11/15.
 */
public abstract class BaseObject implements Serializable {
    @Override
    public abstract int hashCode();

    @Override
    public abstract String toString();

    @Override
    public abstract boolean equals(Object obj);
}
