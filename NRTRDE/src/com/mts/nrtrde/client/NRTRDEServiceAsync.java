package com.mts.nrtrde.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface NRTRDEServiceAsync {



	void login(String user, String password, AsyncCallback<Boolean> callback);


	void findGenerateFiles(Date fromDate, Date toDate, String operator,
			AsyncCallback<ArrayList<FileInfo>> callback);

	public void updateParameters(HashMap<String, String> map,
			AsyncCallback<Void> callback);

	void getOperators(AsyncCallback<ArrayList<OperatorValueObject>> callback);

	void findCollectedFiles(String operator,
			AsyncCallback<ArrayList<FileInfo>> callback);

	void logout(AsyncCallback<Void> callback);

	void getParameters(AsyncCallback<HashMap<String, String>> callback);


	void listAlerts(AsyncCallback<AlertInfoContainer> asyncCallback);


	
	void executeFileDeliveryReport(String operator, String NRFile,
			String FDRFile, Date fromDate, Date toDate, String sequence,
			AsyncCallback<ArrayList<FileDeliveryDetails>> callback);


	void executeErrorReport(String operator, String erroType, int errorCode,
			String NRFile, String FDRFile, Date fromDate, Date toDate,
			String sequence,
			AsyncCallback<ArrayList<ErrorReportDetails>> callback);

}
