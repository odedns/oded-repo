/**
 * 
 */
package com.mts.nrtrde.server.reports;

import java.util.ArrayList;
import java.util.Date;

import com.mts.nrtrde.server.filters.Criteria;
import com.mts.nrtrde.shared.RecordType;

/**
 * @author Oded Nissan
 *
 */
public class ReportMgr {
	
	
	public static ArrayList<FDRRecord> executeFDRReport(ArrayList<Criteria<?>> critList)
	{
		ArrayList<FDRRecord> recList = new ArrayList<FDRRecord>();
	    FDRRecord record = new FDRRecord("fdrFile", "VPMN", new Date(), new Date(), "NRFILE", new Date(), 1);
	    if(critList.size() > 0 ){
	    	record = new FDRRecord("fdrFile-crit", "VPMN", new Date(), new Date(), "NRFILE", new Date(), 1);
	    }
	    recList.add(record);
		return(recList);
	}
	
	public static ArrayList<ERRRecord> executeERRReport(ArrayList<Criteria<?>> critList)
	{
		
		ArrayList<ERRRecord> recList = new ArrayList<ERRRecord>();
		ERRRecord record = new ERRRecord("VPMN","NRFILE",10,"errFile",10,RecordType.GPRS,new Date());
		 if(critList.size() > 0 ){
			 record = new ERRRecord("VPMN-2","NRFILE",10,"errFile",10,RecordType.GPRS,new Date());
		 }
	    recList.add(record);
		return(recList);
		
	}
	

}
