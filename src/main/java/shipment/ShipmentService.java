package shipment;


import java.util.Date;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import order.Order;
import order.OrderService;

@ApplicationScoped
public class ShipmentService {

	@Inject
	private ShipmentRepository shipmentRepository;
	@Inject
	private OrderService orderService;
	
	public void createShipment(Order order) {
		Shipment shipment = new Shipment();
		shipment.setAddress(order.getAddress());
		shipment.setRecievedDate(order.getRecievedDate());
		shipment.setOrderId(order.getId());
		shipmentRepository.update(shipment);
	}
	
	public void setOrderDeliveredTime(Shipment shipment) {
		int orderId = shipment.getOrderId();
		Date date = shipment.getDeliveredDate();
		orderService.setDeliveredTime(orderId, date);
	}
	
	
}
