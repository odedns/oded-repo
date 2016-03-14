/**
 * 
 */
package il.co.midlink.couchbaselabs;


import com.couchbase.client.java.AsyncBucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;

import rx.Subscriber;

/**
 * @author midlink
 *
 */
public class AsyncClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Connect to localhost
		Cluster cluster = CouchbaseCluster.create();

		// Open the default bucket and the "beer-sample" one
		AsyncBucket beerSampleBucket = cluster.openBucket("beer-sample").async();
		String id = "21st_amendment_brewery_cafe-563_stout";
		beerSampleBucket.get(id)
		.subscribe(new Subscriber<JsonDocument>() {
			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				System.out.println("on completed");			
			}
			@Override
			public void onError(Throwable arg0) {
				// TODO Auto-generated method stub
				System.err.println("on error");	
			}
			@Override
			public void onNext(JsonDocument doc) {
				// TODO Auto-generated method stub
				System.out.println("got doc " + doc.content().toString());	
			}
		});
		
		
		// Disconnect and clear all allocated resources
		System.out.println("after ..");
		cluster.disconnect();
	}

}
