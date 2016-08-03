package shipment;

import java.net.URI;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Path("shipments")
public class ShipmentResource {
	@Inject
	private ShipmentRepository shipmentRepository;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Shipment> findAll() {
		return shipmentRepository.findAll();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Shipment findOne(@PathParam("id") Integer id) {
		return shipmentRepository.find(id).orElseThrow(() -> new NotFoundException("Shipment not found."));
	}
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") Integer id, Shipment shipment) {
		shipmentRepository.updatePut(shipment);
		URI location = UriBuilder.fromResource(ShipmentResource.class).path("{id}").build(shipment.getId());
		return Response.created(location).build();
		
	}
	/*
	 * @POST
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON) public Response create(Shipment
	 * shipment) { shipmentRepository.update(shipment); URI location =
	 * UriBuilder.fromResource(ShipmentResource.class).path("{id}").build(
	 * shipment.getId()); return Response.created(location).build(); }
	 */
	
	
}
