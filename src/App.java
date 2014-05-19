package main;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;


public class App {
  MongoClient mongoClient;
  
  public App() throws Exception{
    System.out.println("Setting up client");
    mongoClient = new MongoClient("127.0.0.1", 27017);
    System.out.println("Client set up");
   //statement.execute("DROP TABLE IF EXISTS test;");
   //statement.execute("CREATE TABLE test (city varchar(20), state char(2));");
  }
  
  public void insertData(String city, String state) {
    System.out.println("Getting database");
    DB db = mongoClient.getDB("test");
    System.out.println("Got database");
    DBCollection coll = db.getCollection("testCollection");
    System.out.println("Got collection");
    BasicDBObject doc = new BasicDBObject("city", city).append("state", state);
    System.out.println("Got object");
    coll.insert(doc);
  }

  public String[] getData(String city, String state) {
    String[] toReturn = null;
    DBCursor cursor = null;
      
    try {
      DB db = mongoClient.getDB("test");
      DBCollection coll = db.getCollection("testCollection");
      BasicDBObject doc = new BasicDBObject("city", "Seattle").append("state", "WA");
      coll.insert(doc);
      if (city != null && state != null) {
        BasicDBObject query = new BasicDBObject("city", city).append("state", state);
        cursor = coll.find(query);
      } else if (city != null) {
        BasicDBObject query = new BasicDBObject("city", city);
        cursor = coll.find(query);
      } else if (state != null) {
        BasicDBObject query = new BasicDBObject("state", state);
        cursor = coll.find(query);
      } else {
        cursor = coll.find();
      }
      
      int number = cursor.size();
      toReturn = new String[number];
      int i = 0;
      while (cursor.hasNext()) {
        DBObject item = cursor.next();
        toReturn[i] = item.get("city") + ", " + item.get("state");
        i++;
      }
    } finally {
      try {
        cursor.close();
      } catch (Exception ex) {
        cursor = null;
      }
      return toReturn;
    }
  }
}

