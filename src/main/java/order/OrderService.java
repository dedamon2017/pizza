package order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import customer.CustomerService;
import good.GoodService;
import shipment.ShipmentService;

@ApplicationScoped
public class OrderService {
	private static final Logger LOGGER = Logger.getLogger(OrderService.class.getName());
	private static final int TIME_OF_DELIVERY = 2;

	@Inject
	private OrderRepository orderRepository;
	@Inject
	private GoodService goodService;
	@Inject
	private CustomerService customerService;
	@Inject
	private ShipmentService shipmentService;

	public void searchShopItemById(Order order) {
		ArrayList<OrderLineItem> lineItems = getOrderLineItems(order);
		lineItems.forEach(item -> {
			int itemId = item.getShopItemId();
			if (goodService.contains(itemId)) {
				item.setShopItemName(goodService.getGoodName(itemId));
				item.setShopItemPrice(goodService.getGoodPrice(itemId));
			} else {
				throw new NotFoundException("ShopItem not found.");
			}
		});
	}

	public void sumOfOrder(Order order) {
		ArrayList<OrderLineItem> lineItems = getOrderLineItems(order);
		BigDecimal sum = lineItems.stream().map(OrderLineItem::getShopItemPrice).reduce(BigDecimal::add).get();
		order.setSum(sum);
	}

	public void searchCustomerById(Order order) {

		int customerId = order.getCustomerId();
		LOGGER.info("Customer recieved number" + Integer.toString(customerId));
		if (customerService.contains(customerId)) {
			order.setCustomerName(customerService.getCustomerName(customerId));
			order.setAddress(customerService.getCustomerAddress(customerId));
			order.setPhoneNumber(customerService.getCustomerPhonenumber(customerId));
		} else {
			throw new NotFoundException("Customer not found.");
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
		shipmentService.createShipment(order);
	}

	private ArrayList<OrderLineItem> getOrderLineItems(Order order) {
		return new ArrayList<>(order.getLineItemList());
	}

	public void setDeliveredTime(int orderId, Date date) {
		Order order = orderRepository.find(orderId).orElseThrow(() -> new NotFoundException("Order not found."));
		order.setDeliveredTime(date);
		orderRepository.updateOrderToMap(order);
		LOGGER.info("Order delivered time" + order.getDeliveredTime().toString());
	}
}
