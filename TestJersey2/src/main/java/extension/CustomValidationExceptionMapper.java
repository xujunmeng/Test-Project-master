package extension;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;


public class CustomValidationExceptionMapper implements ExceptionMapper<ValidationException> {

	@Context
	private HttpServletRequest request;
	
	@Context
	private ServletContext servletContext;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomValidationExceptionMapper.class);
	
	@Override
    public Response toResponse(final ValidationException exception) {

		String message = null;
		WebApplicationContext context = (WebApplicationContext)servletContext.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		
		if (exception instanceof ConstraintViolationException) {
			ConstraintViolationException cve = (ConstraintViolationException)exception;
			message = context.getMessage(ExceptionCode.VALIDATION_ERROR, new Object[]{cve.getConstraintViolations().iterator().next().getMessage()},request.getLocale());
			ErrorResponse response = new ErrorResponse(ResponseCode.VALIDATION_ERROR, ExceptionCode.VALIDATION_ERROR, message, exception);
			LOGGER.error(message, exception);
			return Response.ok(response, MediaType.APPLICATION_JSON).build();
		}
		else {
			message = context.getMessage(ExceptionCode.INTERNAL_SERVER_ERROR, new Object[]{exception.getMessage()}, request.getLocale());
			ErrorResponse response = new ErrorResponse(ResponseCode.SYSTEM_ERROR, ExceptionCode.VALIDATION_ERROR, message,exception);
			LOGGER.error(message, exception);
			return Response.ok(response, MediaType.APPLICATION_JSON).build();
		}
	}
}
