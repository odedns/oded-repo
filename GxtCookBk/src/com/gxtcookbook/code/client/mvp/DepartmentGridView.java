package com.gxtcookbook.code.client.mvp;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Record.RecordUpdate;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.store.StoreEvent;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid.ClicksToEdit;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.RowEditor;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.gxtcookbook.code.client.data.LiveStore;
import com.gxtcookbook.code.client.ext.ActionMenu;
import com.gxtcookbook.code.client.mvp.DepartmentPresenter.DepartmentDisplay;
import com.gxtcookbook.code.server.comet.event.MvpDepartmentUpdate;

public class DepartmentGridView extends DepartmentView {
	
	private static DepartmentGridView instance;
	
	public static void init(){
		instance = (instance == null ? new DepartmentGridView() : instance);
	}
	
	public static DepartmentGridView get(){
		init();
		return instance;
	}
	
	private ActionMenu ctxMenu;
	private Grid<BeanModel> grid;
	
	protected DepartmentGridView(){
		super();			
        
        List<ColumnConfig> columns = getColumns();
		ColumnModel columnModel = new ColumnModel(columns);
		grid = new Grid<BeanModel>(store, columnModel);
		grid.setStripeRows(true);
		grid.setLoadMask(true);
		grid.getSelectionModel().setSelectionMode(SelectionMode.MULTI);
		
		RowEditor<BeanModel> rowEditor = new RowEditor<BeanModel>();
		rowEditor.getMessages().setSaveText("Ok");
		rowEditor.setClicksToEdit(ClicksToEdit.TWO);
		grid.addPlugin(rowEditor);
		
		ctxMenu = new ActionMenu();
		grid.setContextMenu(ctxMenu);
				
		grid.addListener(MvpEvents.Attach, new Listener<GridEvent<BeanModel>>() {
			@Override
			public void handleEvent(GridEvent<BeanModel> evt) {
				if(grid.getStore().getModels().size() <= 0){
					grid.getStore().getLoader().load();
				}				
			}
		});	
		
		// Whenever there are local changes
		// notify the app via the event bus!
		grid.getStore().addListener(Store.Update, new Listener<StoreEvent<BeanModel>>() {
			@Override
			public void handleEvent(StoreEvent<BeanModel> evt) {
				if(RecordUpdate.EDIT.equals(evt.getOperation())){
					BaseAction actn = (BaseAction) display().saveAction();
					if(actn != null){
						actn.setEnabled(true);
					}
					MvpEvents.getBus().fireEvent(MvpEvents.DepartmentChanged, evt);
				}										
			}
		});
		
		MvpEvents.getBus().addListener(MvpEvents.SaveDepartment, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent evt) {
				grid.mask("Saving ...");
			};
		});
		MvpEvents.getBus().addListener(MvpEvents.DepartmentSaveERR, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent evt) {
				grid.unmask();
			};
		});
		MvpEvents.getBus().addListener(MvpEvents.DepartmentSaveOK, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent evt) {
				grid.unmask();
			};
		});				
		
		// Used for Comet
		liveStore.addObserver(new LiveStore.UpdateObserver() {	
			@Override
			public void broadcastReceived(ListStore<? extends ModelData> store, MvpDepartmentUpdate updateEvt) {
				if(grid.isVisible()){
					grid.getView().refresh(true);
				}
			}
		});
	}
	
	protected List<ColumnConfig> getColumns(){
		List<ColumnConfig> columns = new ArrayList<ColumnConfig>();

        ColumnConfig column = new ColumnConfig("name", "Name", 180);
        TextField<String> txtField = new TextField<String>();
        txtField.setAllowBlank(false);
        column.setEditor(new CellEditor(txtField));
        columns.add(column);  

        column = new ColumnConfig("code", "Code", 65);
        txtField = new TextField<String>();
        txtField.setAllowBlank(false);
        txtField.setMaxLength(3);
        column.setEditor(new CellEditor(txtField));
        columns.add(column);    

        return columns;
	}
	
	@Override
	public DepartmentDisplay display() {
		init();
		return instance;
	}

	@Override
	public Menu ctxMenu() {
		return ctxMenu;
	}

	@Override
	public Component viewComponent() {
		return grid;
	}
	
	@Override
	public ListStore<BeanModel> store() {
		return store;
	}
}
