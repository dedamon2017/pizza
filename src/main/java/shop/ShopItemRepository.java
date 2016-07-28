package shop;

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
public class ShopItemRepository {

	private Map<Integer, ShopItem> shopItemDB;
	private AtomicInteger idCounter = new AtomicInteger();
	// private static final Logger LOGGER =
	// Logger.getLogger(ShopItemRepository.class.getName());

	@PostConstruct
	public void init() {
		shopItemDB = new ConcurrentHashMap<>();
		idCounter = new AtomicInteger(0);
	}

	public List<ShopItem> findAll() {
		return new ArrayList<>(shopItemDB.values());
	}

	public boolean contains(Integer id) {
		return shopItemDB.containsKey(id);
	}

	public Optional<ShopItem> find(Integer id) {
		/*
		 * ShopItem item = shopItemDB.get(id) != null ? shopItemDB.get(id) : new
		 * ShopItem(); return item;
		 */
		return Optional.ofNullable(shopItemDB.get(id));
	}

	public void delete(Integer id) {
		shopItemDB.remove(id);
	}

	public void update(ShopItem item) {
		Objects.requireNonNull(item);
		item.setId(idCounter.incrementAndGet());
		shopItemDB.put(item.getId(), item);
	}

}
