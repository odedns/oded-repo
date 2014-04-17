/**
 * 
 */
package com.mts.nrtrde.client;

import java.io.Serializable;
import java.util.Date;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;

/**
 * @author Oded Nissan
 *
 */
public class FileDeliveryDetails extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public FileDeliveryDetails()
	{
		
	}
	
	public String getFDRfileName() {
		return get("FDRfileName");
	}
	public void setFDRfileName(String fDRfileName) {
		set("FDRfileName",fDRfileName);
	}
	public String getVPMN() {
		return get("VPMN");
	}
	public void setVPMN(String vPMN) {
		set("VPMN", vPMN);
	}
	
	public String getNRTRDEfileName() {
		return get("NRTRDEfileName");
	}
	public void setNRTRDEfileName(String nRTRDEfileName) {
		set("NRTRDEfileName",nRTRDEfileName);
	}
	public Date getReceivedDate() {
		return get("ReceivedDate");
	}
	public void setReceivedDate(Date receivedDate) {
		set("ReceivedDate", receivedDate);
	}
	public int getRecordNum() {
		return get("recordNum");
	}
	public void setRecordNum(int recordNum) {
		set("recordNum",recordNum);
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setStartDate(Date startDate) {
		set("startDate", startDate);
	}
	public Date getStartDate() {
		return get("startDate");
	}
	public void setEndDate(Date endDate) {
		set("endDate", endDate);
	}
	public Date getEndDate() {
		return get("endDate");
	}
	
}
