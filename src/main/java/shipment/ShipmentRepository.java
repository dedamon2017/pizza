package shipment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ShipmentRepository {
	private Map<Integer, Shipment> shipmentDB;
	private AtomicInteger idCounter;
	
	@Inject
	private ShipmentService shipmentService;
	
	@PostConstruct
	public void init() {
		shipmentDB = new ConcurrentHashMap<>();
		idCounter = new AtomicInteger(0);
	}

	public List<Shipment> findAll() {
		return new ArrayList<>(shipmentDB.values());
	}

	public boolean contains(Integer id) {
		return shipmentDB.containsKey(id);
	}

	public Optional<Shipment> find(Integer id) {
		return Optional.ofNullable(shipmentDB.get(id));
	}

	public void delete(Integer id) {
		shipmentDB.remove(id);
	}

	public void update(Shipment shipment) {
		Objects.requireNonNull(shipment);
		shipment.setId(idCounter.incrementAndGet());
		shipmentDB.put(shipment.getId(), shipment);
	}
	
	public void updatePut(Shipment shipment) {
		shipmentService.setDeliveryDateTime(shipment);
		shipmentDB.put(shipment.getId(), shipment);
	}

}
