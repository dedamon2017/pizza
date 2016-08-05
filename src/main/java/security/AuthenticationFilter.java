package security;

import java.io.IOException;
import java.util.logging.Logger;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

	private static final Logger LOGGER = Logger.getLogger(Utility.class.getName());
	@Inject
	private Utility utility;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		if (authorizationHeader == null || !authorizationHeader.startsWith("admin")) {
			throw new NotAuthorizedException("Authorization header must be provided");
		}
		String token = authorizationHeader;
		LOGGER.info(String.format("Authorization header token is %s", token));
		try {
			validateToken(token);
		} catch (Exception e) {
			LOGGER.info("Failed to autorizate!");
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
		}
	}

	public void validateToken(String token) throws Exception {
		// if (!token.equals("ADMIN")) {
		String takenToken = (String) utility.getTokenDB().get("admin");
		LOGGER.info(String.format("taken token is %s", takenToken));
		if (!token.equals(takenToken)) {
			throw new Exception();
		}
	}
}
