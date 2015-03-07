/**
 * 
 */
package com.mts.nrtrde.client.view;

import java.util.ArrayList;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.PagingModelMemoryProxy;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Element;
import com.mts.nrtrde.client.ErrorReportDetails;
import com.mts.nrtrde.client.NRTRDE;
import com.mts.nrtrde.client.OperatorValueObject;
import com.mts.nrtrde.client.presenter.ErrorReportPresenter;
import com.mts.nrtrde.shared.RecordType;

/**
 * @author Oded Nissan
 *
 */
public class ErrorReportView extends GridLayoutContainer implements ErrorReportPresenter.Display {
	
	Button searchButton;
	Button resetButton;
	ComboBox<OperatorValueObject> operList;
	TextField<String> nrFileField;
	TextField<String> fdrFileField;
	DateField fromDateField;
	DateField toDateField;
	TextField<String> seqField;
	NumberField errCodeField;
	SimpleComboBox<String> errTypeCombo;
	private PagingModelMemoryProxy proxy; 
	private PagingLoader<PagingLoadResult<ModelData>> loader;
	Radio nrFileRadio;
	Radio fdrFileRadio;
	Radio dateRadio;
	Radio seqRadio;
	
	
	public ErrorReportView()
	{
		this.searchButton = new Button("Submit");
		this.resetButton = new Button("Reset");
		this.operList = new ComboBox<OperatorValueObject>();
		this.nrFileField = new TextField<String>();
		this.fdrFileField = new TextField<String>();
		this.fromDateField = new DateField();
		this.toDateField = new DateField();
		this.seqField = new TextField<String>();
		this.errCodeField = new NumberField();
		this.errTypeCombo = new SimpleComboBox<String>();
		nrFileRadio = new Radio();
		fdrFileRadio = new Radio();
		dateRadio = new Radio();
		seqRadio = new Radio();
	}

	@Override
	protected void onRender(Element parent, int index) {
		// TODO Auto-generated method stub
		super.onRender(parent, index);
		
		
		ArrayList<ErrorReportDetails> errorFileList = new ArrayList<ErrorReportDetails>();
		this.proxy = new PagingModelMemoryProxy(errorFileList);  
		this.loader = new BasePagingLoader<PagingLoadResult<ModelData>>(proxy);  
		loader.setRemoteSort(true);  
		ListStore<ErrorReportDetails> errorStore = new ListStore<ErrorReportDetails>(loader);    
		final PagingToolBar toolBar = new PagingToolBar(NRTRDE.constants.pageSize());  
		toolBar.bind(loader);  
		  
		ArrayList<ColumnConfig> configs = new ArrayList<ColumnConfig>();  
		
			    
			    
		ColumnConfig column = new ColumnConfig();    
		column.setId("errFileName");    
		column.setHeader("Err File Name");
		column.setWidth(200);
	 	configs.add(column);
	 		 		
		column = new ColumnConfig();    
		column.setId("VPMN");
		column.setWidth(70);
		column.setHeader("VPMN");
			
			
	    configs.add(column);
	    column = new ColumnConfig();    
		column.setId("errorCode");
		column.setWidth(70);
		column.setHeader("Error Code");
		configs.add(column);
			
			
		column = new ColumnConfig();    
		column.setId("recordType");
		column.setHeader("Record Type");
		column.setWidth(100);
		configs.add(column);
	    	
		column = new ColumnConfig();    
		column.setId("EREventDate");    
		column.setHeader("ER Event Date");
		column.setDateTimeFormat(DateTimeFormat.getFormat("dd/MM/yyyy"));  
		column.setWidth(100);
	    configs.add(column);
	    	
		column = new ColumnConfig();    
		column.setId("NRTRDEFileName");    
		column.setHeader("NRTRDE File Name");
		column.setWidth(200);
	    configs.add(column);
			
		
			
		column = new ColumnConfig();    
		column.setId("recordNum");    
		column.setHeader("Record Number");    
		column.setWidth(150);
		configs.add(column);
			
			
		ColumnModel cm = new ColumnModel(configs);  
		Grid<ErrorReportDetails> grid = new Grid<ErrorReportDetails>(errorStore, cm);   
		grid.setStyleAttribute("borderTop", "none");   
		grid.setBorders(true);   
		grid.setStripeRows(true);  
		cp = new ContentPanel();  
		cp.setFrame(true);  
		cp.setHeading("FTP Task List");
		cp.setButtonAlign(HorizontalAlignment.CENTER);  
		cp.setLayout(new FitLayout());  
		cp.setBottomComponent(toolBar);
		cp.setSize(600,600);
		cp.setFrame(true);
		cp.setHeading("Error Report View");
			
		FormPanel fp = new FormPanel();
		FieldSet fs1 = new FieldSet();
		ListStore<OperatorValueObject> store = new ListStore<OperatorValueObject>();
		store.add(NRTRDE.nonCommercialList);
		operList.setStore(store);
		operList.setTriggerAction(TriggerAction.ALL);   
		operList.setFieldLabel("Operator");
		operList.setDisplayField("value");		
		errCodeField.setFieldLabel("Error Code");
		
		
		errTypeCombo.add(RecordType.GPRS.toString());
		errTypeCombo.add(RecordType.MOC.toString());
		errTypeCombo.add(RecordType.MTC.toString());
		errTypeCombo.setFieldLabel("Error Type");
		errTypeCombo.setTriggerAction(TriggerAction.ALL); 

			
		fs1.setHeading("Filter Criteria");
		fs1.add(operList);
		fs1.add(errCodeField);
		fs1.add(errTypeCombo);
		fs1.setLayout(new FormLayout());
			
		FieldSet fs2 = new FieldSet();
		fs2.setHeading("Search Criteria");
		nrFileRadio.setValue(Boolean.TRUE);
		/*
		 * enable the default field
		 * disable the rest.
		 */
		nrFileField.enable();
		fdrFileField.disable();
		seqField.disable();
		fromDateField.disable();
		toDateField.disable();
		
				 
		LabelField nrFileLabel = new LabelField("NR FILE:");
		LabelField fdrFileLabel = new LabelField("FDR File:");
		LabelField fromDateLabel = new LabelField("From Date:");
		LabelField toDateLabel = new LabelField("To Date:");
		LabelField seqLabel = new LabelField("Sequence:");
		com.google.gwt.user.client.ui.Grid gridLayout = new  com.google.gwt.user.client.ui.Grid(4,5);
		gridLayout.setWidget(0,0,nrFileRadio);
		gridLayout.setWidget(0,1,nrFileLabel);
		gridLayout.setWidget(0,2,nrFileField);
		gridLayout.setWidget(1,0,fdrFileRadio);
		gridLayout.setWidget(1,1,fdrFileLabel);
		gridLayout.setWidget(1,2,fdrFileField);
		gridLayout.setWidget(2,0,dateRadio);
		gridLayout.setWidget(2,1,fromDateLabel);
		gridLayout.setWidget(2,2,fromDateField);
		gridLayout.setWidget(2,3,toDateLabel);
		gridLayout.setWidget(2,4,toDateField);
		gridLayout.setWidget(3,0,seqRadio);
		gridLayout.setWidget(3,1,seqLabel);
		gridLayout.setWidget(3,2,seqField);
		fs2.add(gridLayout);
			
				 
		fp.add(fs1);
		fp.add(fs2);
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(searchButton);
		hp.add(resetButton);
		fp.add(hp);
		cp.setSize(NRTRDE.TABLE_WIDTH,NRTRDE.TABLE_HEIGHT);
		cp.setTopComponent(fp);
		cp.add(grid);
		add(cp);
		setLayout(new FitLayout());
		layout();
	}

	public Button getSearchButton() {
		// TODO Auto-generated method stub
		return this.searchButton;
	}

	@Override
	public ComboBox<OperatorValueObject> getOperList() {
		// TODO Auto-generated method stub
		return this.operList;
	}

	@Override
	public TextField<String> getNRFileField() {
		// TODO Auto-generated method stub
		return this.nrFileField;
	}

	@Override
	public TextField<String> getFDRFileField() {
		// TODO Auto-generated method stub
		return this.fdrFileField;
	}

	@Override
	public DateField getFromDateField() {
		// TODO Auto-generated method stub
		return this.fromDateField;
	}

	@Override
	public DateField getToDateField() {
		// TODO Auto-generated method stub
		return this.toDateField;
	}

	@Override
	public TextField<String> getSeqField() {
		// TODO Auto-generated method stub
		return this.seqField;
	}

	@Override
	public NumberField getErrCodeField() {
		// TODO Auto-generated method stub
		return this.errCodeField;
	}

	@Override
	public SimpleComboBox<String> getErrTypeCombo() {
		// TODO Auto-generated method stub
		return this.errTypeCombo;
	}
	@Override
	public String getOperator() {
		// TODO Auto-generated method stub
		OperatorValueObject v = this.operList.getValue();
		return(v != null ? v.getId(): null);

	}

	@Override
	public PagingModelMemoryProxy getProxy() {
		// TODO Auto-generated method stub
		return this.proxy;
	}

	@Override
	public PagingLoader<PagingLoadResult<ModelData>> getLoader() {
		// TODO Auto-generated method stub
		return this.loader;
	}
	@Override
	public Radio getNRFileRadio() {
		// TODO Auto-generated method stub
		return this.nrFileRadio;
	}

	@Override
	public Radio getFDRFileRadio() {
		// TODO Auto-generated method stub
		return this.fdrFileRadio;
	}

	@Override
	public Radio getDateRadio() {
		// TODO Auto-generated method stub
		return this.dateRadio;
	}

	@Override
	public Radio getSeqRadio() {
		// TODO Auto-generated method stub
		return this.seqRadio;
	}

	@Override
	public Button getResetButton() {
		// TODO Auto-generated method stub
		return this.resetButton;
	}
}
