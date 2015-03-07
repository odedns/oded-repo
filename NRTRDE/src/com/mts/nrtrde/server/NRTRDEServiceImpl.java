/**
 * 
 */
package com.mts.nrtrde.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;


import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mts.nrtrde.client.AlertInfo;
import com.mts.nrtrde.client.AlertInfoContainer;
import com.mts.nrtrde.client.ErrorReportDetails;
import com.mts.nrtrde.client.FileDeliveryDetails;
import com.mts.nrtrde.client.FileInfo;
import com.mts.nrtrde.client.NRTRDEService;
import com.mts.nrtrde.client.OperatorValueObject;

import com.mts.nrtrde.server.alerts.Alert;
import com.mts.nrtrde.server.alerts.AlertContainer;
import com.mts.nrtrde.server.alerts.AlertMgr;
import com.mts.nrtrde.server.filters.Criteria;
import com.mts.nrtrde.server.filters.DateRange;
import com.mts.nrtrde.server.filters.DateRangeCriteria;
import com.mts.nrtrde.server.filters.FilterConstants;
import com.mts.nrtrde.server.reports.ERRRecord;
import com.mts.nrtrde.server.reports.FDRRecord;
import com.mts.nrtrde.server.reports.ReportMgr;
import com.mts.nrtrde.shared.SessionException;

/**
 * @author Oded Nissan
 *
 */
public class NRTRDEServiceImpl  extends RemoteServiceServlet implements NRTRDEService {

	private static final Logger logger = Logger.getLogger(NRTRDEServiceImpl.class);
	private static final String PROPERTIES_FILENAME = "NRTRDE_server.properties";
	private Properties serverProps = null;
	private GenerateNRTRDE generateService = FileHandlerFactory.createGenerateHandler();
	private CollectNRTRDE collectService = FileHandlerFactory.createCollectorHandler();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	public NRTRDEServiceImpl()
	{
		try {
			this.serverProps = PropertyReader.read(PROPERTIES_FILENAME);
			logger.info("properties file read");
		} catch(IOException e) {
			logger.info("properties file not found");
			this.serverProps = new Properties();
		}
	}

	/* (non-Javadoc)
	 * @see com.mts.nrtrde.client.NRDTREService#login(java.lang.String, java.lang.String)
	 */
	public boolean login(String user, String password) throws Exception {
		// TODO Auto-generated method stub
		getThreadLocalRequest().getSession(true);
		return(true);
	}

	/**
	 * check if a session exists.
	 * @return true if a session exists.
	 */
	private boolean sessionExists()
	{
		HttpSession session = getThreadLocalRequest().getSession(false);
		return(session != null);
		
	}

	/* (non-Javadoc)
	 * @see com.mts.nrtrde.client.NRDTREService#findGenerateFiles(java.util.Date, java.util.Date, int)
	 */
	@Override
	public ArrayList<FileInfo> findGenerateFiles(Date fromDate, Date toDate,
			String operator) throws Exception, SessionException  {
		// TODO Auto-generated method stub
		if(!sessionExists()) {
			logger.error("No Session");
			throw new SessionException("No Session");			
		}
		File files[] = generateService.generateFiles(operator, fromDate, toDate);
		ArrayList<FileInfo> fileList = new ArrayList<FileInfo>();
		for(int i=0; i < files.length; ++i) {
			long lastModified = files[i].lastModified();
			Date d = new Date(lastModified);
			FileInfo fi = new FileInfo(files[i].getName(), "type", d);
			fileList.add(fi);
			logger.debug("file: " + files[i].getName());
		}
		return(fileList);
		
	}


	/* (non-Javadoc)
	 * @see com.mts.nrtrde.client.NRDTREService#updateParameters(java.util.HashMap)
	 */
	public void updateParameters(HashMap<String, String> params)
			throws Exception {
		// TODO Auto-generated method stub
		if(!sessionExists()) {
			logger.error("No Session");
			throw new Exception("No Session");			
		}
		logger.debug("Got params: " + params.toString());
		Properties props = new Properties();
		props.putAll(params);
		String paramsFile = serverProps.getProperty("nrtrde.parametersFile", "c:\\temp\\NRTRDE_params_def.properties");
		FileOutputStream os = new FileOutputStream(paramsFile);
		props.store(os, "params file");

	}

	public ArrayList<OperatorValueObject> getOperators() throws Exception {
		// TODO Auto-generated method stub
		ArrayList<OperatorValueObject> list = new ArrayList<OperatorValueObject>();
		list.add(new OperatorValueObject("oper1",true,"oper1"));
		list.add(new OperatorValueObject("oper2",true,"oper2"));
		list.add(new OperatorValueObject("oper3",true,"oper3"));
		list.add(new OperatorValueObject("oper4",false,"oper4"));
		list.add(new OperatorValueObject("oper5",false,"oper5"));
		list.add(new OperatorValueObject("oper6",false,"oper6"));
		return (list);
	}

	@Override
	public ArrayList<FileInfo> findCollectedFiles(String operator) throws Exception,SessionException {
		// TODO Auto-generated method stub
		if(!sessionExists()) {
			logger.error("No Session");
			throw new SessionException("No Session");
		}
		File files[] = collectService.getFilesFromHub(operator, null,null);
		ArrayList<FileInfo> fileList = new ArrayList<FileInfo>();
		for(int i=0; i < files.length; ++i) {
			long lastModified = files[i].lastModified();
			Date d = new Date(lastModified);
			FileInfo fi = new FileInfo(files[i].getName(), "type", d);
			fileList.add(fi);
			logger.debug("file: " + files[i].getName());
		}
		return(fileList);
		
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub
		getThreadLocalRequest().getSession().invalidate();
	}



	

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, String> getParameters() throws Exception, SessionException {
		// TODO Auto-generated method stub
		if(!sessionExists()) {
			logger.error("No Session");
			throw new SessionException("No Session");
		}
		String paramsFile = serverProps.getProperty("nrtrde.parametersFile", "c:\\temp\\NRTRDE_params_def.properties");
		FileInputStream fis = null;
		HashMap<String, String> map = null;
		try {
			fis = new FileInputStream(paramsFile);
			Properties props = new Properties();
			props.load(fis);
			@SuppressWarnings("rawtypes")
			Map m = (Map)props;		
			map = new HashMap<String, String>(m);
		} catch(FileNotFoundException fe) {
			logger.info("Params file not found: " + paramsFile);
		}
		
		return(map);
	}

	@Override
	public AlertInfoContainer listAlerts() throws Exception , SessionException{
		// TODO Auto-generated method stub
		logger.info("listAlerts");
		if(!sessionExists()) {
			logger.error("No Session");
			throw new SessionException("No Session");
		}
		AlertInfoContainer container = new AlertInfoContainer();
		ArrayList<AlertInfo> list = new ArrayList<AlertInfo>();
		
		AlertContainer aContainer = AlertMgr.getAllMockAlerts();
		ArrayList<Alert> alerts = aContainer.getAlerts();
		for(Alert a : alerts) {
			AlertInfo ai = ConversionUtils.convertAlert(a);
			list.add(ai);			
		}
		container.setAlerts(list);
		container.setCurrentSMSThreshold(aContainer.getCurrentSMSThreshold());
		container.setCurrentUsageThreshold(aContainer.getCurrentUsageThreshold());
		container.setCurrentVolumeThreshold(aContainer.getCurrentVolumeThreshold());
		return(container);
	}

	@Override
	public ArrayList<FileDeliveryDetails> executeFileDeliveryReport(
			String operator, String NRFile, String FDRFile, Date fromDate,
			Date toDate, String sequence) throws SessionException{
		
		if(!sessionExists()) {
			logger.error("No Session");
			throw new SessionException("No Session");
		}
		ArrayList<FileDeliveryDetails> fdDetails = null;
		
		logger.info("executeFileDeliveryReport: operator= " + operator +"\tNRFile=" + NRFile +
				"\tFDRFile=" + FDRFile + "\tfromDate=" + fromDate + "\toDate=" + toDate
				+ "\tsequence = " + sequence);
		ArrayList<Criteria<?>>  critList = createCriteria(operator,NRFile, FDRFile, fromDate, toDate, sequence);
		ArrayList<FDRRecord> fdrList = ReportMgr.executeFDRReport(critList);
		if(fdrList != null) {
			fdDetails = new ArrayList<FileDeliveryDetails>();
			for(FDRRecord record : fdrList){
				FileDeliveryDetails fdRecord = ConversionUtils.convertFileRecord(record);
				fdDetails.add(fdRecord);
			}
		}
				
		return(fdDetails);
	}

	@Override
	public ArrayList<ErrorReportDetails> executeErrorReport(String operator,
			String errorType, int errorCode, String NRFile, String FDRFile,
			Date fromDate, Date toDate, String sequence) throws SessionException{
		
		if(!sessionExists()) {
			logger.error("No Session");
			throw new SessionException("No Session");
		}
		ArrayList<ErrorReportDetails> errDetailsList = null;
		
		logger.info("executeErrorReport: operator=" + operator +"\terrorType=" + errorType + "\terrorCode=" + errorCode 
				+ "\tNRFile=" + NRFile +"\tFDRFile=" + FDRFile + "\tfromDate=" + fromDate + "\toDate=" + toDate
		+ "\tsequence = " + sequence);
		ArrayList<Criteria<?>>  critList = createCriteria(operator,NRFile, FDRFile, fromDate, toDate, sequence);
		if(errorType != null) {
			Criteria<String> c = new Criteria<String>(errorType, FilterConstants.ERROR_TYPE);
			critList.add(c);
		}
		if(errorCode != 0) {
			Criteria<Integer> c = new Criteria<Integer>(errorCode, FilterConstants.ERROR_CODE);
			critList.add(c);
		}
		ArrayList<ERRRecord> errList = ReportMgr.executeERRReport(critList);
		if(errList != null) {
			errDetailsList = new ArrayList<ErrorReportDetails>();
			for(ERRRecord record : errList) {
				ErrorReportDetails errDetails = ConversionUtils.convertErrRecord(record);
				errDetailsList.add(errDetails);
			}
		}
		return(errDetailsList);
	}
	
	
	
	private ArrayList<Criteria<?>> createCriteria(String operator, String NRFile, String FDRFile,
			Date fromDate, Date toDate,String sequence)
	{
		ArrayList<Criteria<?>> critList = new ArrayList<Criteria<?>>();
		if(operator != null) {
			Criteria<String> c = new Criteria<String>(operator, FilterConstants.OPERATOR);
			critList.add(c);
		}
		if(NRFile != null) {
			Criteria<String> c = new Criteria<String>(NRFile, FilterConstants.NR_FILE);
			critList.add(c);
		}
		if(FDRFile != null) {
			Criteria<String> c = new Criteria<String>(FDRFile, FilterConstants.FDR_FILE);
			critList.add(c);
		}
		if(sequence != null) {
			Criteria<String> c = new Criteria<String>(sequence, FilterConstants.SEQUENCE);
			critList.add(c);
		}
		if(fromDate != null) {
			DateRange range = new DateRange(fromDate, toDate);
			DateRangeCriteria dr = new DateRangeCriteria(range, FilterConstants.DATE_RANGE);
			critList.add(dr);
		}
		
		
		return(critList);
	}


}
