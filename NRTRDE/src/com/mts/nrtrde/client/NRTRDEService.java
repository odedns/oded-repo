package com.mts.nrtrde.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.mts.nrtrde.shared.SessionException;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("NRTRDEService")
public interface NRTRDEService  extends RemoteService {
	
	/**
	 * login to the NRTRDE app
	 * @param user the user
	 * @param password password 
	 * @return boolean true in case of success
	 * @throws Exception in case of error.
	 */
	public boolean login(String user, String password) throws Exception;
	/**
	 * logout of the application.
	 * @throws Exception in case of error.
	 */
	public void logout();
	
	
	/**
	 * Get the list of operators.
	 * @return List of OperatorValueObjects 
	 * @throws Exception in case of error.
	 */
	public ArrayList<OperatorValueObject> getOperators() throws Exception;
	/**
	 * get the files to generate.
	 * @param fromDate filter fromDate
	 * @param toDate filter to date.
	 * @param operator filter by operator.
	 * @return ArrayList of files to generate.
	 * @throws Exception in case of Error.
	 */
	public ArrayList<FileInfo> findGenerateFiles(Date fromDate, Date toDate, String operator) throws Exception,SessionException;
	
	/**
	 * collect files.
	 * @param operator filter by operator.
	 * @return ArrayList of files to generate.
	 * @throws Exception in case of Error.
	 */
	public ArrayList<FileInfo> findCollectedFiles( String operator) throws Exception, SessionException;
	
	/**
	 * update paramters save them in a property file on the server.
	 * @param params HashMap containing the properties
	 * @throws Exception in case of error.
	 */
	public void updateParameters(HashMap<String,String> params) throws Exception, SessionException;
	
	
	/**
	 * get the parameters from the property file.
	 * @return HashMap containing the paramters.
	 * @throws Exception in case of error.
	 */
	public HashMap<String,String> getParameters() throws Exception, SessionException;
	
	
	/**
	 * get the list of alerst.
	 * @return ArrayList containing AlertsInfo objects.
	 * @throws Exception in case of error.
	 */
	public AlertInfoContainer listAlerts() throws Exception, SessionException;

	public ArrayList<FileDeliveryDetails> executeFileDeliveryReport(String operator, String NRFile,String FDRFile, Date fromDate, Date toDate,String sequence) throws SessionException;
	
	public ArrayList<ErrorReportDetails> executeErrorReport(String operator, String erroType, int errorCode,String NRFile,String FDRFile, Date fromDate, Date toDate,String sequence) throws SessionException;

}
