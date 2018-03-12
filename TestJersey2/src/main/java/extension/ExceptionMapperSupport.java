package extension;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.NotSupportedException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * 统一异常处理
 * @author saber.wang
 * @version 1.0
 * */
@Provider
public class ExceptionMapperSupport implements ExceptionMapper<Exception> {
	@Context
	private HttpServletRequest request;
	
	@Context
	private ServletContext servletContext;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionMapperSupport.class);
	
	public Response toResponse(Exception exception){
		
		//LOGGER.error(exception.getMessage(), exception);
		String message = null;
		//Status statusCode = Status.INTERNAL_SERVER_ERROR; 
		WebApplicationContext context = (WebApplicationContext)servletContext.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		if(exception instanceof BaseException) {
			BaseException baseException = (BaseException)exception;

			message = context.getMessage(baseException.getCode(), baseException.getValues(), request.getLocale()); //国际化异常信息
			
			ErrorResponse response = new ErrorResponse(baseException.getResponseCode(), baseException.getCode(),message,exception);
			LOGGER.error(message,exception);
			return Response.ok(response, MediaType.APPLICATION_JSON).build();
		}
		else if(exception instanceof NotFoundException) {
			message = context.getMessage(ExceptionCode.NOT_FOUND, null, request.getLocale());
			ErrorResponse response = new ErrorResponse(ResponseCode.RESOURCE_UNFOUND, ExceptionCode.NOT_FOUND, message,exception);
			LOGGER.error(message, exception);
			return Response.ok(response, MediaType.APPLICATION_JSON).build();
		}
		else if(exception instanceof NotSupportedException){
			ErrorResponse response  = new ErrorResponse(ResponseCode.HTTP_MEDIA_TYPE_UNSUPPORTED);
			response.setResultMessage(exception.getMessage());
			LOGGER.error(exception.getMessage(), exception);
			return Response.ok(response, MediaType.APPLICATION_JSON).build();
		}
		else if(exception instanceof NotAllowedException){
			ErrorResponse response  = new ErrorResponse(ResponseCode.HTTP_METHOD_NOT_ALLOWED);
			response.setResultMessage(exception.getMessage());
			LOGGER.error(exception.getMessage(), exception);
			return Response.ok(response, MediaType.APPLICATION_JSON).build();
		}
		else {
			message = context.getMessage(ExceptionCode.INTERNAL_SERVER_ERROR, new Object[]{exception.getMessage()}, request.getLocale());
			ErrorResponse response = new ErrorResponse(ResponseCode.SYSTEM_ERROR, ExceptionCode.INTERNAL_SERVER_ERROR, message,exception);
			LOGGER.error(message, exception);
			return Response.ok(response, MediaType.APPLICATION_JSON).build();
		}
		//return Response.ok(message, MediaType.TEXT_PLAIN).status(statusCode).build();
	}
}
