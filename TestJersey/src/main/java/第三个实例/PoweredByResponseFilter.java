package 第三个实例;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by james on 2017/5/27.
 */
public class PoweredByResponseFilter implements ContainerResponseFilter {

    private static final String NOTIFICATION_PREFIX = "* ";
    private static final String RESPONSE_PREFIX = "< ";
    private static final String TAKE_TIME = "Take-Time";

    private static final ThreadLocal<StringBuilder> stringBuilder = new ThreadLocal<>();
    private static final ThreadLocal<Long> requestTimeThreadLocal = new ThreadLocal<>();

    private final AtomicLong _id = new AtomicLong(0);

    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {

        int status = containerResponseContext.getStatus();
        containerResponseContext.getHeaders().putSingle(TAKE_TIME, System.currentTimeMillis() - requestTimeThreadLocal.get());

        long id = this._id.get();

        StringBuilder stringBuilder = PoweredByResponseFilter.stringBuilder.get();
        stringBuilder.append("\n");

        stringBuilder.append(Long.toString(id))
                .append(" ")
                .append(NOTIFICATION_PREFIX)
                .append("Server responded with a response")
                .append(" on thread ")
                .append(Thread.currentThread().getName()).append("\n");

        stringBuilder.append(Long.toString(id))
                .append(RESPONSE_PREFIX).
                append(Integer.toString(status)).
                append("\n");

    }

}
