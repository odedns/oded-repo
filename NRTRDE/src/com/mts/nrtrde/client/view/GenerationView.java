/**
 * 
 */
package com.mts.nrtrde.client.view;

import java.util.ArrayList;
import java.util.Date;


import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.PagingModelMemoryProxy;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.DatePicker;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.datepicker.client.DateBox;
import com.mts.nrtrde.client.FileInfo;
import com.mts.nrtrde.client.NRTRDE;
import com.mts.nrtrde.client.OperatorValueObject;
import com.mts.nrtrde.client.presenter.GenerationPresenter;
import com.sun.corba.se.impl.encoding.CodeSetConversion.BTCConverter;

/**
 * @author Oded Nissan
 *
 */
public class GenerationView extends GridLayoutContainer  implements GenerationPresenter.Display {
	VerticalPanel panel;
	private Button generateButton;
	private Radio commercial;
	private Radio nonCommercial;
	private DateField fromDate;
	private DateField toDate;
	private PagingModelMemoryProxy proxy; 
	private PagingLoader<PagingLoadResult<ModelData>> loader;
	
	ComboBox<OperatorValueObject> operList;
	
	public GenerationView()
	{
		 this.operList = new ComboBox<OperatorValueObject>();
		 
		 this.commercial = new Radio();
		 this.nonCommercial = new Radio();
		 this.generateButton = new Button("Generate");

		
		 
	}
	
		

	@Override
	protected void onRender(Element parent, int index) {
		// TODO Auto-generated method stub
		super.onRender(parent, index);
		ArrayList<FileInfo> fileInfoList = new ArrayList<FileInfo>();
		
	    this.proxy = new PagingModelMemoryProxy(fileInfoList);
	    this.loader = new BasePagingLoader<PagingLoadResult<ModelData>>(proxy);
	    this.loader.setRemoteSort(true);
	   ListStore<FileInfo> fileList = new ListStore<FileInfo>(loader);
	
	    final PagingToolBar toolBar = new PagingToolBar(NRTRDE.constants.pageSize());  
	    toolBar.bind(loader);  
		ArrayList<ColumnConfig> configs = new ArrayList<ColumnConfig>();  
		  
		ColumnConfig column = new ColumnConfig();    
		column.setId("fileName");    
		column.setHeader("FileName");
		column.setWidth(400);
 		configs.add(column);
 		 	
 	
		column = new ColumnConfig();    
		column.setId("dateModified");    
		column.setHeader("Date Modified");
		column.setDateTimeFormat(DateTimeFormat.getFormat("dd/MM/yyyy"));  
		column.setWidth(200);
    	configs.add(column);
    	
		
		  
		  
				
		 LabelField fromLabel = new LabelField("From Date:");
		 this.fromDate = new DateField();
		 fromDate.setFieldLabel("From Date");
		 this.toDate = new DateField();
		 LabelField toLabel = new LabelField("To Date:");
		 toDate.setFieldLabel("To Date");
		 		   
		 this.commercial.setBoxLabel("Commercial");  
		 this.commercial.setValue(false);  
			  
		   
		 this.nonCommercial.setBoxLabel("Non-Commercial");
		 this.nonCommercial.setValue(true);
		 RadioGroup radioGroup = new RadioGroup();			  
		 radioGroup.setFieldLabel("Operator Type");  
		 radioGroup.add(this.commercial);  
		 radioGroup.add(nonCommercial);
		 
		 
		 ListStore<OperatorValueObject> store = new ListStore<OperatorValueObject>();
		 store.add(NRTRDE.nonCommercialList);
		 LabelField operLabel= new LabelField("Operator:"); 
		 operList.setStore(store);
		 operList.setTriggerAction(TriggerAction.ALL);   
		 operList.setFieldLabel("Operator:");
		 operList.setDisplayField("value");		
		
		 VerticalPanel buttonPanel = new VerticalPanel();
		 HorizontalPanel hp1 = new HorizontalPanel();
		 hp1.setSpacing(2);
		 hp1.add(fromLabel);
		 hp1.add(fromDate);
		 hp1.add(toLabel);
		 hp1.add(toDate);
		
		 HorizontalPanel hp2 = new HorizontalPanel();
		 hp2.setSpacing(3);
		 hp2.add(this.commercial);
		 hp2.add(this.nonCommercial);
		 hp2.add(operLabel);
		 hp2.add(operList);
		 hp2.add(generateButton);
		
		
			
		 com.google.gwt.user.client.ui.Grid gridLayout = new  com.google.gwt.user.client.ui.Grid(2,1);
		 gridLayout.setWidget(0,0,hp1);
		 gridLayout.setWidget(1,0,hp2);
		 buttonPanel.add(gridLayout);
		
		 ColumnModel cm = new ColumnModel(configs);
		 Grid<FileInfo> grid = new Grid<FileInfo>(fileList, cm);   
		 grid.setStyleAttribute("borderTop", "none");   
		 grid.setAutoExpandColumn("fileName");   
		 grid.setBorders(true);   
		 grid.setStripeRows(true);
	
		 
		 cp = new ContentPanel();  
		 cp.setFrame(true);  
		 cp.setHeading("Generate");			
		 cp.setButtonAlign(HorizontalAlignment.CENTER);
		 cp.setLayout(new FitLayout());
		 cp.setTopComponent(buttonPanel);
		 cp.add(grid);
		 cp.setBottomComponent(toolBar);
		 cp.setSize(NRTRDE.TABLE_WIDTH,NRTRDE.TABLE_HEIGHT);
		 
		 setLayout(new FitLayout());
		 add(cp);		 		 
		 layout();
	
	}


	public Button getGenerateButton() {
		// TODO Auto-generated method stub
		return (this.generateButton);
	}

	public ComboBox<OperatorValueObject> getOperListBox() {
		// TODO Auto-generated method stub
		return(this.operList);
	}


	public Radio getCommercialRadio() {
		// TODO Auto-generated method stub
		return (this.commercial);
	}


	public Radio getNonCommercialRadio() {
		// TODO Auto-generated method stub
		return (this.nonCommercial);
	}


	@Override
	public Date getFromDate() {
		// TODO Auto-generated method stub
		return(this.fromDate.getValue());
	}


	@Override
	public Date getToDate() {
		// TODO Auto-generated method stub
		return(this.toDate.getValue());
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



	

}
