package good;

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

@Path("goods")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GoodResource {

	private GoodService goodService;

	@Inject
	public void setGoodService(GoodService goodService) {
		this.goodService = goodService;
	}

	@GET
	public List<Good> findAll() {
		return goodService.findAll();
	}

	@GET
	@Path("{id}")
	public Good findOne(@PathParam("id") Integer id) {
		return goodService.find(id).orElseThrow(() -> new NotFoundException("Good not found."));
	}

	@POST
	public Response create(Good good) {
		if (Objects.isNull(good.getName()) || Objects.isNull(good.getId())) {
			throw new BadRequestException("Good should have name and id.");
		}
		if (goodService.contains(good.getId())) {
			throw new ForbiddenException("Good already exists.");
		}
		goodService.update(good);
		URI location = UriBuilder.fromResource(GoodResource.class).path("{id}").build(good.getId());
		return Response.created(location).build();
	}

}
