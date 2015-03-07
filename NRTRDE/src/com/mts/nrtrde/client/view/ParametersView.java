/**
 * 
 */
package com.mts.nrtrde.client.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.SpinnerField;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Element;
import com.mts.nrtrde.client.BasicValueObject;
import com.mts.nrtrde.client.NRTRDE;
import com.mts.nrtrde.client.presenter.ParametersPresenter;

/**
 * @author Oded Nissan
 *
 */
public class ParametersView extends LayoutContainer implements ParametersPresenter.Display {
	
	private static final int LABEL_WIDTH = 120;
	private static final int FIELD_WIDTH = 250;
	private Button submitButton;
	
	
	private TextField<String> hostField;
	private SpinnerField  portField; 
	/*
	 * ftp fields
	 */
	private TextField<String> ftpField; 
	private SpinnerField ftpPortField;
	private TextField<String> userField; 
	private TextField<String> passField; 
	private TextField<String> ftpSendField;
	private TextField<String> ftpReceiveField;
	
	
	/*
	 * host configuration 
	 */
	private SpinnerField execIntervalField;
	private TextField<String> filesField;
	private TextField<String> generatedField;
	private SpinnerField historyDaysField;
	
	
	/*
	 * alarm configuration
	 */
	private SpinnerField alarmTimeSpanField;
	private SpinnerField thresholdField;
	private SpinnerField volField;
	private SpinnerField usageField;
	private SpinnerField smsThresholdField;

	/*
	 * notification configuration
	 */
	private Dialog phoneDialog;
	private TextArea phoneField;

	
	public ParametersView()
	{
		submitButton = new Button("Submit");
		phoneDialog = new Dialog();
		phoneDialog.setButtons(Dialog.OKCANCEL);
		phoneDialog.setHideOnButtonClick(true);

		
		ftpField = new TextField<String>();
		ftpPortField = new SpinnerField();
		userField = new TextField<String>();
		passField = new TextField<String>();
		ftpSendField = new TextField<String>();
		ftpReceiveField = new TextField<String>();
		execIntervalField = new SpinnerField();
		filesField = new TextField<String>();
		generatedField = new TextField<String>();
		historyDaysField = new SpinnerField();;
		thresholdField = new SpinnerField();
		alarmTimeSpanField = new SpinnerField();
		volField = new SpinnerField();
		usageField= new SpinnerField();
		smsThresholdField=new SpinnerField();
		
		phoneField = new TextArea();
		hostField = new TextField<String>();	
		portField = new SpinnerField();
	}
	
	
	@Override
	protected void onRender(Element parent, int index) {
		// TODO Auto-generated method stub
		super.onRender(parent, index);
	

		
		NumberFormat nf = NumberFormat.getFormat("#####");

		/*
		 * ftp fields
		 */		
		
		ftpField.setFieldLabel("FTP Server");
		ftpField.setAllowBlank(false);
	
		ftpPortField.setPropertyEditorType(Integer.class);
		ftpPortField.setFieldLabel("FTP Server port");
		ftpPortField.setMinValue(0);
		ftpPortField.setMaxValue(10000);
		ftpPortField.setValue(23);
		ftpPortField.setFormat(nf);
		
		userField.setFieldLabel("Username");	
		userField.setAllowBlank(false);
		
		passField.setFieldLabel("Password");
		passField.setAllowBlank(false);
		passField.setPassword(true);
		
		ftpSendField.setFieldLabel("FTP Send Path");
		ftpSendField.setAllowBlank(false);
		
		ftpReceiveField.setFieldLabel("FTP Receive Path");
		ftpReceiveField.setAllowBlank(false);
		
		FieldSet fs1 = new FieldSet();
		FormLayout fl1 = new FormLayout();
		fl1.setDefaultWidth(FIELD_WIDTH);
		fl1.setLabelWidth(LABEL_WIDTH);
		fs1.setLayout(fl1);
		fs1.setHeading("FTP Parameters");
		fs1.add(ftpField);
		fs1.add(ftpPortField);
		fs1.add(userField);
		fs1.add(passField);
		fs1.add(ftpSendField);
		fs1.add(ftpReceiveField);
		
		
		/*
		 * host configuration
		 */
		
		execIntervalField.setPropertyEditorType(Integer.class);
		execIntervalField.setFieldLabel("Execution Interval");
		execIntervalField.setAllowBlank(false);
		execIntervalField.setMinValue(0);
		execIntervalField.setFormat(nf);
		execIntervalField.setValue(NRTRDE.constants.execIntervalDefault());
		
		filesField.setFieldLabel("File Upload Path");
		
		generatedField.setFieldLabel("Generated Files Path");
		
		historyDaysField.setPropertyEditorType(Integer.class);
		historyDaysField.setFieldLabel("History Days");
		historyDaysField.setAllowBlank(false);
		historyDaysField.setMinValue(0);
		historyDaysField.setFormat(nf);
		
		FieldSet fs2 = new FieldSet();
		FormLayout fl2 = new FormLayout();
		fl2.setLabelWidth(LABEL_WIDTH);
		fl2.setDefaultWidth(FIELD_WIDTH);
		fs2.setLayout(fl2);
		fs2.setHeading("Host Configuration");
		fs2.add(execIntervalField);
		fs2.add(filesField);
		fs2.add(generatedField);
		fs2.add(historyDaysField);
		

		/*
		 * alarm notification
		 */
		alarmTimeSpanField.setPropertyEditorType(Integer.class);
		alarmTimeSpanField.setFieldLabel("Alarm Timespan");
		alarmTimeSpanField.setAllowBlank(false);
		alarmTimeSpanField.setMinValue(0);
		alarmTimeSpanField.setFormat(nf);
		alarmTimeSpanField.setValue(NRTRDE.constants.timeSpanDefault());
		
		thresholdField.setPropertyEditorType(Integer.class);
		thresholdField.setFieldLabel("Threshold");
		thresholdField.setAllowBlank(false);
		thresholdField.setMinValue(0);
		thresholdField.setFormat(nf);
		thresholdField.setValue(NRTRDE.constants.thresholdDefault());
		
		volField.setPropertyEditorType(Integer.class);
		volField.setFieldLabel("Volume");
		volField.setAllowBlank(false);
		volField.setMinValue(0);
		volField.setFormat(nf);
		
		
		usageField.setPropertyEditorType(Integer.class);
		usageField.setFieldLabel("Usage");
		usageField.setAllowBlank(false);
		usageField.setMinValue(0);
		usageField.setFormat(nf);
		
		
		smsThresholdField.setPropertyEditorType(Integer.class);
		smsThresholdField.setFieldLabel("SMS Threshold");
		smsThresholdField.setAllowBlank(false);
		smsThresholdField.setMinValue(0);
		smsThresholdField.setFormat(nf);
		
		
		FieldSet fs3 = new FieldSet();
		FormLayout fl3 = new FormLayout();
		fl3.setLabelWidth(LABEL_WIDTH);
		fl3.setDefaultWidth(FIELD_WIDTH);
		fs3.setLayout(fl3);
		fs3.setHeading("Alarm Configuration");
		fs3.add(alarmTimeSpanField);
		fs3.add(thresholdField);
		fs3.add(volField);
		fs3.add(usageField);
		fs3.add(smsThresholdField);
	/*
	 * notification configuration	
	 */
		hostField.setFieldLabel("Provision Server");
		hostField.setAllowBlank(false);
		hostField.setWidth(300);
		portField.setPropertyEditorType(Integer.class);
		portField.setFieldLabel("Provision Server port");		
		portField.setAllowBlank(false);
		portField.setMinValue(0);
		portField.setMaxValue(10000);
		portField.setValue(2323);
		portField.setFormat(nf);
		portField.setWidth(300);
		
		phoneField.setFieldLabel("Phone");
		FieldSet fs4 = new FieldSet();
		
		FormLayout fl4 = new FormLayout();
		fs4.setLayout(fl4);
		fs4.setHeading("Host Configuration");
		
		fs4.add(hostField);
		fs4.add(portField);
		fs4.add(phoneField);
	
		
		
		fl4.setLabelWidth(LABEL_WIDTH);
		fl4.setDefaultWidth(FIELD_WIDTH);
		
		FormPanel generateForm = new FormPanel();
		generateForm.setLayout(new TableLayout(2));
		
		generateForm.setAutoWidth(true);
		fs1.setExpanded(true);
		fs1.setAutoWidth(false);
		
		generateForm.add(fs1);
		generateForm.add(fs2);
		generateForm.add(fs3);
		generateForm.add(fs4);
		generateForm.addButton(submitButton);
		generateForm.setHeading("Parameters");
		
		add(generateForm);
		setWidth("90%");
		layout();
		
		
		/*
		 * need to do this after component is rendered.
		 */
		
		fs2.setHeight(fs1.getHeight());
		fs4.setHeight(fs3.getHeight());
	
	}

	
	
	@Override
	public Button getSubmitButton() {
		// TODO Auto-generated method stub
		return this.submitButton;
	}

	

	@Override
	public HashMap<String,String> getParameters() {
		// TODO Auto-generated method stub
		
		
		String ftp = ftpField.getValue();
		Integer ftpPort = (Integer)ftpPortField.getValue();
		String user = userField.getValue();
		String password = passField.getValue();
		String ftpSend = ftpSendField.getValue();
		String ftpReceive = ftpReceiveField.getValue();
		
		Integer execInterval = (Integer) execIntervalField.getValue();
		String generated = generatedField.getValue();	
		String files = filesField.getValue();
		Integer historyDays = (Integer) historyDaysField.getValue();
		
		Integer alarmTimeSpan = (Integer)alarmTimeSpanField.getValue();
		Integer threshold = (Integer)thresholdField.getValue();
		Integer vol = (Integer)volField.getValue();
		Integer usage = (Integer) usageField.getValue();
		Integer smsThreshold = (Integer) smsThresholdField.getValue();
		
		
		String host = hostField.getValue();
		Integer port = (Integer)portField.getValue();
		String phones = phoneField.getValue();
		
		/*
		 * we need to pass non null values so that
		 * we can put them into the Properties on the server side.
		 */
		HashMap<String,String> map = new HashMap<String,String>();
 		
 		map.put("ftpHost", ftp != null ? ftp : "");
 		map.put("ftpPort", ftpPort != null ? ftpPort.toString() : "");
 		map.put("ftpSendPath", ftpSend != null ? ftpSend : "");
 		map.put("ftpReceivePath", ftpReceive != null ? ftpReceive : "");
 		map.put("user", user != null? user : "");
 		map.put("password", password != null ? password : "");
 		
 		map.put("execInterval", execInterval != null ? execInterval.toString() : "");
 		map.put("generatedPath", generated != null? generated : "");
 		map.put("files", files != null? files : "");
 		map.put("historyDays", historyDays != null ? historyDays.toString() : "");
 		
 		
 		map.put("alarmTimeSpan", alarmTimeSpan != null ? alarmTimeSpan.toString() : "");
 		map.put("threshold", threshold != null ? threshold.toString() : "");
 		map.put("volume", vol != null ? vol.toString() : "");
 		map.put("usage", usage != null ? usage.toString() : "");
 		map.put("smsThreshold", smsThreshold != null ? smsThreshold.toString() : "");
 		
 		map.put("provisionHost", host != null ? host : "");
 		map.put("provisionPort", port != null ? port.toString() : "");
 		map.put("phones", phones);
 		
		return(map);
	}


	@Override
	public void setParameters(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		
		String ftpHost = map.get("ftpHost");			
		Integer ftpPort = getInteger(map,"ftpPort");						
		String user = map.get("user");
		String password = map.get("password");
		
		String ftpSendPath = map.get("ftpSendPath");
		String ftpReceivePath = map.get("ftpReceivePath");
		Integer execInterval = getInteger(map,"execInterval");
		
		String files = map.get("files");				
		String generated = map.get("generatedPath");
		Integer historyDays = getInteger(map,"historyDays");
		
		Integer alarmTimeSpan = getInteger(map,"alarmTimeSpan");
		Integer threshold = getInteger(map,"threshold");
		Integer vol = getInteger(map,"volume");
		Integer usage = getInteger(map,"usage");
		Integer smsThreshold = getInteger(map,"smsThreshold");
		
		String host = map.get("provisionHost");
		Integer port = getInteger(map,"provisionPort");
		String phones = map.get("phones");
			
		
		
		ftpField.setValue(ftpHost);
		ftpPortField.setValue(ftpPort);
		userField.setValue(user);
		passField.setValue(password);
		ftpSendField.setValue(ftpSendPath);
		ftpReceiveField.setValue(ftpReceivePath);
		execIntervalField.setValue(execInterval);
		filesField.setValue(files);					
		generatedField.setValue(generated);
		historyDaysField.setValue(historyDays);
		
		
		alarmTimeSpanField.setValue(alarmTimeSpan);
		thresholdField.setValue(threshold);
		usageField.setValue(usage);
		volField.setValue(vol);
		smsThresholdField.setValue(smsThreshold);
		
		hostField.setValue(host);
		portField.setValue(port);
		phoneField.setValue(phones);
	}
	
	
	/**
	 * convert a List to a string with concatenated phonenumbers
	 * separated by a comma
	 * @param list the List of phone numbers
	 * @return String the cncatenated list of phone nums.
	 */
	private String fromList(List<BasicValueObject> list)
	{
		StringBuffer sb = new StringBuffer();
		boolean first = false;
		for(BasicValueObject vo : list) {
			if(vo.getValue() == null || vo.getValue().isEmpty())
				continue;
			if(!first) {
				first = true;
			} else {
				sb.append(',');				
			}
			sb.append(vo.getValue());
			
		}
		return(sb.toString());
		
	}
	
	
	private Integer getInteger(HashMap<String,String> map,String key)
	{
		String s = map.get(key);
		int p=0;
		if(s != null ) {
			try {
				p = Integer.parseInt(s);
			} catch(NumberFormatException ne) {
				p = 0;
				GWT.log(ne.getMessage());
			}
		}		
		Integer retVal = new Integer(p);
		return (retVal);
		
	}
	
	private ArrayList<BasicValueObject> toList(String s)
	{
		ArrayList<BasicValueObject> list = new ArrayList<BasicValueObject>();
		String v[] = s.split(",");
		
		for(int i=0; i < v.length; ++i) {
			list.add(new BasicValueObject(i, v[i]));
		}
		
		return(list);
		
	}


		
	
	
}
