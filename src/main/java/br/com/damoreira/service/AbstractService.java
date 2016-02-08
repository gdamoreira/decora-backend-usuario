package br.com.damoreira.service;

import javax.inject.Inject;

import org.jongo.Jongo;

public abstract class AbstractService {

	@Inject
	protected Jongo jongo;
	
	protected String createSortQuery(String field, Integer direction) {
		return String.format("{'%s':%s}", field, String.valueOf(direction));
	}
	
}
