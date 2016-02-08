package br.com.damoreira.rest;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import br.com.damoreira.model.AuthAccessElement;
import br.com.damoreira.model.AuthLoginElement;
import br.com.damoreira.service.AuthService;

/**
 * This class represents the REST login API.
 */
@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginREST {

	@EJB
	private AuthService authService;

	@POST
	@Path("/login")
	@PermitAll
	public AuthAccessElement login(@Context HttpServletRequest request, AuthLoginElement loginElement) {
		return authService.login(loginElement);
	}

	@POST
	@Path("/restore")
	@PermitAll
	public AuthAccessElement restore(@Context HttpServletRequest request, AuthAccessElement accessElement) {
		boolean authorized = authService.isAuthorized(accessElement.getAuthId(), accessElement.getAuthToken());
		return authorized ? accessElement : null;
	}

}
