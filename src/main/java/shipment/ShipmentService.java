package shipment;

import java.time.LocalDateTime;

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
		shipment.setRecievedDateTime(order.getDate().atTime(order.getEsimatedTime()));
		shipment.setOrderId(order.getId());
		shipmentRepository.update(shipment);
	}
	
	public void setDeliveryDateTime(Shipment shipment) {
		shipment.setDeliveredDateTime(LocalDateTime.now());
	}
}
