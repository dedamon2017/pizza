package order;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped

public class OrderRepository {
	private Map<Integer, Order> orderDB;
	private AtomicInteger idCounter;
	
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
		order.setCancel(false);
		order.setAddress("Test address");
		order.setDate(LocalDate.now());
		order.setEsimatedTime(LocalTime.now());
		order.setDeliveryTime(LocalTime.now().plusHours(2));
		order.setPhoneNumber("+380984408389");
		order.setCourier("Pavel");
		
		orderDB.put(order.getId(), order);
	}
}
