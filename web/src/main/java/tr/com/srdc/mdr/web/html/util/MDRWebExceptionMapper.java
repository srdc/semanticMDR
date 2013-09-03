package tr.com.srdc.mdr.web.html.util;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class MDRWebExceptionMapper implements ExceptionMapper<RuntimeException> {

	private static final Logger logger = LoggerFactory
			.getLogger(MDRWebExceptionMapper.class);

	@Override
	public Response toResponse(RuntimeException runtimeException) {
		// This is to map run time exceptions to HTTP error status
		// Normally with runtime exceptions, users see full stack trace on web
		// page
		// With this mapper, stack trace is given as log message and client gets
		// a valid
		// HTTP response with valid error code
		logger.error(
				"Runtime exception caught, HTTP Response with Status NoContent ",
				runtimeException);
		return Response.noContent().build();
	}
}