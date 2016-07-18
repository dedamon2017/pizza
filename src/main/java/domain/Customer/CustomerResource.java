package domain.Customer;


import java.net.URI;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Path("customers")
public class CustomerResource {
	
	@Inject
	private CustomerRepository customerRepository;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Customer customer) {
        if (Objects.isNull(customer.getName()) || Objects.isNull(customer.getId())) {
            throw new BadRequestException("Customer should have name and id.");
        }
        if (customerRepository.contains(customer.getId())) {
            throw new ForbiddenException("Customer already exists.");
        }
        customerRepository.update(customer);
        URI location = UriBuilder.fromResource(CustomerResource.class).path("{id}").build(customer.getId());
        return Response.created(location).build();
    }
	
	@GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer findOne(@PathParam("id") Integer id) {
        return customerRepository.find(id).orElseThrow(() -> new NotFoundException("Customer not found."));
    }
	
	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") Integer id) {
	   customerRepository.delete(id);
	}
	
}
