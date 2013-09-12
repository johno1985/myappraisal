package uk.co.stearnes.repository;

import static org.junit.Assert.assertEquals;

import java.net.UnknownHostException;
import java.util.Date;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class RepositoryTest {

	private MongoClient mongoClient;
	private DB db;
	private Repository repository;
	private DBCollection collection;

	@Before
	public void setUp() throws UnknownHostException {
		mongoClient = new MongoClient("localhost");
		db = mongoClient.getDB("myappraisal-test");
		collection = db.getCollection("diary");
		repository = new Repository(db);
	}
	
	@Test
	public void createCreatesNewRecord() {
		Date date = new Date();
		String verbatim = "x";
		long userId = 1L;
		String recordId = repository.create(date, verbatim, userId);
		DBObject actual = getRecord(recordId);
		assertEquals(date, actual.get("date"));
		assertEquals(verbatim, actual.get("verbatim"));
		assertEquals(userId, actual.get("user_id"));
	}

	private DBObject getRecord(String recordId) {
		BasicDBObject query = new BasicDBObject("_id", new ObjectId(recordId));

		DBCursor cursor = collection.find(query);

		try {
		   while(cursor.hasNext()) {
			   return cursor.iterator().next();
		   }
		} finally {
		   cursor.close();
		}
		return null;
	}

}
