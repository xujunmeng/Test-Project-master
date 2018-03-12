package junmeng.springframework.core;

import junmeng.springframework.lang.Nullable;

/**
 * Helper类用于实现能够保持嵌套异常的异常类。 必要的，因为我们不能在不同的异常类型之间共享一个基类。
 *
 * 主要用于框架内部。
 *
 * Created by james on 2018/3/3.
 */
public abstract class NestedExceptionUtils {

    /**
     * 为给定的基本消息和根本原因构建消息。
     */
    @Nullable
    public static String buildMessage(@Nullable String message, @Nullable Throwable cause) {
        if (cause == null) {
            return message;
        }
        StringBuilder sb = new StringBuilder(64);
        if (message != null) {
            sb.append(message).append("; ");
        }
        sb.append("nested exception is ").append(cause);
        return sb.toString();
    }

    /**
     * 检索给定异常的最内层原因（如果有的话）。
     */
    @Nullable
    public static Throwable getRootCause(@Nullable Throwable original) {
        if (original == null) {
            return null;
        }
        Throwable rootCause = null;
        Throwable cause = original.getCause();
        while (cause != null && cause != rootCause) {
            rootCause = cause;
            cause = cause.getCause();
        }
        return rootCause;
    }

    /**
     * 检索给定异常的最具体原因，也就是说，
     * 最根本的原因（根本原因）或异常本身。
     * <p>与{@link #getRootCause}不同之处在于，如果没有根本原因，它将回退到原始异常。
     */
    public static Throwable getMostSpecificCause(Throwable original) {
        Throwable rootCause = getRootCause(original);
        return (rootCause != null ? rootCause : original);
    }

}
