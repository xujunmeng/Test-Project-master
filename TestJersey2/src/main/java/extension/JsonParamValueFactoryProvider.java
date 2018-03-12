package extension;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import org.apache.commons.io.IOUtils;
import org.glassfish.hk2.api.InjectionResolver;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.api.TypeLiteral;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.message.internal.ReaderWriter;
import org.glassfish.jersey.server.ContainerRequest;
import org.glassfish.jersey.server.internal.inject.AbstractContainerRequestValueFactory;
import org.glassfish.jersey.server.internal.inject.AbstractValueFactoryProvider;
import org.glassfish.jersey.server.internal.inject.MultivaluedParameterExtractorProvider;
import org.glassfish.jersey.server.internal.inject.ParamInjectionResolver;
import org.glassfish.jersey.server.model.Parameter;
import org.glassfish.jersey.server.spi.internal.ValueFactoryProvider;
import org.springframework.util.StringUtils;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Encoded;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

@Singleton
public class JsonParamValueFactoryProvider extends AbstractValueFactoryProvider {
	
	public static final String JSON_PROPERTY = "jersey.config.server.representation.json";

	@Inject
    private ServiceLocator locator;
	
	public static class JsonParamInjectionResolverBinder extends AbstractBinder {
        @Override
        protected void configure() {
            bind(JsonParamValueFactoryProvider.class).to(ValueFactoryProvider.class).in(Singleton.class);
            bind(JsonParamInjectionResolver.class).to(new TypeLiteral<InjectionResolver<JsonParam>>() {}).in(Singleton.class);
        }
    }
	
	/**
     * {@link QueryParam &#64;QueryParam} injection resolver.
     */
    @Singleton
    static final class JsonParamInjectionResolver extends ParamInjectionResolver<JsonParam> {

        /**
         * Create new {@link QueryParam &#64;QueryParam} injection resolver.
         */
        public JsonParamInjectionResolver() {
            super(JsonParamValueFactoryProvider.class);
        }
    }
	
	
	 /**
     * Injection constructor.
     *
     * @param mpep     extractor provider.
     * @param injector injector.
     */
    @Inject
    public JsonParamValueFactoryProvider(MultivaluedParameterExtractorProvider mpep, ServiceLocator injector) {
        super(mpep, injector, Parameter.Source.UNKNOWN);
        
    }
    

    @Override
    public AbstractContainerRequestValueFactory<?> createValueFactory(Parameter parameter) {
    	if(parameter.getAnnotation(JsonParam.class) == null){
    		return null;
    	}
        return new JsonParamValueFactory(locator, parameter);
    }

    private static final class JsonParamValueFactory extends AbstractContainerRequestValueFactory<Object> {
    	private final Parameter parameter;
    	@SuppressWarnings("unused")
		private final ServiceLocator locator;

        JsonParamValueFactory(ServiceLocator locator, Parameter parameter) {
            this.locator = locator;
            this.parameter = parameter;
            
        }

        @Override
        public Object provide() {
            ContainerRequest request = getContainerRequest();

            JSONObject json = getCachedJson(request, false);

            if (json == null) {
            	json = getJson(request);
            	
            	if (json == null) {
            		return null;
            	}
                cacheJson(request, json);
            }
            String paramName = parameter.getAnnotation(JsonParam.class).value();
            if (StringUtils.isEmpty(paramName)) {
            	return json.getObject(paramName, parameter.getRawType());
            } else {
            	String strParam = json.getString(paramName);
            	if (List.class.isAssignableFrom(parameter.getRawType())) {
            		ParameterizedType pt = (ParameterizedType)parameter.getType();
            		Class<?> aTypeArg = (Class<?>)pt.getActualTypeArguments()[0];
    				return JSONObject.parseArray(strParam, aTypeArg);
            	} else if (parameter.getRawType().isPrimitive() || String.class.isAssignableFrom(parameter.getRawType())) {
            		return json.getObject(paramName, parameter.getRawType());
            	} else if(Date.class.isAssignableFrom(parameter.getRawType())){
            		return json.getDate(paramName);
            	} else {
            		return JSONObject.parseObject(strParam, parameter.getRawType(), Feature.AllowISO8601DateFormat);
            	}
            }
        }

        private void cacheJson(final ContainerRequest request, final JSONObject obj) {
            request.setProperty(JSON_PROPERTY, obj);
        }

        private JSONObject getJson(final ContainerRequest request) {
            return getJsonParameters(ensureValidRequest(request));
        }

        private static JSONObject getCachedJson(final ContainerRequest request, boolean decode) {
            return (JSONObject) request.getProperty(JSON_PROPERTY);
        }

        private static ContainerRequest ensureValidRequest(final ContainerRequest request) throws IllegalStateException {
            /*f (!MediaTypes.typeEqual(MediaType.APPLICATION_JSON_TYPE, request.getMediaType())) {
                throw new IllegalStateException("Json parameter content type error");
            }*/
            return request;
        }

        @SuppressWarnings("unused")
		private final static Annotation encodedAnnotation = getEncodedAnnotation();

        private static Annotation getEncodedAnnotation() {
            /**
             * Encoded-annotated class.
             */
            @Encoded
            final class EncodedAnnotationTemp {
            }
            return EncodedAnnotationTemp.class.getAnnotation(Encoded.class);
        }

        private JSONObject getJsonParameters(ContainerRequest request) {
//            if (!MediaTypes.typeEqual(MediaType.APPLICATION_JSON_TYPE, request.getMediaType())) {
//                return new JSONObject();
//            }
        	
            request.bufferEntity();

        	String text = "";
        	try {
            	final String charsetName = ReaderWriter.getCharset(MediaType.valueOf(request.getHeaderString(HttpHeaders.CONTENT_TYPE))).name();
        		text = IOUtils.toString(request.getEntityStream(), charsetName);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}

        	if (text == null || text.length() == 0) {
        		return null;
        	}
        	
        	Class<?> rawType = parameter.getRawType();
            if (org.springframework.util.StringUtils.isEmpty(parameter.getAnnotation(JsonParam.class).value())) {

            	JSONObject json = new JSONObject();
            	

            	if (List.class.isAssignableFrom(rawType)) {
            		ParameterizedType pt = (ParameterizedType)parameter.getType();
            		Class<?> aTypeArg = (Class<?>)pt.getActualTypeArguments()[0];
    				json.put("", JSONObject.parseArray(text, aTypeArg));
    				return json;
            	}

            	json.put("", JSONObject.parseObject(text, rawType, Feature.AllowISO8601DateFormat));
            	return json;
            }

            JSONObject json = JSONObject.parseObject(text, Feature.AllowISO8601DateFormat);
            return json == null ? new JSONObject() : json;
        }
    }


}
