package security;

import java.util.UUID;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/authentication")
public class AuthenticationResource {
	
	
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response authenticateUser(Credentials credentials) {
		String userName = credentials.getUserName();
		String password = credentials.getPassword();
		try {
			authenticate(userName, password);
			String token = issueToken(userName);
			return Response.ok(token).build();
		}
		catch (Exception e) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}	
	}
	
	
	private void authenticate(String userName, String password) throws Exception {
		//Utility utility = new Utility();
		
		if (!userName.equals("admin") || !password.equals("qwerty")) {
		//if (!userName.equals(utility.getAdmin()) || !password.equals(utility.getPass())) {
			throw new Exception();
		}
	}
	
	private String issueToken(String userName) {
        
		//return userName + UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
		return userName + "ADMIN";
	}
}
