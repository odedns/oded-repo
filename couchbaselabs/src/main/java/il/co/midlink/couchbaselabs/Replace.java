/**
 * 
 */
package il.co.midlink.couchbaselabs;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;

/**
 * @author midlink
 *
 */
public class Replace {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Connect to localhost
		Cluster cluster = CouchbaseCluster.create();
		Bucket beerSampleBucket = cluster.openBucket("beer-sample");
		System.out.println("connected");
		String id = "midlink_brewery_2";
		JsonDocument doc = beerSampleBucket.get(id);
		JsonObject beer = doc.content();
		beer.put("city", "Tel-Aviv");
		beerSampleBucket.replace(JsonDocument.create("midlink_brewery_1", beer));
		cluster.disconnect();
	}

}
