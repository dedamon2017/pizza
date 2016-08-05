package shop;

import java.net.URI;

import javax.ws.rs.HEAD;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;

import customer.CustomerResource;
import order.OrderResource;
import shipment.ShipmentResource;

@Path("/")
public class ShopApplicationHeader {
	@HEAD
	public Response head(@Context UriInfo uriInfo) {
		UriBuilder absolute = uriInfo.getBaseUriBuilder();
		URI customerUrl = absolute.clone().path(CustomerResource.class).build();
		URI orderUrl = absolute.clone().path(OrderResource.class).build();
		URI shipmentUrl = absolute.clone().path(ShipmentResource.class).build();
		URI shopItemUrl = absolute.clone().path(ShopItemResource.class).build();
		ResponseBuilder builder = Response.ok();
		Link customers = Link.fromUri(customerUrl).rel("customers").type("aplication/json").build();
		Link orders = Link.fromUri(orderUrl).rel("orders").type("aplication/json").build();
		Link shipments = Link.fromUri(shipmentUrl).rel("shipments").type("aplication/json").build();
		Link shopItems = Link.fromUri(shopItemUrl).rel("shopitems").type("aplication/json").build();
		builder.links(customers, orders, shipments, shopItems);
		return builder.build();
	}
}
