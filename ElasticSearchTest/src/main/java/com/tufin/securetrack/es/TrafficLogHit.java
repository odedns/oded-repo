/**
 * 
 */
package com.tufin.securetrack.es;

/**
 * @author oded.nissan
 *
 */
public class TrafficLogHit {
	private String source;
	private String destination;
	private int destPort;
	private int protocolId;
	private String deviceName;
	
	
	
	public TrafficLogHit()
	{
		
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public int getDestPort() {
		return destPort;
	}
	public void setDestPort(int destPort) {
		this.destPort = destPort;
	}
	public int getProtocolId() {
		return protocolId;
	}
	public void setProtocolId(int protocolId) {
		this.protocolId = protocolId;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

}
