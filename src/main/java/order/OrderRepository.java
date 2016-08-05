package order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class OrderRepository {
	private Map<Integer, Order> orderDB;
	private AtomicInteger idCounter;

	@Inject
	private OrderService orderservice;

	@PostConstruct
	public void init() {
		orderDB = new ConcurrentHashMap<>();
		idCounter = new AtomicInteger(0);
	}

	public List<Order> findAll() {
		return new ArrayList<>(orderDB.values());
	}

	public boolean contains(Integer id) {
		return orderDB.containsKey(id);
	}

	public Optional<Order> find(Integer id) {
		return Optional.ofNullable(orderDB.get(id));
	}

	public void delete(Integer id) {
		orderDB.remove(id);
	}

	public void update(Order order) {
		Objects.requireNonNull(order);
		order.setId(idCounter.incrementAndGet());
		orderservice.searchShopItemById(order);
		orderservice.sumOfOrder(order);
		orderservice.searchCustomerById(order);
		orderservice.setCommonInfo(order);
		updateOrderToMap(order);
		orderservice.createShipment(order);
	}
	public void updateOrderToMap(Order order) {
		orderDB.put(order.getId(), order);
	}
}
