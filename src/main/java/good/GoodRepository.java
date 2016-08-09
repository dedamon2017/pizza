package good;

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
public class GoodRepository {

	private Map<Integer, Good> goodDB;
	private AtomicInteger idCounter = new AtomicInteger();

	@PostConstruct
	public void init() {
		goodDB = new ConcurrentHashMap<>();
		idCounter = new AtomicInteger(0);
	}

	public List<Good> findAll() {
		return new ArrayList<>(goodDB.values());
	}

	public boolean contains(Integer id) {
		return goodDB.containsKey(id);
	}

	public Optional<Good> find(Integer id) {
		return Optional.ofNullable(goodDB.get(id));
	}

	public void delete(Integer id) {
		goodDB.remove(id);
	}

	public void update(Good good) {
		Objects.requireNonNull(good);
		good.setId(idCounter.incrementAndGet());
		goodDB.put(good.getId(), good);
	}

}
