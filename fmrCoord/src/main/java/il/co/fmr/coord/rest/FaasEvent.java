/**
 * 
 */
package il.co.fmr.coord.rest;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @author oded
 *
 */
public class FaasEvent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String eventType;
	private String eventSource;
	private Date eventTime;
	private Map<String,String> params;
	
	
	/**
	 * empty constructor
	 */
	public FaasEvent()
	{
		
	}

	public String getEventType() {
		return eventType;
	}



	public void setEventType(String eventType) {
		this.eventType = eventType;
	}



	public Map<String, String> getParams() {
		return params;
	}



	public void setParams(Map<String, String> params) {
		this.params = params;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getEventSource() {
		return eventSource;
	}



	public void setEventSource(String eventSource) {
		this.eventSource = eventSource;
	}



	public Date getEventTime() {
		return eventTime;
	}



	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}
	

}
