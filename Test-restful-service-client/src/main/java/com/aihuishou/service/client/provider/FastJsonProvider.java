package com.aihuishou.service.client.provider;

import com.aihuishou.service.client.util.IOUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.*;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * Created by james on 2017/5/31.
 */
@Provider
public class FastJsonProvider implements MessageBodyReader<Object>, MessageBodyWriter<Object> {

    private boolean annotated = false;

    private String[] scanpackages = null;

    private Class<?>[] clazzes = null;

    protected FastJsonConfig fastJsonConfig = new FastJsonConfig(new SerializeConfig(), null, null, new ParserConfig(), null);

    /**
     * Can serialize/deserialize all types.
     */
    public FastJsonProvider() {

    }

    /**
     * Only serialize/deserialize all types annotated with {@link FastJsonType}
     */
    public FastJsonProvider(boolean annotated) {
        this.annotated = annotated;
    }

    /**
     * Only serialize/deserialize all types in scanpackages
     */
    public FastJsonProvider(String[] scanpackages) {
        this.scanpackages = scanpackages;
    }

    /**
     * Only serialize/deserialize all types in scanpackages
     */
    public FastJsonProvider(String[] scanpackages, boolean annotated) {
        this.scanpackages = scanpackages;
        this.annotated = annotated;
    }

    /**
     * Only serialize/deserialize all types in clazzes
     */
    public FastJsonProvider(Class<?>[] clazzes) {
        this.clazzes = clazzes;
    }

    /**
     * Init this fastjson with more fastjson configuration
     */
    public FastJsonProvider init(FastJsonConfig fastJsonConfig) {
        this.fastJsonConfig = fastJsonConfig;
        return this;
    }


    /**
     *
     * MessageBodyReader impl
     *
     */


    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        if (!hasMatchingMediaType(mediaType)) {
            return false;
        }
        return isValidType(type, annotations);
    }

    @Override
    public Object readFrom(Class<Object> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        String input = IOUtils.inputStreamToString(entityStream);
        if (input == null || input.length() == 0) {
            return null;
        }
        if (fastJsonConfig.features == null) {
            return JSON.parseObject(input, genericType, fastJsonConfig.parserConfig, JSON.DEFAULT_PARSER_FEATURE);
        } else {
            return JSON.parseObject(input, genericType, fastJsonConfig.parserConfig, JSON.DEFAULT_GENERATE_FEATURE, fastJsonConfig.features);
        }
    }


    /**
	 *
	 * MessageBodyWriter impl
	 *
	 */


    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        if (!hasMatchingMediaType(mediaType)) {
            return false;
        }
        return isValidType(type, annotations);
    }

    @Override
    public long getSize(Object t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(Object t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders,
                        OutputStream entityStream) throws IOException, WebApplicationException {
        SerializeFilter filter = null;
        if (fastJsonConfig.serializeFilters != null) {
            filter = fastJsonConfig.serializeFilters.get(type);
        }
        String jsonStr = toJSONString(t, filter, fastJsonConfig.serializerFeatures);
        if (jsonStr != null) {
            entityStream.write(jsonStr.getBytes());
        }
    }

    private boolean hasMatchingMediaType(MediaType mediaType) {
        if (mediaType != null) {
            String subtype = mediaType.getSubtype();
            return "json".equalsIgnoreCase(subtype) ||
                    subtype.endsWith("+json") ||
                    "javascript".equals(subtype) ||
                    "x-javascript".equals(subtype);
        }
        return true;
    }

    private boolean isValidType(Class<?> type, Annotation[] annotations) {
        if (type == null) {
            return false;
        }
        if (annotated) {
            return checkAnnotation(type);
        } else if (scanpackages != null) {
            String classPackage = type.getPackage().getName();
            for (String pkg : scanpackages) {
                if (classPackage.startsWith(pkg)) {
                    if (annotated) {
                        return checkAnnotation(type);
                    } else {
                        return true;
                    }
                }
            }
            return false;
        } else if (clazzes != null) {
            for (Class<?> cls : clazzes) {
                if (cls == type) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    private boolean checkAnnotation(Class<?> type) {
        Annotation[] annotations = type.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof FastJsonType) {
                return true;
            }
        }
        return false;
    }

    private String toJSONString(Object object, SerializeFilter filter, SerializerFeature[] features) {
        SerializeWriter out = new SerializeWriter();
        try {
            JSONSerializer serializer = new JSONSerializer(out, fastJsonConfig.serializeConfig);
            if (features != null) {
                for (SerializerFeature feature : features) {
                    serializer.config(feature, true);
                }
            }
            if (filter != null) {
                if (filter instanceof PropertyPreFilter) {
                    serializer.getPropertyPreFilters().add((PropertyPreFilter) filter);
                }

                if (filter instanceof NameFilter) {
                    serializer.getNameFilters().add((NameFilter) filter);
                }

                if (filter instanceof ValueFilter) {
                    serializer.getValueFilters().add((ValueFilter) filter);
                }

                if (filter instanceof PropertyFilter) {
                    serializer.getPropertyFilters().add((PropertyFilter) filter);
                }

                if (filter instanceof BeforeFilter) {
                    serializer.getBeforeFilters().add((BeforeFilter) filter);
                }

                if (filter instanceof AfterFilter) {
                    serializer.getAfterFilters().add((AfterFilter) filter);
                }
            }
            serializer.write(object);
            return out.toString();
        } finally {
            out.close();
        }

    }

}
