package shop;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Path("shopitems")
public class ShopItemResource {

	@Inject
	private ShopItemRepository itemRepository;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ShopItem> findAll() {
		return itemRepository.findAll();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ShopItem findOne(@PathParam("id") Integer id) {
		return itemRepository.find(id).orElseThrow(() -> new NotFoundException("Customer not found."));
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(ShopItem shopItem) {
		if (Objects.isNull(shopItem.getName()) || Objects.isNull(shopItem.getId())) {
			throw new BadRequestException("ShopItem should have name and id.");
		}
		if (itemRepository.contains(shopItem.getId())) {
			throw new ForbiddenException("ShopItem already exists.");
		}
		itemRepository.update(shopItem);
		URI location = UriBuilder.fromResource(ShopItemResource.class).path("{id}").build(shopItem.getId());
		return Response.created(location).build();
	}

}
