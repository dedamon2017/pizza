package domain.Customer;

import java.io.InputStream;
import java.util.List;


import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/customers")
public class CustomerResource {
	
	
	@Inject
	private CustomerRepository customRepository;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> findAll() {
		return customRepository.getAll();
	}
	
	@POST
	@Consumes("application/xml")
	public Response createCustomer(InputStream is) {
		
		return null;
		
	}
	
}
