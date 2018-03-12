package junmeng.springframework.transaction;

import junmeng.springframework.core.NestedRuntimeException;
import junmeng.springframework.lang.Nullable;

/**
 * Superclass for all transaction exceptions.
 *
 * Created by james on 2018/3/3.
 */
public abstract class TransactionException extends NestedRuntimeException {

    public TransactionException(String msg) {
        super(msg);
    }

    public TransactionException(@Nullable String msg, @Nullable Throwable cause) {
        super(msg, cause);
    }
}
