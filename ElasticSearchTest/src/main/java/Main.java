
import java.util.Map;
import java.util.Set;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.StopWatch;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHitField;
import org.elasticsearch.search.SearchHits;

import static org.elasticsearch.index.query.QueryBuilders.*;


/**
 * 
 */

/**
 * @author oded.nissan
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", "elasticsearch").build();
		TransportClient client = new TransportClient(settings);
		
		client.addTransportAddress( new InetSocketTransportAddress("10.100.125.3", 9300));
	
		
		
		QueryBuilder qb = matchQuery("source","135.41.43.21");
		
		String fields[] = { "source","dest","destport","protocol","deviceName"};
	//	QueryBuilder qb = matchAllQuery();
		StopWatch st = new StopWatch();
		st.start();
		SearchResponse response = client.prepareSearch().addFields(fields).setQuery(qb).execute().actionGet();
		
		System.out.println("result = " + response.toString());
		st.stop();
		System.out.println("elapsed = " + st.toString());
		SearchHits hits = response.getHits();
		System.out.println("number of hits : " + hits.getTotalHits());
		
		for(SearchHit hit: hits.getHits()) {
			//System.out.println("hit=\n"+ hit.getSourceAsString());
			Map<String,SearchHitField> map = hit.getFields();
			Set<String> keys = map.keySet();
			for(String s: keys) {
				SearchHitField f = map.get(s);
				System.out.println("name=" + f.getName() + " value =" + f.getValue());
			}
			
			System.out.println(map.toString());
		}
		
		
		client.close();
				
	}

}
