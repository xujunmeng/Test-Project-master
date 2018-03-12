package Lambda表达式.supplier实例.实例测试;

import java.util.function.Supplier;

/**
 * Created by james on 2017/10/24.
 */
public class Main {

    public static boolean executeWithinConcurrency(Supplier<Boolean> supplier, boolean throwIffailed) {
        int retryIndex = 0;
        boolean succeed = false;
        while (retryIndex < 5)
        {
            retryIndex += 1;
            succeed = supplier.get();
            if (!succeed) {
                sleep(50L);
            } else {
                break;
            }
        }
        if (throwIffailed && !succeed) {

        }
        return succeed;
    }

    public static boolean executeWithinConcurrency2(Boolean b, boolean throwIffailed) {
        int retryIndex = 0;
        boolean succeed = false;
        while (retryIndex < 5)
        {
            retryIndex += 1;
            if (!b) {
                sleep(50L);
            } else {
                break;
            }
        }
        if (throwIffailed && !succeed) {

        }
        return succeed;
    }

    private static void sleep(Long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
        }

    }

    public static void main(String[] args) {
//        executeWithinConcurrency(() -> {
//            return false;
//        }, true);

        executeWithinConcurrency2(false, true);
    }
}
