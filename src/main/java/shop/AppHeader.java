package shop;

import java.net.URI;

import javax.ws.rs.HEAD;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import customer.CustomerResource;
import good.GoodResource;
import order.OrderResource;
import shipment.ShipmentResource;

@Path("/")
public class AppHeader {

	@HEAD
	public Response head(@Context UriInfo uriInfo) {
		UriBuilder absolute = uriInfo.getBaseUriBuilder();
		return Response.ok().links(getLink(CustomerResource.class, absolute), getLink(OrderResource.class, absolute),
				getLink(ShipmentResource.class, absolute), getLink(GoodResource.class, absolute)).build();
	}

	public Link getLink(Class<?> clazz, UriBuilder builder) {
		URI clazzUri = builder.clone().path(clazz).build();
		return Link.fromUri(clazzUri).rel(clazz.getAnnotation(Path.class).value()).type(MediaType.APPLICATION_JSON)
				.build();
	}
}
