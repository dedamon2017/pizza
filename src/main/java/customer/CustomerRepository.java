package customer;

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
public class CustomerRepository {
	private Map<Integer, Customer> customerDB;
	private AtomicInteger idCounter = new AtomicInteger();

	@PostConstruct
	public void init() {
		customerDB = new ConcurrentHashMap<>();
		idCounter = new AtomicInteger(0);
	}

	public List<Customer> findAll() {
		return new ArrayList<>(customerDB.values());
	}

	public boolean contains(Integer id) {
		return customerDB.containsKey(id);
	}

	public Optional<Customer> find(Integer id) {
		return Optional.ofNullable(customerDB.get(id));
	}

	public void delete(Integer id) {
		customerDB.remove(id);
	}

	public void createId(Customer customer) {
		customer.setId(idCounter.incrementAndGet());
	}
	
	public void updateToMap(Customer customer) {
		customerDB.put(customer.getId(), customer);
	}
	
}
