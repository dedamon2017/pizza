package customer;

import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CustomerService {
	@Inject
	private CustomerRepository customerRepository;

	private static final Logger LOGGER = Logger.getLogger(CustomerService.class.getName());

	public boolean contains(Integer id) {
		LOGGER.info(customerRepository.findAll().toString());
		return customerRepository.contains(id);
	}

	public String getCustomerName(Integer id) {
		LOGGER.info(customerRepository.findAll().toString());
		return customerRepository.find(id).get().getName();
	}

	public String getCustomerAddress(Integer id) {
		LOGGER.info(customerRepository.findAll().toString());
		return customerRepository.find(id).get().getAddress();
	}

	public String getCustomerPhonenumber(Integer id) {
		LOGGER.info(customerRepository.findAll().toString());
		return customerRepository.find(id).get().getPhoneNumber();
	}

}
