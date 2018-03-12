package extension;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JsonMessageBodyWriter implements MessageBodyWriter<Object>
{
    @Override
    public boolean isWriteable(final Class<?> type, final Type genericType,
        final Annotation[] annotations, final MediaType mediaType)
    {
    	Boolean result = MediaType.APPLICATION_JSON_TYPE.getType().equalsIgnoreCase(mediaType.getType()) &&
    			MediaType.APPLICATION_JSON_TYPE.getSubtype().equalsIgnoreCase(mediaType.getSubtype());
    	return result;
    }

    @Override
    public long getSize(final Object t, final Class<?> type, final Type genericType,
        final Annotation[] annotations, final MediaType mediaType)
    {
    	// deprecated by JAX-RS 2.0 and ignored by Jersey runtime
        return 0;
    }

    @Override
    public void writeTo(final Object t, final Class<?> type, final Type genericType,
                        final Annotation[] annotations, final MediaType mediaType,
                        final MultivaluedMap<String, Object> httpHeaders, final OutputStream entityStream)
        throws IOException, WebApplicationException
    {
    	
    	byte[] json = JSON.toJSONBytes(t, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect,
    			SerializerFeature.WriteNonStringKeyAsString);
    	entityStream.write(json); 
    }
    

   /* private static SerializeConfig mapping = new SerializeConfig();  
    private static String dateFormat;  
    static {  
        dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";  
        mapping.put(Date.class, new SimpleDateFormatSerializer(dateFormat));  
    }  */
}
