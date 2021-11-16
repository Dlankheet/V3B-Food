import com.mongodb.*;

import java.util.Arrays;

public class Database {
    public static void main(String[] argv) throws Exception {
        //used tutorial: https://www.mongodb.com/blog/post/getting-started-with-mongodb-and-java-part-i
//        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoCredential credential = MongoCredential.createPlainCredential("root", "database", "rootpassword".toCharArray());
        MongoClient mongoClient = new MongoClient(new ServerAddress("localhost", 27017), Arrays.asList(credential));
        DB database = mongoClient.getDB("ad");
        mongoClient.getDatabaseNames().forEach(System.out::println);

        database.createCollection("customers", null);
        database.getCollectionNames().forEach(System.out::println);

        DBCollection collection = database.getCollection("customers");
        BasicDBObject document = new BasicDBObject();
        document.put("name", "Shubham");
        document.put("company", "Baeldung");
        collection.insert(document);
    }

}
