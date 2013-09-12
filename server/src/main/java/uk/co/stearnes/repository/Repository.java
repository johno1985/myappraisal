package uk.co.stearnes.repository;

import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

public class Repository {
	
	private DB db;
	private DBCollection collection;

	public Repository(DB db) {
		this.db = db;
	}

	public String create(Date date, String verbatim, long userId) {
		collection = db.getCollection("diary");
		
		BasicDBObject doc = new BasicDBObject("date", date)
				.append("verbatim", verbatim)
				.append("user_id", userId);

		collection.insert(doc);
		return doc.getString("_id");
	}
}
