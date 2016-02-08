package br.com.damoreira.util;

import javax.enterprise.inject.Produces;

import org.jongo.Jongo;
import org.jongo.MongoCollection;

import com.mongodb.DB;
import com.mongodb.MongoClient;

import br.com.damoreira.service.UserQualifier;

/**
 * This class uses CDI to alias Java EE resources, such as the persistence
 * context, to CDI beans
 * 
 * <p>
 * Example injection on a managed bean field:
 * </p>
 * 
 * <pre>
 * &#064;Inject
 * private Jongo jongo;
 * </pre>
 */
public class Resources {

	private static final String MONGO_DATABASE = "local";
	private static final String MONGO_HOST = "172.17.0.2"; //System.getProperty("MONGO_DB_HOST");
	private static final String USER_COLLECTION = "user";

	@SuppressWarnings("deprecation")
	/**
	 * Using deprecated method because Jongo API don't support the new
	 * {@see MongoDatabase} object.
	 */
	protected DB db = new MongoClient(MONGO_HOST).getDB(MONGO_DATABASE);

	@Produces
	public Jongo produceJongo() {
		return new Jongo(db);
	}

	@Produces
	@UserQualifier
	public MongoCollection produceUserCollection(Jongo jongo) {
		return jongo.getCollection(USER_COLLECTION);
	}

}
