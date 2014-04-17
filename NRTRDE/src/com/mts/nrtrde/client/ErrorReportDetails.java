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
public class ErrorReportDetails extends BaseModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	
	
	public ErrorReportDetails()
	{
		
	}
	
	public String getErrFileName() {
		return get("errFileName");
	}
	public void setErrFileName(String errFileName) {
		set("errFileName",errFileName);
	}
	public String getVPMN() {
		return get("VPMN");
	}
	public void setVPMN(String vPMN) {
		set("VPMN",vPMN);
	}
	public int getErrorCode() {
		return get("errorCode");
	}
	public void setErrorCode(int errorCode) {
		set("errorCode",errorCode);
	}
	public String getRecordType() {
		return get("recordType");
	}
	public void setRecordType(String recordType) {
		set("recordType",recordType);
	}
	public Date getEREventDate() {
		return get("EREventDate");
	}
	public void setEREventDate(Date eREventDate) {
		set("EREventDate",eREventDate);
	}
	public String getNRTRDEFileName() {
		return get("NRTRDEFileName");
	}
	public void setNRTRDEFileName(String nRTRDEFileName) {
		set("NRTRDEFileName", nRTRDEFileName);
	}
	public int getRecordNum() {
		return get("recordNum");
	}
	public void setRecordNum(int recordNum) {
		set("recordNum",recordNum);
	}
	

}
