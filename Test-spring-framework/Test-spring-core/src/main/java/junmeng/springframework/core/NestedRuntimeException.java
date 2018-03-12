package junmeng.springframework.core;

import junmeng.springframework.lang.Nullable;

/**
 *
 * 方便的类用根本原因包装运行时。
 * 这个类是强制程序员扩展类。 将包括嵌套的异常信息; 和其他类似的方法将委托给包装的异常（如果有的话）。
 * <p>这个类和类之间的相似性是不可避免的，因为Java迫使这两个类有不同的超类（啊，具体继承的灵活性！）。
 *
 * Created by james on 2018/3/3.
 */
public abstract class NestedRuntimeException extends RuntimeException {

    /** Use serialVersionUID from Spring 1.2 for interoperability */
    private static final long serialVersionUID = 5439915454935047936L;

    static {
        // Eagerly load the NestedExceptionUtils class to avoid classloader deadlock
        // issues on OSGi when calling getMessage(). Reported by Don Brown; SPR-5607.
        NestedExceptionUtils.class.getName();
    }

    /**
     * Construct a {@code NestedRuntimeException} with the specified detail message.
     * @param msg the detail message
     */
    public NestedRuntimeException(String msg) {
        super(msg);
    }

    /**
     * Construct a {@code NestedRuntimeException} with the specified detail message
     * and nested exception.
     * @param msg the detail message
     * @param cause the nested exception
     */
    public NestedRuntimeException(@Nullable String msg, @Nullable Throwable cause) {
        super(msg, cause);
    }

    /**
     * Return the detail message, including the message from the nested exception
     * if there is one.
     */
    @Override
    @Nullable
    public String getMessage() {
        return NestedExceptionUtils.buildMessage(super.getMessage(), getCause());
    }

    /**
     * Retrieve the innermost cause of this exception, if any.
     * @return the innermost exception, or {@code null} if none
     * @since 2.0
     */
    @Nullable
    public Throwable getRootCause() {
        return NestedExceptionUtils.getRootCause(this);
    }

    /**
     * Retrieve the most specific cause of this exception, that is,
     * either the innermost cause (root cause) or this exception itself.
     * <p>Differs from {@link #getRootCause()} in that it falls back
     * to the present exception if there is no root cause.
     * @return the most specific cause (never {@code null})
     * @since 2.0.3
     */
    public Throwable getMostSpecificCause() {
        Throwable rootCause = getRootCause();
        return (rootCause != null ? rootCause : this);
    }

    /**
     * Check whether this exception contains an exception of the given type:
     * either it is of the given class itself or it contains a nested cause
     * of the given type.
     * @param exType the exception type to look for
     * @return whether there is a nested exception of the specified type
     */
    public boolean contains(@Nullable Class<?> exType) {
        if (exType == null) {
            return false;
        }
        if (exType.isInstance(this)) {
            return true;
        }
        Throwable cause = getCause();
        if (cause == this) {
            return false;
        }
        if (cause instanceof NestedRuntimeException) {
            return ((NestedRuntimeException) cause).contains(exType);
        }
        else {
            while (cause != null) {
                if (exType.isInstance(cause)) {
                    return true;
                }
                if (cause.getCause() == cause) {
                    break;
                }
                cause = cause.getCause();
            }
            return false;
        }
    }

}
