package order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import customer.CustomerService;
import shipment.ShipmentService;
import shop.ShopService;

@ApplicationScoped
public class OrderService {
	@Inject
	private ShopService shopService;
	@Inject
	private CustomerService customerService;
	@Inject
	private ShipmentService shipmentService;

	public void searchShopItemById(Order order) {
		ArrayList<OrderLineItem> lineItems = getOrderLineItems(order);
		lineItems.forEach(item -> {
			int itemId = item.getShopItemId();
			if (shopService.contains(itemId)) {
				item.setShopItemName(shopService.getShopItemName(itemId));
				item.setShopItemPrice(shopService.getShopItemPrice(itemId));
			} else {
				throw new NotFoundException("ShopItem not found");
			}
		});
	}

	public void sumOfOrder(Order order) {
		ArrayList<OrderLineItem> lineItems = getOrderLineItems(order);
		BigDecimal sum = lineItems.stream().map(OrderLineItem::getShopItemPrice).reduce(BigDecimal::add).get();
		order.setSum(sum);
	}

	public void searchCustomerById(Order order) {
		int customerId = order.getId();
		if (customerService.contains(customerId)) {
			order.setCustomerName(customerService.getCustomerName(customerId));
			order.setAddress(customerService.getCustomerAddress(customerId));
			order.setPhoneNumber(customerService.getCustomerPhonenumber(customerId));
		} else {
			throw new NotFoundException("Customer not found");
		}
	}

	public void setCommonInfo(Order order) {
		order.setCancel(false);
		order.setDate(LocalDate.now());
		order.setEsimatedTime(LocalTime.now());
		order.setDeliveryTime(LocalTime.now().plusHours(2));
		order.setCourier("Pavel");
	}

	public void createShipment(Order order) {
		shipmentService.createShipment(order);
	}

	private ArrayList<OrderLineItem> getOrderLineItems(Order order) {
		return new ArrayList<>(order.getLineItemList());
	}
}
