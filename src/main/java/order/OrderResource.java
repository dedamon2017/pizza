package order;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Path("orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

	private OrderService orderService;

	@Inject
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	@GET
	public List<Order> findAll() {
		return orderService.findAll();
	}

	@GET
	@Path("{id}")
	public Order findOne(@PathParam("id") Integer id) {
		return orderService.find(id).orElseThrow(() -> new NotFoundException("Order not found."));
	}

	@POST
	public Response create(Order order) {
		orderService.createNew(order);
		URI location = UriBuilder.fromResource(OrderResource.class).path("{id}").build(order.getId());
		return Response.created(location).build();
	}

}
