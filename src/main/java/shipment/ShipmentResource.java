package shipment;

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

@Path("shipments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ShipmentResource {

	private ShipmentService shipmentService;

	@Inject
	public void setShipmentRepository(ShipmentService shipmentService) {
		this.shipmentService = shipmentService;
	}

	@GET
	public List<Shipment> findAll() {
		return shipmentService.findAll();
	}

	@GET
	@Path("{id}")
	public Shipment findOne(@PathParam("id") Integer id) {
		return shipmentService.find(id).orElseThrow(() -> new NotFoundException("Shipment not found."));
	}

	@PUT
	@Path("{id}")
	public void update(@PathParam("id") Integer id, Shipment shipment) {
		shipmentService.updateDelivered(shipment);
	}
}
