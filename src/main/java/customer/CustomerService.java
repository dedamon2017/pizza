package customer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CustomerService {
	@Inject
	private CustomerRepository customerRepository;

	public boolean contains(Integer id) {
		return customerRepository.contains(id);
	}
	
	public String getCustomerName(Integer id) {
		return customerRepository.find(id).get().getName();
	}

	public String getCustomerAddress(Integer id) {
		return customerRepository.find(id).get().getAddress();
	}

	public String getCustomerPhonenumber(Integer id) {
		return customerRepository.find(id).get().getPhoneNumber();
	}

	
}
