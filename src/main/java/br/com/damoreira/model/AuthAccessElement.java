package br.com.damoreira.model;

import java.io.Serializable;

public class AuthAccessElement implements Serializable {

	private static final long serialVersionUID = 6237853957986300363L;

	public static final String PARAM_AUTH_ID = "auth-id";
	public static final String PARAM_AUTH_TOKEN = "auth-token";

	private String authId;
	private String authToken;

	public AuthAccessElement() {
	}

	public AuthAccessElement(String authId, String authToken) {
		this.authId = authId;
		this.authToken = authToken;
	}

	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authId == null) ? 0 : authId.hashCode());
		result = prime * result + ((authToken == null) ? 0 : authToken.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthAccessElement other = (AuthAccessElement) obj;
		if (authId == null) {
			if (other.authId != null)
				return false;
		} else if (!authId.equals(other.authId))
			return false;
		if (authToken == null) {
			if (other.authToken != null)
				return false;
		} else if (!authToken.equals(other.authToken))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AuthAccessElement [authId=" + authId + ", authToken=" + authToken + "]";
	}

}
