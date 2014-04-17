/**
 * 
 */
package com.mts.nrtrde.client;

import java.io.Serializable;
import java.util.Date;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * @author Oded Nissan
 *
 */
public class AlertInfo extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public AlertInfo()
	{
		
	}
	
	public void setIMSI(String IMSI)
	{
		set("IMSI",IMSI);
	}
	
	public String getIMSI()
	{
		return (get("IMSI"));
	}
	
	
	public void setDeviationDate(Date d)
	{
		set("deviationDate",d);
	}
	
	public void setThreshold(long threshold)
	{
		set("threshold",threshold);
	}
	
	public void setUsage(int usage)
	{
		set("usage",usage);
	}
	
	public void setVolume(int volume)
	{
		set("volume",volume);
	}
	
	public void setSMS(int sms)
	{
		set("sms",sms);
	}
	
	public void setVendors(String vendors)
	{
		set("vendors",vendors);
	}

	public boolean isUsageColored() {
		return get("usageColored");
	}

	public void setUsageColored(boolean usageColored) {
		set("usageColored",usageColored);
	}

	public boolean isSmsColored() {
		return get("smsColored");
	}

	public void setSmsColored(boolean smsColored) {
		set("smsColored",smsColored);
	}

	public boolean isVolumeColored() {
		return get("volumeColored");
	}

	public void setVolumeColored(boolean volumeColored) {
		set("volumeColored",volumeColored);
	}

	
}
