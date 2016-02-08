package br.com.damoreira.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.damoreira.model.User;
import br.com.damoreira.service.UserService;
import br.com.damoreira.util.SearchResult;
import br.com.damoreira.util.SortParserUtils;

/**
 * This class represents the REST user API.
 */
@Path("/usuario")
public class UserREST {

	@Inject
	private UserService userService;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response list(@QueryParam("skip") Integer skip, @QueryParam("limit") Integer limit,
			@QueryParam("sort") String sort) {
		SearchResult<User> output = userService.listUsers(skip, limit, SortParserUtils.getField(sort),
				SortParserUtils.getDirection(sort));
		List<User> results = output.getResults();
		for (User user : results) {
			user.setPassword(null);
		}
		return Response.status(200).entity(output).build();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{id}")
	public Response get(@PathParam("id") String id) {
		User output = userService.findUser(id);
		output.setPassword(null);
		return Response.status(200).entity(output).build();
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response post(User user) {
		User output = userService.saveUser(user);
		output.setPassword(null);
		return Response.status(200).entity(output).build();
	}

	@PUT
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response put(@PathParam("id") String id, User user) {
		User output = userService.updateUser(id, user);
		output.setPassword(null);
		return Response.status(200).entity(output).build();
	}

	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") String id) {
		userService.removeUser(id);
		return Response.status(204).build();
	}

}
