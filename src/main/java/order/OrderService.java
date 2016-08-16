package order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import customer.CustomerService;
import good.GoodService;
import shipment.ShipmentService;
import shop.AppException;

@ApplicationScoped
public class OrderService {
	private static final Logger LOGGER = Logger.getLogger(OrderService.class.getName());
	private static final int TIME_OF_DELIVERY = 2;
	private OrderRepository orderRepository;
	private GoodService goodService;
	private CustomerService customerService;
	private ShipmentService shipmentService;

	@Inject
	public void setOrderRepository(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Inject
	public void setGoodService(GoodService goodService) {
		this.goodService = goodService;
	}

	@Inject
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@Inject
	public void setShipmentService(ShipmentService shipmentService) {
		this.shipmentService = shipmentService;
	}

	public void searchGoodById(Order order) {
		ArrayList<OrderLineItem> lineItems = getOrderLineItems(order);
		lineItems.forEach(item -> {
			int itemId = item.getGoodId();
			if (goodService.contains(itemId)) {
				item.setGoodName(goodService.getGoodName(itemId));
				item.setGoodPrice(goodService.getGoodPrice(itemId));
			} else {
				throw new AppException("ShopItem not found.");
			}
		});
	}

	public void sumOfOrder(Order order) {
		ArrayList<OrderLineItem> lineItems = getOrderLineItems(order);
		Optional<BigDecimal> sum = lineItems.stream().map(OrderLineItem::getGoodPrice).reduce(BigDecimal::add);
		order.setSum(sum.orElseGet(() -> new BigDecimal(0)));
	}

	public void searchCustomerById(Order order) {

		int customerId = order.getCustomerId();
		LOGGER.info("Customer recieved number" + Integer.toString(customerId));
		if (customerService.contains(customerId)) {
			order.setCustomerName(customerService.getCustomerName(customerId));
			order.setAddress(customerService.getCustomerAddress(customerId));
			order.setPhoneNumber(customerService.getCustomerPhonenumber(customerId));
		} else {
			throw new AppException("Customer not found.");
		}
	}

	public Date getDeliveryTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR, TIME_OF_DELIVERY);
		return calendar.getTime();

	}

	public void setCommonInfo(Order order) {
		order.setCancel(false);
		order.setRecievedDate(new Date());
		order.setDeliveryTime(getDeliveryTime());
	}

	public void createShipment(Order order) {
		shipmentService.createNew(order);
	}

	private ArrayList<OrderLineItem> getOrderLineItems(Order order) {
		return new ArrayList<>(order.getLineItemList());
	}

	public void setDeliveredTime(int orderId, Date date) {
		Order order = orderRepository.find(orderId).orElseThrow(() -> new AppException("Order not found."));
		order.setDeliveredTime(date);
		orderRepository.updateToMap(order);
		LOGGER.info("Order delivered time" + order.getDeliveredTime().toString());
	}

	public void createNew(Order order) {
		Objects.requireNonNull(order);
		orderRepository.createId(order);
		searchGoodById(order);
		sumOfOrder(order);
		searchCustomerById(order);
		setCommonInfo(order);
		orderRepository.updateToMap(order);
		createShipment(order);	
	}

	public Optional<Order> find(Integer id) {
		return orderRepository.find(id);
	}

	public List<Order> findAll() {
		return orderRepository.findAll();
	}	
}
