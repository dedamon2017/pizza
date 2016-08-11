package good;

import java.math.BigDecimal;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class GoodService {

	
	private GoodRepository goodRepository;
	
	
	@Inject
	public void setGoodRepository(GoodRepository goodRepository) {
		this.goodRepository = goodRepository;
	}

	public boolean contains(Integer id) {
		return goodRepository.contains(id);
	}

	public String getGoodName(Integer id) {
		return goodRepository.find(id).get().getName();
	}

	public BigDecimal getGoodPrice(Integer id) {
		return goodRepository.find(id).get().getPrice();
	}
}
