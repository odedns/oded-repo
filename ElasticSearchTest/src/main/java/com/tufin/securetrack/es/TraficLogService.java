/**
 * 
 */
package com.tufin.securetrack.es;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

/**
 * @author oded.nissan
 *
 */
public class TraficLogService {
	private String hostName;
	private int port;
	private TransportClient client;
	
	public TraficLogService()
	{
		
	}
	
	
	public void init()
	{
		Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", "elasticsearch").build();
		client = new TransportClient(settings);
		
		client.addTransportAddress( new InetSocketTransportAddress(hostName, port));
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

}
