package customer;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import shop.AppException;

@ApplicationScoped
public class CustomerService {
	@Inject
	private CustomerRepository customerRepository;

	public boolean contains(Integer id) {
		return customerRepository.contains(id);
	}
	
	public Customer getCustomer(Integer id) {
		return find(id).orElseThrow(() -> new AppException("Could not find customer"));
	}
	
	/*public String getCustomerName(Integer id) {
		return customerRepository.find(id).get().getName();
	}

	public String getCustomerAddress(Integer id) {
		return customerRepository.find(id).get().getAddress();
	}

	public String getCustomerPhonenumber(Integer id) {
		return customerRepository.find(id).get().getPhoneNumber();
	}*/

	public void delete(Integer id) {
		customerRepository.delete(id);
	}

	public Optional<Customer> find(Integer id) {
		return customerRepository.find(id);
	}

	public void createNew(Customer customer) {
		Objects.requireNonNull(customer);
		customerRepository.createId(customer);
		customerRepository.updateToMap(customer);
	}

	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

}
