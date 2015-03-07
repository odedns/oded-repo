/**
 * 
 */
package test;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;



/**
 * @author I070659
 *
 */
public class TestSolr {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String queryStr = "id:VS1GB400C3";
		System.out.println("Test Solr...");
		String urlString = "http://localhost:8983/solr";
		//String urlString = "http://i070659-app.wdf.sap.corp:8983/solr";
		HttpSolrServer solr = new HttpSolrServer(urlString);
		solr.setConnectionTimeout(5000); // 5 seconds to establish TCP
		  // Setting the XML response parser is only required for cross
		  // version compatibility and only when one side is 1.4.1 or
		  // earlier and the other side is 3.1 or later.
		  solr.setParser(new XMLResponseParser()); // binary parser is used by default
		  // The following settings are provided here for completeness.
		  // They will not normally be required, and should only be used 
		  // after consulting javadocs to know whether they are truly required.
		  solr.setSoTimeout(1000);  // socket read timeout
		  
		SolrQuery parameters = new SolrQuery();
		parameters.set("q", queryStr);
		QueryResponse response;
		try {
			response = solr.query(parameters);
			SolrDocumentList list = response.getResults();
			System.out.println("Num results: " + list.size());
			for( SolrDocument doc : list) {
				System.out.println("doc = " + doc.toString());
			}
			//System.out.println("list = " + list.toString());
			
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}

}
