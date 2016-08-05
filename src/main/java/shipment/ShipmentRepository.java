package shipment;

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
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import order.OrderService;

@ApplicationScoped
public class ShipmentRepository {
	private static final Logger LOGGER = Logger.getLogger(ShipmentRepository.class.getName());
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

	public void updatePut(Shipment update) {
		// shipmentService.setDeliveryDate(shipment);
		Shipment shipment = shipmentDB.get(update.getId());
		if (shipment == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		shipmentDB.put(shipment.getId(), update);
		LOGGER.info("Shipment map is " + shipmentDB);
		LOGGER.info("Update is " + update);
		shipmentService.setOrderDeliveredTime(update);
	}

}
