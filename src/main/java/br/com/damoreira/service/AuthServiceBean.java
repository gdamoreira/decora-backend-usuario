package br.com.damoreira.service;

import java.util.UUID;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.damoreira.model.AuthAccessElement;
import br.com.damoreira.model.AuthLoginElement;
import br.com.damoreira.model.User;

@Stateless(name = "AuthService")
public class AuthServiceBean implements AuthService {

	@Inject
	private UserService userService;

	@Override
	public AuthAccessElement login(AuthLoginElement loginElement) {
		User user = userService.findByLoginAndPassword(loginElement.getLogin(), loginElement.getPassword());
		if (user != null) {
			System.out.println("User found, returning AuthToken!");
			user.setAuthToken(UUID.randomUUID().toString());
			userService.saveUser(user);
			return new AuthAccessElement(loginElement.getLogin(), user.getAuthToken());
		}
		System.out.println("Don't found user!");
		return null;
	}

	public boolean isAuthorized(String authId, String authToken) {
		User user = userService.findByLoginAndAuthToken(authId, authToken);
		return user != null;
	}

}