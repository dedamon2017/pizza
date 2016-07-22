package order;

import javax.inject.Inject;
import javax.ws.rs.Path;

@Path("orders")
public class OrderResource {
	
	@Inject
	private OrderRepository orderRepository;
	
	
	
}
