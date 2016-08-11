package shipment;

import java.util.Date;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import order.Order;
import order.OrderService;

@ApplicationScoped
public class ShipmentService {

	private ShipmentRepository shipmentRepository;
	private OrderService orderService;

	@Inject
	public void setShipmentRepository(ShipmentRepository shipmentRepository) {
		this.shipmentRepository = shipmentRepository;
	}

	@Inject
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

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
