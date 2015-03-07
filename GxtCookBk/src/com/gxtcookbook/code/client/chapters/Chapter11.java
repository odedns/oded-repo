package com.gxtcookbook.code.client.chapters;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelFactory;
import com.extjs.gxt.ui.client.data.BeanModelLookup;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Record;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ButtonBar;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.CheckBoxSelectionModel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.SeparatorMenuItem;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gxtcookbook.code.client.GxtCookBk;
import com.gxtcookbook.code.client.data.DataCenter;
import com.gxtcookbook.code.client.data.Response;
import com.gxtcookbook.code.client.ext.ActionButton;
import com.gxtcookbook.code.client.ext.ActionMenu;
import com.gxtcookbook.code.client.mvp.DepartmentGridView;
import com.gxtcookbook.code.client.mvp.DepartmentListView;
import com.gxtcookbook.code.client.mvp.DepartmentPresenter;
import com.gxtcookbook.code.client.mvp.DepartmentView;
import com.gxtcookbook.code.server.model.persisted.Student;

public class Chapter11 extends ChapterAdapter {
	
	private static Chapter11 instance;
	private static LayoutContainer centerPanel = GxtCookBk.getAppCenterPanel();
	
	public Chapter11() {
		super();
	}
	
	public static Chapter11 get(){
		instance = (instance == null ? new Chapter11() : instance);
		return instance;
	}

	@Override
	protected String getTitle() {
		return "Advanced Tips";
	}

	@Override
	protected List<Recipe> applyTheseRecipes() {
		List<Recipe> recipes = new LinkedList<Recipe>();
		
		Recipe persist = new Recipe("Client/Server Persistence") {
			
			private Grid<BeanModel> grid;
			
			private List<ColumnConfig> getColumnCfgs() {
				// A list for the column configurations
				List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
			
				// Create columns as ColumnConfig objects.
				// having id's pointing to fields in the
				// server-side class of the object to be loaded				
				ColumnConfig column = new ColumnConfig("firstName", "First Name", 85);
				// Edit this column with a TextField
				TextField<String> txtField = new TextField<String>();
				txtField.setAllowBlank(false);
				column.setEditor(new CellEditor(txtField));
				configs.add(column);
			
				column = new ColumnConfig("lastName", "Last Name", 85);
				// Edit this column with a TextField
				txtField = new TextField<String>();
				txtField.setAllowBlank(false);
				column.setEditor(new CellEditor(txtField));				
				configs.add(column);
			
				column = new ColumnConfig("emailId", "Email Addr", 120);
				// Edit this column with a TextField
				txtField = new TextField<String>();
				txtField.setAllowBlank(false);
				txtField.setRegex(DataCenter.VType.EMAIL.get());
				column.setEditor(new CellEditor(txtField));				
				configs.add(column);
				
				column = new ColumnConfig("department", "Department", 150);
				// Edit this column with a Combo
				ComboBox<BeanModel> combo = new ComboBox<BeanModel>();			    
				combo.setValueField("id");
				combo.setDisplayField("code");
				combo.setAllowBlank(false);				
				combo.setTriggerAction(TriggerAction.ALL);	
				String storeKey = DataCenter.StoreKeys.DEPARTMENT_LIST_STORE.encode();
				@SuppressWarnings("unchecked")
				ListStore<BeanModel> store = (ListStore<BeanModel>) Registry.get(storeKey);
				combo.setStore(store);
				column.setEditor(new CellEditor(combo));				
				configs.add(column);
				
				column = new ColumnConfig("address", "Address", 165);
				// Edit this column with a TextField
				txtField = new TextField<String>();
				column.setEditor(new CellEditor(txtField));				
				configs.add(column);
			
				return configs;
			}
			
			private void runDelete(){
				final List<BeanModel> selection = grid.getSelectionModel().getSelection();
				if(selection.size() >= 1){
					grid.mask("Attempting Delete ..");
					ArrayList<Long> losers = new ArrayList<Long>();
					for (BeanModel model : selection) {
						Student student = (Student) model.getBean();
						losers.add(student.getId());
					}
					
					AsyncCallback<Response> callback = new AsyncCallback<Response>() {
						@Override
						public void onFailure(Throwable caught) {
							grid.unmask();
							Info.display("RPC Error", caught.getMessage());
						}
						
						@Override
						public void onSuccess(Response result) {
							grid.unmask();
							if(Response.STATUS_OK.equals(result.getStatus())){
								for (BeanModel model : selection) {
									grid.getStore().remove(model);
								}
			        			grid.getStore().commitChanges();
			        		}else{
			        			Info.display("Server Error", result.getMessages().toString());
			        		}
						}
					};
					
					DataCenter.get().rpcService().deleteStudents(losers, callback);
				}
			}
			
			private void runSave(){
				if(grid.getStore().getModifiedRecords().size() >= 1){
					grid.mask("Saving ...");
					
					ArrayList<Student> changes = new ArrayList<Student>();
			        for (Record record : grid.getStore().getModifiedRecords()) {
			        	Student student = (Student) ((BeanModel) record.getModel()).getBean();
			        	changes.add(student);			            
			        }
			        
					DataCenter.get().rpcService().saveStudents(changes, new AsyncCallback<Response>() {
			        	@Override
			        	public void onFailure(Throwable caught) {
			        		grid.unmask();
			        		Info.display("RPC Error", caught.getMessage());
			        	}
			        	
			        	@Override
			        	public void onSuccess(Response result) {			        		
			        		if(Response.STATUS_OK.equals(result.getStatus())){
								grid.getStore().commitChanges();
								grid.getStore().getLoader().load();
			        		}else{
			        			Info.display("Server Error", result.getMessages().toString());
			        		}
			        	}
					});										
				}
			}
			
			@Override
			public void onApply() {				
				// A Registry based repo of stores
				DataCenter.init();	
				
				// Create and configure the grid
				List<ColumnConfig> configs = getColumnCfgs();
				CheckBoxSelectionModel<BeanModel> selectionMdl = new CheckBoxSelectionModel<BeanModel>();
				configs.add(0, selectionMdl.getColumn());
								
				ColumnModel cm = new ColumnModel(configs);
				String storeKey = DataCenter.StoreKeys.STUDENT_LIST_STORE.encode();
				@SuppressWarnings("unchecked")
				final ListStore<BeanModel> store = (ListStore<BeanModel>) Registry.get(storeKey);
				grid = new EditorGrid<BeanModel>(store, cm);
				
				grid.addPlugin(selectionMdl);
				grid.setSelectionModel(selectionMdl);
				
				grid.setBorders(true);
				grid.setSize(650, 135);
				grid.setStripeRows(true);
				grid.setLoadMask(true);
				grid.setAutoExpandColumn("emailId");				
				grid.setStyleAttribute("marginBottom", "15px");
				
				grid.addListener(Events.Attach, new Listener<GridEvent<BeanModel>>() {
					@Override
					public void handleEvent(GridEvent<BeanModel> evt) {
						store.getLoader().load();
					}
				});
			
				// show it up, equivalent to
				// RootPanel.get().add(grid)
				centerPanel.add(grid);
				
				ButtonBar btnBar = new ButtonBar();
				btnBar.add(new Button("Add", new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent evt) {
						BeanModelFactory modelFtry = BeanModelLookup.get().getFactory(Student.class);
						BeanModel model = modelFtry.createModel(new Student());
						EditorGrid<BeanModel> editorGrid = (EditorGrid<BeanModel>) grid;
						editorGrid.stopEditing();  
						editorGrid.getStore().insert(model, 0);  
						editorGrid.startEditing(grid.getStore().indexOf(model), 0);
					}
				}));
				
				btnBar.add(new Button("Delete", new SelectionListener<ButtonEvent>() {					
					@Override
					public void componentSelected(ButtonEvent evt) {
						runDelete();
					}
				}));
				
				btnBar.add(new Button("Save Changes", new SelectionListener<ButtonEvent>() {					
					@Override
					public void componentSelected(ButtonEvent evt) {						
						runSave();					
					}
				}));
				
				// show it up, equivalent to
				// RootPanel.get().add(btnBar)
				centerPanel.add(btnBar);
				
			}
		};
		recipes.add(persist);
		
		Recipe novelUi = new Recipe("A Novel UI with MVP, Actions, and a Bus") {	
			
			private ToolBar topBar;
			private ContentPanel ctPanel;
			
			private void installView(DepartmentView view){
				ctPanel.removeAll();
			    ctPanel.layout();
			    
				topBar.removeAll();
				topBar.layout();
				if(view instanceof DepartmentGridView){
					ActionButton refreshBtn = new ActionButton(DepartmentGridView.get().refreshAction());
			        topBar.add(refreshBtn);
			        topBar.add(new SeparatorToolItem());
			        ActionButton saveBtn = new ActionButton(DepartmentGridView.get().saveAction());
			        topBar.add(saveBtn);			        
			        
			        Menu ctxMenu = DepartmentGridView.get().ctxMenu();
			        ctxMenu.removeAll();
			        ((ActionMenu) ctxMenu).add(DepartmentGridView.get().refreshAction());
			        ctxMenu.add(new SeparatorMenuItem());
			        ((ActionMenu) ctxMenu).add(DepartmentGridView.get().saveAction());
			        			        
			        DepartmentPresenter.get().setDisplay(DepartmentGridView.get().display());			        			        
					ctPanel.add(DepartmentGridView.get().viewComponent());					
				}else if(view instanceof DepartmentListView){
					DepartmentPresenter.get().setDisplay(DepartmentListView.get().display());			        			        
					ctPanel.add(DepartmentListView.get().viewComponent());
				}
				
				addControlBtns();
				topBar.layout();
				ctPanel.layout();
			}
			
			private void asGrid(){
				installView(DepartmentGridView.get());
			}
			
			private void asList(){
				installView(DepartmentListView.get());
			}
			
			private void addControlBtns() {
				topBar.add(new FillToolItem());
				topBar.add(new Button("As Grid", new SelectionListener<ButtonEvent>() {					
					@Override
					public void componentSelected(ButtonEvent evt) {
						if(DepartmentPresenter.get().display().equals(DepartmentGridView.get().display())){
							return;
						}
						asGrid();				
					}
				}));
				topBar.add(new Button("As List", new SelectionListener<ButtonEvent>() {					
					@Override
					public void componentSelected(ButtonEvent evt) {
						if(DepartmentPresenter.get().display().equals(DepartmentListView.get().display())){
							return;
						}
						asList();				
					}
				}));
			}
			
			@Override
			public void onApply() {				
				ctPanel = new ContentPanel();
				ctPanel.setLayout(new FitLayout());
				ctPanel.setHeaderVisible(false);
				ctPanel.setScrollMode(Scroll.NONE);
				ctPanel.setSize(350, 175);	
				
				topBar = new ToolBar();
				ctPanel.setTopComponent(topBar);
			    
				// show it up, equivalent to
				// RootPanel.get().add(ctPanel)
				centerPanel.add(ctPanel);
																
				asGrid();
			}
		};
		recipes.add(novelUi);
		
		Recipe brwsrHistory = new BrowserHistoryRecipe();
		recipes.add(brwsrHistory);
	
		Recipe push = new Recipe("Realtime Server Push") {
			
			private ToolBar topBar;
			private ContentPanel ctPanel;
			
			private void installView(DepartmentView view){
				ctPanel.removeAll();
		        ctPanel.layout();
		        
				topBar.removeAll();
				topBar.layout();
				if(view instanceof DepartmentGridView){
					ActionButton refreshBtn = new ActionButton(DepartmentGridView.get().refreshAction());
			        topBar.add(refreshBtn);
			        topBar.add(new SeparatorToolItem());
			        ActionButton saveBtn = new ActionButton(DepartmentGridView.get().saveAction());
			        topBar.add(saveBtn);			        
			        
			        Menu ctxMenu = DepartmentGridView.get().ctxMenu();
			        ctxMenu.removeAll();
			        ((ActionMenu) ctxMenu).add(DepartmentGridView.get().refreshAction());
			        ctxMenu.add(new SeparatorMenuItem());
			        ((ActionMenu) ctxMenu).add(DepartmentGridView.get().saveAction());
			        			        
			        DepartmentPresenter.get().setDisplay(DepartmentGridView.get().display());			        			        
					ctPanel.add(DepartmentGridView.get().viewComponent());					
				}else if(view instanceof DepartmentListView){
					DepartmentPresenter.get().setDisplay(DepartmentListView.get().display());			        			        
					ctPanel.add(DepartmentListView.get().viewComponent());
				}
				
				addControlBtns();
				topBar.layout();
				ctPanel.layout();
			}
			
			private void asGrid(){
				installView(DepartmentGridView.get());
			}
			
			private void asList(){
				installView(DepartmentListView.get());
			}

			private void addControlBtns() {
				topBar.add(new FillToolItem());
				topBar.add(new Button("As Grid", new SelectionListener<ButtonEvent>() {					
					@Override
					public void componentSelected(ButtonEvent evt) {
						if(DepartmentPresenter.get().display().equals(DepartmentGridView.get().display())){
							return;
						}
						asGrid();				
					}
				}));
				topBar.add(new Button("As List", new SelectionListener<ButtonEvent>() {					
					@Override
					public void componentSelected(ButtonEvent evt) {
						if(DepartmentPresenter.get().display().equals(DepartmentListView.get().display())){
							return;
						}
						asList();				
					}
				}));
			}
			
			@Override
			public void onApply() {				
				ctPanel = new ContentPanel();
				ctPanel.setLayout(new FitLayout());
				ctPanel.setHeaderVisible(false);
				ctPanel.setScrollMode(Scroll.NONE);
				ctPanel.setSize(350, 175);	
				
				topBar = new ToolBar();
				ctPanel.setTopComponent(topBar);
		        
				// show it up, equivalent to
				// RootPanel.get().add(ctPanel)
				centerPanel.add(ctPanel);
																
				asGrid();
			}
		};
		recipes.add(push);
		
		return(recipes);
	}
	private class BrowserHistoryRecipe extends Recipe implements ValueChangeHandler<String>{
		
		private ToolBar topBar;
		private ContentPanel ctPanel;
		
		public BrowserHistoryRecipe() {
			super("History & View Transitions");
		}
		
		@Override
		public void onValueChange(ValueChangeEvent<String> evt) {
			String token = evt.getValue();
			if(token != null && token.equals("list")){
				asList();
			} else if(token != null && token.equals("grid")){
				asGrid();
			}
		}
		
		@Override
		public void onApply() {
			History.addValueChangeHandler(this);
			
			ctPanel = new ContentPanel();
			ctPanel.setLayout(new FitLayout());
			ctPanel.setHeaderVisible(false);
			ctPanel.setScrollMode(Scroll.NONE);
			ctPanel.setSize(350, 175);	
			
			topBar = new ToolBar();
			ctPanel.setTopComponent(topBar);
		    
			// show it up, equivalent to
			// RootPanel.get().add(ctPanel)
			centerPanel.add(ctPanel);
			
			begin();
		}
		
		public void begin(){
			if("".equals(History.getToken())){
				History.newItem("grid");
			} else {
				History.fireCurrentHistoryState();
			}
		}
		
		private void installView(DepartmentView view){
			ctPanel.removeAll();
		    ctPanel.layout();
		    
			topBar.removeAll();
			topBar.layout();
			if(view instanceof DepartmentGridView){
				ActionButton refreshBtn = new ActionButton(DepartmentGridView.get().refreshAction());
		        topBar.add(refreshBtn);
		        topBar.add(new SeparatorToolItem());
		        ActionButton saveBtn = new ActionButton(DepartmentGridView.get().saveAction());
		        topBar.add(saveBtn);			        
		        
		        Menu ctxMenu = DepartmentGridView.get().ctxMenu();
		        ctxMenu.removeAll();
		        ((ActionMenu) ctxMenu).add(DepartmentGridView.get().refreshAction());
		        ctxMenu.add(new SeparatorMenuItem());
		        ((ActionMenu) ctxMenu).add(DepartmentGridView.get().saveAction());
		        			        
		        DepartmentPresenter.get().setDisplay(DepartmentGridView.get().display());			        			        
				ctPanel.add(DepartmentGridView.get().viewComponent());					
			}else if(view instanceof DepartmentListView){
				DepartmentPresenter.get().setDisplay(DepartmentListView.get().display());			        			        
				ctPanel.add(DepartmentListView.get().viewComponent());
			}
			
			addControlBtns();
			topBar.layout();
			ctPanel.layout();
		}
		
		private void asGrid(){
			installView(DepartmentGridView.get());
		}
		
		private void asList(){
			installView(DepartmentListView.get());
		}
		
		private void addControlBtns() {
			topBar.add(new FillToolItem());
			topBar.add(new Button("As Grid", new SelectionListener<ButtonEvent>() {					
				@Override
				public void componentSelected(ButtonEvent evt) {
					if(DepartmentPresenter.get().display().equals(DepartmentGridView.get().display())){
						return;
					}
					History.newItem("grid");				
				}
			}));
			topBar.add(new Button("As List", new SelectionListener<ButtonEvent>() {					
				@Override
				public void componentSelected(ButtonEvent evt) {
					if(DepartmentPresenter.get().display().equals(DepartmentListView.get().display())){
						return;
					}
					History.newItem("list");				
				}
			}));
		}
		
		
	}

}
