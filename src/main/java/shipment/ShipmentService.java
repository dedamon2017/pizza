package shipment;


import java.util.Date;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import order.Order;

@ApplicationScoped
public class ShipmentService {

	@Inject
	private ShipmentRepository shipmentRepository;

	public void createShipment(Order order) {
		Shipment shipment = new Shipment();
		shipment.setAddress(order.getAddress());
		shipment.setRecievedDate(order.getRecievedDate());
		shipment.setOrderId(order.getId());
		shipmentRepository.update(shipment);
	}
	
	public void setDeliveryDate(Shipment shipment) {
		shipment.setDeliveredDate(new Date());
	}
}
