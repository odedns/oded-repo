/**
 * 
 */
package com.mts.nrtrde.client;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Oded Nissan
 *
 */
public class AlertInfoContainer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int currentVolumeThreshold;
	private int currentUsageThreshold;
	private int currentSMSThreshold;
	private ArrayList<AlertInfo> alerts;
	
	
	public int getCurrentVolumeThreshold() {
		return currentVolumeThreshold;
	}
	public void setCurrentVolumeThreshold(int currentVolumeThreshold) {
		this.currentVolumeThreshold = currentVolumeThreshold;
	}
	public int getCurrentUsageThreshold() {
		return currentUsageThreshold;
	}
	public void setCurrentUsageThreshold(int currentUsageThreshold) {
		this.currentUsageThreshold = currentUsageThreshold;
	}
	public int getCurrentSMSThreshold() {
		return currentSMSThreshold;
	}
	public void setCurrentSMSThreshold(int currentSMSThreshold) {
		this.currentSMSThreshold = currentSMSThreshold;
	}
	public ArrayList<AlertInfo> getAlerts() {
		return alerts;
	}
	public void setAlerts(ArrayList<AlertInfo> alerts) {
		this.alerts = alerts;
	}
	
}
