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

	private ShipmentRepository shipmentRepository;

	@Inject
	public void setShipmentRepository(ShipmentRepository shipmentRepository) {
		this.shipmentRepository = shipmentRepository;
	}

	@GET
	public List<Shipment> findAll() {
		return shipmentRepository.findAll();
	}

	@GET
	@Path("{id}")
	public Shipment findOne(@PathParam("id") Integer id) {
		return shipmentRepository.find(id).orElseThrow(() -> new NotFoundException("Shipment not found."));
	}

	@PUT
	@Path("{id}")
	public void update(@PathParam("id") Integer id, Shipment shipment) {
		shipmentRepository.updatePut(shipment);
	}
}
