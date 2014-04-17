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
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.google.gwt.user.client.Element;
import com.mts.nrtrde.client.AlertInfo;
import com.mts.nrtrde.client.NRTRDE;
import com.mts.nrtrde.client.presenter.AlertsPresenter;

/**
 * @author Oded Nissan
 *
 */
public class AlertsView extends GridLayoutContainer  implements AlertsPresenter.Display {
	VerticalPanel panel;

	private PagingModelMemoryProxy proxy; 
	private PagingLoader<PagingLoadResult<ModelData>> loader;
	private ColumnModel cm;
	private LabelField alertLabel;
	 
	
	public AlertsView()
	{
		this.alertLabel = new LabelField();
	}
	
		

	@Override
	protected void onRender(Element parent, int index) {
		// TODO Auto-generated method stub
		super.onRender(parent, index);
		//ArrayList<FileInfo> fileInfoList = getFileInfoList();
		ArrayList<AlertInfo> fileInfoList = new ArrayList<AlertInfo>();
		
	    this.proxy = new PagingModelMemoryProxy(fileInfoList);
	    proxy.setData(fileInfoList);
	    this.loader = new BasePagingLoader<PagingLoadResult<ModelData>>(proxy);  
	    loader.setRemoteSort(true);  
	    ListStore<AlertInfo> fileList = new ListStore<AlertInfo>(loader);
	
	     final PagingToolBar toolBar = new PagingToolBar(NRTRDE.constants.pageSize());  
	     toolBar.bind(loader);  
	 
	    GridCellRenderer<AlertInfo> renderer = new GridCellRenderer<AlertInfo>() {

			@Override
			public Object render(AlertInfo model, String property,
					ColumnData config, int rowIndex, int colIndex,
					ListStore<AlertInfo> store, Grid<AlertInfo> grid) {
				// TODO Auto-generated method stub
				int val = model.get(property);
				String style = model.get(property + "Colored") ? "red" : "black";
				return "<span qtitle='" + cm.getColumnById(property).getHeader() + "' qtip='" + val  
	            + "' style='color:" + style + "'>" + val + "</span>";  
				
			}
		}; 
		
		ArrayList<ColumnConfig> configs = new ArrayList<ColumnConfig>();  
		  
		ColumnConfig column = new ColumnConfig();    
		column.setId("IMSI");    
		column.setHeader("IMSI");
		column.setWidth(100);
 		configs.add(column);
 		 	
 	
		column = new ColumnConfig();    
		column.setId("deviationDate");    
		column.setHeader("Date of Deviation");
		column.setWidth(150);
		configs.add(column);
    	
    	column = new ColumnConfig();    
		column.setId("threshold");    
		column.setHeader("Threshold Timespan");
		column.setWidth(150);
		configs.add(column);
		
    	column = new ColumnConfig();    
		column.setId("volume");
		column.setWidth(100);
		column.setHeader("Volume");
		column.setRenderer(renderer);
		configs.add(column);
		
		
    	column = new ColumnConfig();    
		column.setId("usage");
		column.setWidth(100);
		column.setHeader("Usage");
		column.setRenderer(renderer);
		configs.add(column);
    	
		column = new ColumnConfig();    
		column.setId("sms"); 
		column.setWidth(150);
		column.setHeader("SMS");
		column.setRenderer(renderer);
		configs.add(column);
		
		
		column = new ColumnConfig();    
		column.setId("vendors");    
		column.setHeader("Vendors");
		column.setWidth(200);
		configs.add(column);
    	
		 this.cm = new ColumnModel(configs);
		 Grid<AlertInfo> grid = new Grid<AlertInfo>(fileList, cm);   
		 grid.setStyleAttribute("borderTop", "none");   
		 grid.setAutoExpandColumn("vendors");   
		 grid.setBorders(true);   
		 grid.setStripeRows(true);
	
		
		 cp = new ContentPanel();  
		 cp.setFrame(true);  
		 cp.setHeading("Alerts");
		 cp.setSize(NRTRDE.TABLE_WIDTH,NRTRDE.TABLE_HEIGHT);
	
		 cp.setButtonAlign(HorizontalAlignment.CENTER);  
		 cp.setLayout(new FitLayout());
		 cp.setTopComponent(alertLabel);
		 cp.setBottomComponent(toolBar);
		 cp.add(grid);
		 
	
		 add(cp);
		 setLayout(new FitLayout());
		 layout();
	
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
	public LabelField getAlertLabel() {
		// TODO Auto-generated method stub
		return this.alertLabel;
	}

}
