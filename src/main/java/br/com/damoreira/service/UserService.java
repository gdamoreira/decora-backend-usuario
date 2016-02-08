package br.com.damoreira.service;

import java.util.List;

import javax.ejb.Local;
import javax.inject.Inject;

import org.jongo.Find;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;
import org.jongo.Oid;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import br.com.damoreira.model.User;
import br.com.damoreira.util.SearchResult;

@Local
public class UserService extends AbstractService {

	@Inject
	@UserQualifier
	private MongoCollection userCollection;

	public SearchResult<User> listUsers(Integer skip, Integer limit, String field, Integer direction) {
		Find find = userCollection.find().sort(createSortQuery(field, direction));
		if (skip != null) {
			find.skip(skip);
		}
		if (limit != null) {
			find.limit(limit);
		}
		MongoCursor<User> users = find.as(User.class);
		List<User> results = Lists.newArrayList(users.iterator());
		return new SearchResult<>(results.size(), results);
	}

	public User findUser(String id) {
		return userCollection.findOne(Oid.withOid(id)).as(User.class);
	}

	public User saveUser(User user) {
		userCollection.save(user);
		return user;
	}

	public User updateUser(String id, User user) {
		User findUser = findUser(id);
		String password = user.getPassword();
		if (Strings.isNullOrEmpty(password)) {
			password = findUser.getPassword(); // prevents empty password
		}
		userCollection.update(Oid.withOid(id)).with("{$set: {name: #, surname: #, login: #, password: #}}",
				user.getName(), user.getSurname(), user.getLogin(), password);
		return user;
	}

	public void removeUser(String id) {
		userCollection.remove(Oid.withOid(id));
	}

	public User findByLoginAndPassword(String login, String password) {
		return userCollection.findOne(String.format("{login : '%s', password : '%s'}", login, password)).as(User.class);
	}

	public User findByLoginAndAuthToken(String login, String authToken) {
		return userCollection.findOne(String.format("{login : '%s', authToken : '%s'}", login, authToken)).as(User.class);
	}

}
