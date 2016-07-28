package customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerRepository {
	private Map<Integer, Customer> customerDB;
	private AtomicInteger idCounter = new AtomicInteger();
	private static final Logger LOGGER = Logger.getLogger(CustomerRepository.class.getName());

	@PostConstruct
	public void init() {
		LOGGER.info("Create repository.");
		customerDB = new ConcurrentHashMap<Integer, Customer>();
		idCounter = new AtomicInteger(0);
	}

	public List<Customer> findAll() {
		LOGGER.info("Find all.");
		return new ArrayList<Customer>(customerDB.values());
	}

	public boolean contains(Integer id) {
		return customerDB.containsKey(id);
	}

	public Optional<Customer> find(Integer id) {
		LOGGER.info(String.format("Find '%d'.", id));
		return Optional.ofNullable(customerDB.get(id));
	}

	public void delete(Integer id) {
		LOGGER.info(String.format("Delete '%s'.", id));
		customerDB.remove(id);
	}

	public void update(Customer customer) {
		Objects.requireNonNull(customer);
		LOGGER.info(String.format("Update '%s'.", customer.getName()));
		customer.setId(idCounter.incrementAndGet());
		customerDB.put(customer.getId(), customer);
	}
}
