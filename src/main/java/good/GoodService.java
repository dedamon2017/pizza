package good;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import shop.AppException;

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

	public Good getGood(Integer id) {
		return find(id).orElseThrow(() -> new AppException("Could not find good."));
	}
	
	/*public String getGoodName(Integer id) {
		// return goodRepository.find(id).get().getName();
		return goodRepository.find(id).orElseThrow(() -> new AppException("Could not find good.")).getName();
	}

	public BigDecimal getGoodPrice(Integer id) {
		return goodRepository.find(id).orElseThrow(() -> new AppException("Could not find good.")).getPrice();
	}*/

	public List<Good> findAll() {
		return goodRepository.findAll();
	}

	public Optional<Good> find(Integer id) {
		return goodRepository.find(id);
	}

	public void update(Good good) {
		Objects.requireNonNull(good);
		goodRepository.createId(good);
		goodRepository.updateToMap(good);
	}
}
