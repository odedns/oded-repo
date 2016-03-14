/**
 * 
 */
package il.co.midlink.couchbaselabs;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;

/**
 * @author midlink
 *
 */
public class Delete {

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
		beerSampleBucket.remove(id);
		cluster.disconnect();
	}

}
