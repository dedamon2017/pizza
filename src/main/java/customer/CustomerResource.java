package customer;

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

import security.Secured;

@Path("customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

	private CustomerService customerService;

	@Inject
	public void setCustomerResource(CustomerService customerResource) {
		this.customerService = customerResource;
	}

	@GET
	public List<Customer> findAll() {
		return customerService.findAll();
	}

	@POST
	public Response create(Customer customer) {
		if (Objects.isNull(customer.getName()) || Objects.isNull(customer.getId())) {
			throw new BadRequestException("Customer should have name and id.");
		}
		if (customerService.contains(customer.getId())) {
			throw new ForbiddenException("Customer already exists.");
		}
		customerService.createNew(customer);
		URI location = UriBuilder.fromResource(CustomerResource.class).path("{id}").build(customer.getId());
		return Response.created(location).build();
	}

	@GET
	@Secured
	@Path("{id}")
	public Customer findOne(@PathParam("id") Integer id) {
		return customerService.find(id).orElseThrow(() -> new NotFoundException("Customer not found."));
	}

	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") Integer id) {
		customerService.delete(id);
	}

}
