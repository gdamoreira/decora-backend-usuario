package br.com.damoreira.filter;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import br.com.damoreira.model.AuthAccessElement;
import br.com.damoreira.service.AuthService;

@Provider
public class AuthSecurityInterceptor implements ContainerRequestFilter {

	// 401 - Access denied
	private static final Response ACCESS_UNAUTHORIZED = Response.status(Response.Status.UNAUTHORIZED)
			.entity("Not authorized.").build();

	@EJB
	private AuthService authService;

	@Context
	private HttpServletRequest request;

	@Context
	private ResourceInfo resourceInfo;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		// Get AuthId and AuthToken from HTTP-Header.
		String authId = requestContext.getHeaderString(AuthAccessElement.PARAM_AUTH_ID);
		String authToken = requestContext.getHeaderString(AuthAccessElement.PARAM_AUTH_TOKEN);

		// Get method invoked.
		Method methodInvoked = resourceInfo.getResourceMethod();

		if (!methodInvoked.isAnnotationPresent(PermitAll.class)) {
			boolean authorized = authService.isAuthorized(authId, authToken);
			System.out.println("authorized: " + authorized + " - authId: " + authId + " - authToken: " + authToken);
			if (!authorized) {
				requestContext.abortWith(ACCESS_UNAUTHORIZED);
			}
		}
	}
}
