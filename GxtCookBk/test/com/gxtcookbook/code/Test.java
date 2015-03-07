/**
 * 
 */
package com.gxtcookbook.code;

import com.extjs.gxt.charts.client.model.charts.AreaChart;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;

/**
 * @author I070659
 *
 */
public class Test {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		String url = "http://api.worldbank.org/countries/NGA/indicators/SE.PRM.UNER.MA?date=2004:2007&format=json";
		fetchData(url);

	}
	
	private static void fetchData(String url){
		
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, URL.encode(url));
		try {					
			builder.sendRequest(null, new RequestCallback(){
				@Override
				public void onError(Request request, Throwable ex) {
					Info.display("Connection Error", ex.getMessage());	     
				}
				
				@Override
				public void onResponseReceived(Request request,
						Response response) {
					// check for an ok html response code (200).
					if (200 == response.getStatusCode()){
						JSONValue jsonVal = JSONParser.parseStrict(response.getText());
						System.out.println("json= " + response.getText());
					} else {
						System.out.println("response:" + response.getText());
							
					}
				}
			});
		} catch (RequestException ex) {
			System.out.println("Connection Error" + ex.getMessage());        
		}
	}
	

}
