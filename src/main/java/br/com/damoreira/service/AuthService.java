package br.com.damoreira.service;

import br.com.damoreira.model.AuthAccessElement;
import br.com.damoreira.model.AuthLoginElement;

public interface AuthService {

	AuthAccessElement login(AuthLoginElement loginElement);
	
	boolean isAuthorized(String authId, String authToken);
	
}
