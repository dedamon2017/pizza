package shipment;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import order.Order;
import order.OrderService;
import shop.AppException;

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

	public void createNew(Order order) {
		Shipment shipment = new Shipment();
		shipment.setAddress(order.getAddress());
		shipment.setRecievedDate(order.getRecievedDate());
		shipment.setOrderId(order.getId());
		shipmentRepository.createId(shipment);
		shipmentRepository.updateToMap(shipment);
	}

	public void setOrderDeliveredTime(Shipment shipment) {
		int orderId = shipment.getOrderId();
		Date date = shipment.getDeliveredDate();
		orderService.setDeliveredTime(orderId, date);
	}

	public List<Shipment> findAll() {
		return shipmentRepository.findAll();
	}

	public Optional<Shipment> find(Integer id) {
		return shipmentRepository.find(id);
	}

	public void updateDelivered(Shipment updateShipment) {
		shipmentRepository.find(updateShipment.getId()).orElseThrow(() -> new AppException("Could not find shipment."));
		shipmentRepository.updateToMap(updateShipment);
		setOrderDeliveredTime(updateShipment);

	}

}
