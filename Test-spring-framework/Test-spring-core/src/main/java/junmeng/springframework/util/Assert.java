package junmeng.springframework.util;

/**
 * Created by james on 2018/3/5.
 */
public abstract class Assert {

    public static void state(boolean expression, String message) {
        if (!expression) {
            throw new IllegalStateException(message);
        }
    }

}
