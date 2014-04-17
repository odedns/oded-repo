/**
 * 
 */
package com.mts.nrtrde.server;

import org.apache.http.entity.FileEntity;

import com.mts.nrtrde.client.AlertInfo;
import com.mts.nrtrde.client.ErrorReportDetails;
import com.mts.nrtrde.client.FileDeliveryDetails;
import com.mts.nrtrde.client.OperatorValueObject;
import com.mts.nrtrde.server.alerts.Alert;
import com.mts.nrtrde.server.infra.Vendor;
import com.mts.nrtrde.server.reports.ERRRecord;
import com.mts.nrtrde.server.reports.FDRRecord;

/**
 * @author Oded Nissan
 *
 */
public class ConversionUtils {
	

	/**
	 * convert the Alert value object into the AlertInfo 
	 * value object used by GXT.
	 * @param alert value object containing alert data
	 * @return an AlertInfo object.
	 */
	public static AlertInfo convertAlert(Alert alert)
	{
		AlertInfo alertInfo = new AlertInfo();
		
		alertInfo.setDeviationDate(alert.getDeviationDate());
		alertInfo.setIMSI(alert.getIMSI());
		alertInfo.setUsage(alert.getUsage().getValue());
		alertInfo.setVolume(alert.getVolume().getValue());
		alertInfo.setSMS(alert.getSms().getValue());
		alertInfo.setUsageColored(alert.getUsage().isColored());
		alertInfo.setVolumeColored(alert.getVolume().isColored());
		alertInfo.setSmsColored(alert.getSms().isColored());
		String vendors[] = alert.getVendors();
		StringBuffer sb = new StringBuffer();
		for(int i=0; i < vendors.length; ++i) {
			sb.append(vendors[i]);
			if(i +1 < vendors.length) {
				sb.append(',');
			}
		}
		alertInfo.setVendors(sb.toString());
		alertInfo.setThreshold(alert.getTimespan());
		return(alertInfo);
	}

	/**
	 * convert an ErrorRecord value object into and ErrorReportDetails value
	 * object used by GXT.
	 * @param record
	 * @return
	 */
	public static ErrorReportDetails convertErrRecord(ERRRecord record)
	{
		ErrorReportDetails errDetails = new ErrorReportDetails();
		
		errDetails.setEREventDate(record.getEventDate());
		errDetails.setErrFileName(record.getErrFileName());
		errDetails.setErrorCode(record.getErrorCode());
		errDetails.setNRTRDEFileName(record.getNrFile());
		errDetails.setRecordNum(record.getRecordNum());
		errDetails.setRecordType(record.getRecordType().toString());
		errDetails.setVPMN(record.getVPMN());
		
		return(errDetails);
		
	}
	
	/**
	 * convert an FDR Record value object into and FileDeliveryDetails value
	 * object used by GXT.
	 * @param record
	 * @return
	 */
	public static FileDeliveryDetails convertFileRecord(FDRRecord record)
	{
		FileDeliveryDetails fileDetails = new FileDeliveryDetails();
		
		fileDetails.setEndDate(record.getToDate());
		fileDetails.setStartDate(record.getStartDate());
		fileDetails.setFDRfileName(record.getFdrFile());
		fileDetails.setNRTRDEfileName(record.getNrFile());
		fileDetails.setReceivedDate(record.getRecievedDate());
		fileDetails.setRecordNum(record.getRecordNum());
		fileDetails.setVPMN(record.getVPMN());
		
		return(fileDetails);
	}
	
	
	public static OperatorValueObject convertVendor(Vendor vendor)
	{
		OperatorValueObject oper = new OperatorValueObject(vendor.getCode(),vendor.isCommercial(),vendor.getDescription());		
		return (oper);		
	}
}
