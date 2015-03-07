package com.mts.nrtrde.server.alerts;

import java.util.Date;

public class Alert {

	private final ThresholdParam volume;
	private final ThresholdParam usage;
	private final ThresholdParam sms;
	private final Date deviationDate;
	private final int timespan;
	private final String[] vendors;
	private final String IMSI;
	
	public Alert(ThresholdParam volume, ThresholdParam usage,
			ThresholdParam sms, Date deviationDate, int timespan,
			String[] vendors, String IMSI) {
		super();
		this.volume = volume;
		this.usage = usage;
		this.sms = sms;
		this.deviationDate = deviationDate;
		this.timespan = timespan;
		this.vendors = vendors;
		this.IMSI = IMSI;
	}
	
	
	public String getIMSI() {
		return IMSI;
	}


	public ThresholdParam getVolume() {
		return volume;
	}


	public ThresholdParam getUsage() {
		return usage;
	}


	public ThresholdParam getSms() {
		return sms;
	}


	public Date getDeviationDate() {
		return deviationDate;
	}


	public int getTimespan() {
		return timespan;
	}


	public String[] getVendors() {
		return vendors;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((IMSI == null) ? 0 : IMSI.hashCode());
		result = prime * result
				+ ((deviationDate == null) ? 0 : deviationDate.hashCode());
		result = prime * result + ((sms == null) ? 0 : sms.hashCode());
		result = prime * result + ((usage == null) ? 0 : usage.hashCode());
		result = prime * result + ((volume == null) ? 0 : volume.hashCode());
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
		Alert other = (Alert) obj;
		if (IMSI == null) {
			if (other.IMSI != null)
				return false;
		} else if (!IMSI.equals(other.IMSI))
			return false;
		if (deviationDate == null) {
			if (other.deviationDate != null)
				return false;
		} else if (!deviationDate.equals(other.deviationDate))
			return false;
		if (sms == null) {
			if (other.sms != null)
				return false;
		} else if (!sms.equals(other.sms))
			return false;
		if (usage == null) {
			if (other.usage != null)
				return false;
		} else if (!usage.equals(other.usage))
			return false;
		if (volume == null) {
			if (other.volume != null)
				return false;
		} else if (!volume.equals(other.volume))
			return false;
		return true;
	}

	public static void main(String[] args) {
		Alert a = new Alert(new ThresholdParam(20, true), new ThresholdParam(10, false), new ThresholdParam(0, false),
				new Date(), 10, new String[]{"ATT"}, "37538");
		Alert b = new Alert(new ThresholdParam(30, true), new ThresholdParam(100, true), new ThresholdParam(5, false),
				new Date(), 10, new String[]{"ATT"}, "375382");

	}
	
}
