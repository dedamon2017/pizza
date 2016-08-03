package shop;

import java.math.BigDecimal;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ShopService {

	@Inject
	private ShopItemRepository shopItemRepository;

	public boolean contains(Integer id) {
		return shopItemRepository.contains(id);
	}

	public String getShopItemName(Integer id) {
		return shopItemRepository.find(id).get().getName();
	}

	public BigDecimal getShopItemPrice(Integer id) {
		return shopItemRepository.find(id).get().getPrice();
	}
}
