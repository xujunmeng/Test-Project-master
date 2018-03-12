package extension;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;


public class ApplicationExtension extends ResourceConfig {
	private static Logger LOGGER = LoggerFactory.getLogger(AhsLoggingFilter.class.getName());


	public ApplicationExtension() {

		SLF4JBridgeHandler.removeHandlersForRootLogger();
		SLF4JBridgeHandler.install();
		
		//get all resources and register them

		super.register(new AhsLoggingFilter(LOGGER, true));
		super.register(CustomValidationExceptionMapper.class);
		super.register(ExceptionMapperSupport.class);
		super.register(org.glassfish.jersey.server.validation.ValidationFeature.class);
		super.property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
		super.register(new JsonParamValueFactoryProvider.JsonParamInjectionResolverBinder());
		super.register(JsonParamValueFactoryProvider.class);
		super.register(JsonMessageBodyWriter.class);
		super.register(ExceptionMapperSupport.class);
	}
}

