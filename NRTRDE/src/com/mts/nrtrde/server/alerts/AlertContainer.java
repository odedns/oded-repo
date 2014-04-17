package com.mts.nrtrde.server.alerts;

import java.util.ArrayList;

public class AlertContainer {

	private final ArrayList<Alert> alerts;
	private final int currentVolumeThreshold;
	private final int currentUsageThreshold;
	private final int currentSMSThreshold;
	
	
	public AlertContainer(ArrayList<Alert> alerts, int currentVolumeThreshold,
			int currentUsageThreshold, int currentSMSThreshold) {
		super();
		this.alerts = alerts;
		this.currentVolumeThreshold = currentVolumeThreshold;
		this.currentUsageThreshold = currentUsageThreshold;
		this.currentSMSThreshold = currentSMSThreshold;
	}


	public ArrayList<Alert> getAlerts() {
		return alerts;
	}


	public int getCurrentVolumeThreshold() {
		return currentVolumeThreshold;
	}


	public int getCurrentUsageThreshold() {
		return currentUsageThreshold;
	}


	public int getCurrentSMSThreshold() {
		return currentSMSThreshold;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alerts == null) ? 0 : alerts.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlertContainer other = (AlertContainer) obj;
		if (alerts == null) {
			if (other.alerts != null)
				return false;
		} else if (!alerts.equals(other.alerts))
			return false;
		return true;
	}
	
	
}
