package security;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/authentication")
public class AuthenticationResource {
	private static final Logger LOGGER = Logger.getLogger(AuthenticationResource.class.getName());
	@Inject
	private Utility utility;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response authenticateUser(Credentials credentials) {
		String userName = credentials.getUserName();
		String password = credentials.getPassword();
		try {
			authenticate(userName, password);
			String token = issueToken(userName);
			addTokenToUtility(userName, token);
			return Response.ok(token).build();
		} catch (Exception e) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}

	private void authenticate(String userName, String password) throws Exception {
		if (!userName.equals(utility.getAdminUserName()) || !password.equals(utility.getAdminPassword())) {
			throw new Exception();
		}
	}

	private String issueToken(String userName) {
		return userName + "ADMIN";
	}

	private void addTokenToUtility(String userName, String token) {
		utility.addToken(userName, token);
		LOGGER.info(String.format("Added token is %s", utility.getTokenDB().get("admin")));
	}
}
