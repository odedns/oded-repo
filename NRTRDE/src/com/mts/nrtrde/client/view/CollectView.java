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
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Element;
import com.mts.nrtrde.client.FileInfo;
import com.mts.nrtrde.client.NRTRDE;
import com.mts.nrtrde.client.OperatorValueObject;
import com.mts.nrtrde.client.presenter.CollectPresenter;

/**
 * @author Oded Nissan
 *
 */
public class CollectView extends GridLayoutContainer  implements CollectPresenter.Display {
	VerticalPanel panel;
	private Button collectButton;
	private PagingModelMemoryProxy proxy; 
	private PagingLoader<PagingLoadResult<ModelData>> loader;
	
	ComboBox<OperatorValueObject> operList;
	
	public CollectView()
	{
		 this.operList = new ComboBox<OperatorValueObject>();
		 
		 this.collectButton = new Button("Collect");
		

		 
	}
	
		

	@Override
	protected void onRender(Element parent, int index) {
		// TODO Auto-generated method stub
		super.onRender(parent, index);
		ArrayList<FileInfo> fileInfoList = new ArrayList<FileInfo>();
		
	    this.proxy = new PagingModelMemoryProxy(fileInfoList);
	    this.loader = new BasePagingLoader<PagingLoadResult<ModelData>>(proxy);  
	    loader.setRemoteSort(true);  
	
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
    	
		
		  
		  
				
		 
		 ListStore<OperatorValueObject> store = new ListStore<OperatorValueObject>();
		 store.add(NRTRDE.allOperList);
		 
		 LabelField operLabel= new LabelField("Operator:"); 
		 operList.setStore(store);
		 operList.setTriggerAction(TriggerAction.ALL);   
		 operList.setFieldLabel("Operator:");
		 operList.setDisplayField("value");
		 operList.setValue(store.getAt(0));

		
		 HorizontalPanel buttonPanel = new HorizontalPanel();
		 
		 buttonPanel.setSpacing(5);
		 
		 
		 buttonPanel.add(operLabel);
		 buttonPanel.add(operList);
		 buttonPanel.add(collectButton);
		
		
		 ColumnModel cm = new ColumnModel(configs);
		 Grid<FileInfo> grid = new Grid<FileInfo>(fileList, cm);   
		 grid.setStyleAttribute("borderTop", "none");   
		 grid.setAutoExpandColumn("fileName");   
		 grid.setBorders(true);   
		 grid.setStripeRows(true);
	
		
		 cp = new ContentPanel();  
		 cp.setFrame(true);  
		 cp.setHeading("Generate");
		 cp.setSize(NRTRDE.TABLE_WIDTH,NRTRDE.TABLE_HEIGHT);
		 cp.setButtonAlign(HorizontalAlignment.CENTER);  
		 cp.setLayout(new FitLayout());
		 cp.setTopComponent(buttonPanel);
		 cp.setBottomComponent(toolBar);
		 cp.add(grid);
		
		
		 add(cp);
		 setLayout(new FitLayout());
		 layout();
		    
		
		  
		 
			
			
	}



	public Button getCollectButton() {
		// TODO Auto-generated method stub
		return (this.collectButton);
	}





	public ComboBox<OperatorValueObject> getOperListBox() {
		// TODO Auto-generated method stub
		return(this.operList);
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
