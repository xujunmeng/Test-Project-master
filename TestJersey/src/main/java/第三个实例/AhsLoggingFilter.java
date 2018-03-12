package 第三个实例;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.message.internal.HeadersFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Priority;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.container.*;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;
import java.io.*;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by james on 2017/5/31.
 */
@PreMatching
@Priority(Integer.MIN_VALUE)
public class AhsLoggingFilter implements ContainerRequestFilter, ContainerResponseFilter, ClientRequestFilter,
        ClientResponseFilter, WriterInterceptor{

    private static final Logger LOGGER = LoggerFactory.getLogger(AhsLoggingFilter.class);

    private final AtomicLong _id = new AtomicLong(0);
    private static final String NOTIFICATION_PREFIX = "* ";
    private static final String REQUEST_PREFIX = "> ";
    private static final String RESPONSE_PREFIX = "< ";
    private static final String ENTITY_LOGGER_PROPERTY = LoggingFilter.class.getName() + ".entityLogger";
    private final boolean printEntity;
    private final int maxEntitySize;

    private final Logger logger;

    public AhsLoggingFilter() {
        this(LOGGER, false);
    }

    public AhsLoggingFilter(Logger logger, boolean printEntity) {
        this.logger = logger;
        this.printEntity = printEntity;
        this.maxEntitySize = 10 * 1024;
    }

    public AhsLoggingFilter(Logger logger, int maxEntitySize) {
        this.logger = logger;
        this.printEntity = true;
        this.maxEntitySize = maxEntitySize;
    }




    @Override
    public void filter(ClientRequestContext context) throws IOException {
        long id = this._id.incrementAndGet();
        StringBuilder b = new StringBuilder();

        printRequestLine(b, id, context.getMethod(), context.getUri());
        // TODO: change to context.getStringHeaders() once the method is added to the API
        printPrefixedHeaders(b, id, REQUEST_PREFIX, HeadersFactory.asStringHeaders(context.getHeaders()));

        if (printEntity && context.hasEntity()) {
            OutputStream stream = new LoggingStream(b, context.getEntityStream());
            context.setEntityStream(stream);
            context.setProperty(ENTITY_LOGGER_PROPERTY, stream);
            // not calling log(b) here - it will be called by the interceptor
        } else {
            log(b);
        }
    }

    @Override
    public void filter(ClientRequestContext clientRequestContext, ClientResponseContext clientResponseContext) throws IOException {

    }

    @Override
    public void filter(ContainerRequestContext context) throws IOException {
        long id = this._id.incrementAndGet();
        StringBuilder b = new StringBuilder();

        printRequestLine(b, id, context.getMethod(), context.getUriInfo().getRequestUri());
        printPrefixedHeaders(b, id, REQUEST_PREFIX, context.getHeaders());

        if (printEntity && context.hasEntity()) {
            context.setEntityStream(logInboundEntity(b, context.getEntityStream()));
        }

        log(b);
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        long id = this._id.incrementAndGet();
        StringBuilder b = new StringBuilder();

        printResponseLine(b, id, responseContext.getStatus());
        // TODO: change to context.getStringHeaders() once the method is added to the API
        printPrefixedHeaders(b, id, RESPONSE_PREFIX, HeadersFactory.asStringHeaders(responseContext.getHeaders()));

        if (printEntity && responseContext.hasEntity()) {
            OutputStream stream = new LoggingStream(b, responseContext.getEntityStream());
            responseContext.setEntityStream(stream);
            requestContext.setProperty(ENTITY_LOGGER_PROPERTY, stream);
            // not calling log(b) here - it will be called by the interceptor
        } else {
            log(b);
        }
    }

    @Override
    public void aroundWriteTo(WriterInterceptorContext writerInterceptorContext) throws IOException, WebApplicationException {

    }

    private void printRequestLine(StringBuilder b, long id, String method, URI uri) {
        prefixId(b, id).append(NOTIFICATION_PREFIX).append("LoggingFilter - Request received on thread ").append(Thread.currentThread().getName()).append("\n");
        prefixId(b, id).append(REQUEST_PREFIX).append(method).append(" ").
                append(uri.toASCIIString()).append("\n");
    }

    private StringBuilder prefixId(StringBuilder b, long id) {
        b.append(Long.toString(id)).append(" ");
        return b;
    }

    private void printPrefixedHeaders(StringBuilder b, long id, final String prefix, MultivaluedMap<String, String> headers) {
        for (Map.Entry<String, List<String>> e : headers.entrySet()) {
            List<?> val = e.getValue();
            String header = e.getKey();

            if (val.size() == 1) {
                prefixId(b, id).append(prefix).append(header).append(": ").append(val.get(0)).append("\n");
            } else {
                StringBuilder sb = new StringBuilder();
                boolean add = false;
                for (Object s : val) {
                    if (add) {
                        sb.append(',');
                    }
                    add = true;
                    sb.append(s);
                }
                prefixId(b, id).append(prefix).append(header).append(": ").append(sb.toString()).append("\n");
            }
        }
    }

    private class LoggingStream extends OutputStream {
        private final StringBuilder b;
        private final OutputStream inner;
        private final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        LoggingStream(StringBuilder b, OutputStream inner) {
            this.b = b;
            this.inner = inner;
        }

        StringBuilder getStringBuilder() {
            // write entity to the builder
            byte[] entity = baos.toByteArray();

            b.append(new String(entity, 0, Math.min(entity.length, maxEntitySize)));
            if (entity.length > maxEntitySize) {
                b.append("...more...");
            }
            b.append('\n');

            return b;
        }

        @Override
        public void write(int i) throws IOException {
            if (baos.size() <= maxEntitySize) {
                baos.write(i);
            }
            inner.write(i);
        }
    }

    private void log(StringBuilder b) {
        if (logger != null) {
            logger.info(b.toString());
        }
    }

    private InputStream logInboundEntity(StringBuilder b, InputStream stream) throws IOException {
        if (!stream.markSupported()) {
            stream = new BufferedInputStream(stream);
        }
        stream.mark(maxEntitySize + 1);
        byte[] entity = new byte[maxEntitySize + 1];
        int entitySize = stream.read(entity);
        b.append(new String(entity, 0, Math.min(entitySize, maxEntitySize)));
        if (entitySize > maxEntitySize) {
            b.append("...more...");
        }
        b.append('\n');
        stream.reset();
        return stream;
    }

    private void printResponseLine(StringBuilder b, long id, int status) {
        prefixId(b, id).append(NOTIFICATION_PREFIX).
                append("LoggingFilter - Response received on thread ").append(Thread.currentThread().getName()).append("\n");
        prefixId(b, id).append(RESPONSE_PREFIX).
                append(Integer.toString(status)).
                append("\n");
    }
}
