package com.gxtcookbook.code.client.chapters;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelReader;
import com.extjs.gxt.ui.client.data.ListLoadConfig;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.ListLoader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.PagingModelMemoryProxy;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.GroupingStore;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.AggregationRenderer;
import com.extjs.gxt.ui.client.widget.grid.AggregationRowConfig;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.CheckBoxSelectionModel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid.ClicksToEdit;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.GridGroupRenderer;
import com.extjs.gxt.ui.client.widget.grid.GroupColumnData;
import com.extjs.gxt.ui.client.widget.grid.GroupSummaryView;
import com.extjs.gxt.ui.client.widget.grid.GroupingView;
import com.extjs.gxt.ui.client.widget.grid.HeaderGroupConfig;
import com.extjs.gxt.ui.client.widget.grid.RowEditor;
import com.extjs.gxt.ui.client.widget.grid.RowNumberer;
import com.extjs.gxt.ui.client.widget.grid.SummaryColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.SummaryRenderer;
import com.extjs.gxt.ui.client.widget.grid.SummaryType;
import com.extjs.gxt.ui.client.widget.grid.filters.DateFilter;
import com.extjs.gxt.ui.client.widget.grid.filters.GridFilters;
import com.extjs.gxt.ui.client.widget.grid.filters.NumericFilter;
import com.extjs.gxt.ui.client.widget.grid.filters.StringFilter;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gxtcookbook.code.client.GxtCookBk;
import com.gxtcookbook.code.client.RemoteGateway;
import com.gxtcookbook.code.client.RemoteGatewayAsync;
import com.gxtcookbook.code.client.data.LocalData;
import com.gxtcookbook.code.client.data.Stock;
import com.gxtcookbook.code.server.model.Customer;


public class Chapter7 extends ChapterAdapter {
	
	private static Chapter7 instance;
	
	public static Chapter7 get(){
		instance = instance == null ? new Chapter7() : instance;
		return instance;
	}
	
	protected Chapter7(){
		super();
	}

	@Override
	protected String getTitle() {
		return "The Venerable GridPanel";
	}

	@Override
	protected List<Recipe> applyTheseRecipes() {
		List<Recipe> recipes = new LinkedList<Recipe>();

		Recipe basicGrid = new Recipe("Basic Grid: Numbered Rows, Re-orderable Columns") {
			@Override
			public void onApply() {
				// A list for the column configurations
				List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
				
				// This how you would make a normal column,
				// give it an id, label, and initial width
				// the id is a property in the bean you are trying to display
				ColumnConfig column = new ColumnConfig("name", "Company", 200);
				configs.add(column);
				
				column = new ColumnConfig("symbol", "Symbol", 75);
				configs.add(column);
				
				column = new ColumnConfig("last", "Last", 50);				
				configs.add(column);
				
				column = new ColumnConfig("change", "Change", 100);
				configs.add(column);
				
				column = new ColumnConfig("date", "Last Updated", 150);
				configs.add(column);
				
				// An automatic serial number column,
				// RowNumberer is a special ColumnConfig
				RowNumberer serialNum = new RowNumberer();
				serialNum.setWidth(30);
				// make it the first column!
				configs.add(0, serialNum);
				
				// Populate the store with data
				ListStore<ModelData> store = new ListStore<ModelData>();
				store.add(LocalData.getStocks());
				
				// Create the grid with a ColumnModel instantiated
				// from our list of column configurations, and a store
				ColumnModel cm = new ColumnModel(configs);
				Grid<ModelData> grid = new Grid<ModelData>(store, cm);
				
				// RowNumberer is a special ColumnConfig,
				// actually a plugin !!
				grid.addPlugin(serialNum);
				
				// Some cosmetics on our beloved grid
				grid.setSize(600, 300);
				grid.setBorders(true);
				// show color strips for alternate rows
				grid.setStripeRows(true);
				// separate columns with vertical lines 
				grid.setColumnLines(true);
				// allow re-ordering of columns
				//grid.setColumnReordering(true);
				// expand the 'name' as much as possible
				grid.setAutoExpandColumn("name");
				
				// show it up, equivalent to
				// RootPanel.get().add(grid)
				
				GxtCookBk.getAppCenterPanel().add(grid);
			}
		};
		recipes.add(basicGrid);

		Recipe cellRendering = new Recipe("Formating Cell Data") {
			@Override
			public void onApply() {
				// A list for the column configurations
				List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
			
				// Create columns as ColumnConfig objects, add to the above list
				ColumnConfig column = new ColumnConfig("name", "Company", 200);
				configs.add(column);
			
				column = new ColumnConfig("symbol", "Symbol", 75);
				configs.add(column);
			
				column = new ColumnConfig("last", "Last", 75);
				// format value to show up right-aligned
				column.setAlignment(HorizontalAlignment.RIGHT);
				// format value to show 0.46 instead of 0.460000000000
				// and denote as a monetary value with a currency symbol
				column.setNumberFormat(NumberFormat.getCurrencyFormat());
				configs.add(column);
			
				column = new ColumnConfig("change", "Change", 85);
				column.setAlignment(HorizontalAlignment.RIGHT);
				// Hmmm.. a custom formatting solves all!
				// show a standard decimal value,
				// and green if positive else red. 
				column.setRenderer(new GridCellRenderer<ModelData>() {
					@Override
					public Object render(ModelData model, String property,
							ColumnData config, int rowIndex, int colIndex,
							ListStore<ModelData> store, Grid<ModelData> grid) {
			
						double val = (Double) model.get(property);
						String style = val < 0 ? "red" : "green";
						String v = NumberFormat.getFormat("0.00").format(val);
			
						return "<span style='font-weight: bold;color:" + style
								+ "'>" + v + "</span>";
					}
				});
				configs.add(column);
			
				column = new ColumnConfig("date", "Last Updated", 125);
				column.setAlignment(HorizontalAlignment.RIGHT);
				// format date value as Oct 1, 1960
				column.setDateTimeFormat(DateTimeFormat.getFormat("MMM dd, yyyy"));
				configs.add(column);
			
				// Populate store
				ListStore<ModelData> store = new ListStore<ModelData>();
				store.add(LocalData.getStocks());
			
				// Create and configure the grid.
				ColumnModel cm = new ColumnModel(configs);
				Grid<ModelData> grid = new Grid<ModelData>(store, cm);
				grid.setBorders(true);
				grid.setSize(600, 300);
				grid.setAutoExpandColumn("name");
			
				// show it up, equivalent to
				// RootPanel.get().add(grid)
			
				GxtCookBk.getAppCenterPanel().add(grid);
			}
		};
		recipes.add(cellRendering);

		Recipe colGrouping = new Recipe("Grouping Column Headers") {
			@Override
			public void onApply() {
				// A list for the column configurations
				List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
			
				// Create columns as ColumnConfig objects, add to the above list
				ColumnConfig column = new ColumnConfig("name", "Company", 200);
				configs.add(column);
			
				column = new ColumnConfig("symbol", "Symbol", 75);
				configs.add(column);
			
				column = new ColumnConfig("last", "Last", 75);
				// align value to right
				column.setAlignment(HorizontalAlignment.RIGHT);
				// format value as US currency
				column.setNumberFormat(NumberFormat.getCurrencyFormat());
				configs.add(column);
			
				column = new ColumnConfig("change", "Change", 85);
				// align value to right
				column.setAlignment(HorizontalAlignment.RIGHT);
				// format value as standard decimal
				column.setNumberFormat(NumberFormat.getDecimalFormat());
				configs.add(column);
			
				column = new ColumnConfig("date", "Last Updated", 125);
				column.setAlignment(HorizontalAlignment.RIGHT);
				column.setDateTimeFormat(DateTimeFormat
						.getFormat("MMM dd, yyyy"));
				configs.add(column);
			
				// Populate the store
				ListStore<ModelData> store = new ListStore<ModelData>();
				store.add(LocalData.getStocks());
			
				ColumnModel cm = new ColumnModel(configs);
				
				// To the first column in the first row (column 0 in row 0),
				// add a 'Stock Information' header group spanning 1 row & 2 columns 
				cm.addHeaderGroup(0, 0, new HeaderGroupConfig("Stock Information", 1, 2));
				
				// To the third column in the first row (column 2 in row 0),
				// add a 'Stock Performance' header group spanning 1 row & 2 columns
				cm.addHeaderGroup(0, 2, new HeaderGroupConfig("Stock Performance", 1, 2));
			
				// Create and configure the grid.
				Grid<ModelData> grid = new Grid<ModelData>(store, cm);
				grid.setBorders(true);
				grid.setSize(600, 300);
				grid.setAutoExpandColumn("name");
			
				// show it up, equivalent to
				// RootPanel.get().add(grid)
			
				GxtCookBk.getAppCenterPanel().add(grid);
			}
		};
		recipes.add(colGrouping);

		Recipe aggregatnSmry = new Recipe("Aggregating Column Data") {
			
			private String formatChangeCol(double val){
				String style = val < 0 ? "red" : "green";
				String v = NumberFormat.getDecimalFormat().format(val);
				return "<span style='font-weight: bold;color:" + style + "'>" + v + "</span>";
			}
			
			@Override
			public void onApply() {
				// A list for the column configurations
				List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
			
				// Create columns as ColumnConfig objects, add to the above list
				ColumnConfig column = new ColumnConfig("name", "Company", 200);
				configs.add(column);
			
				column = new ColumnConfig("symbol", "Symbol", 75);
				configs.add(column);
			
				column = new ColumnConfig("last", "Last", 75);
				// align value to right
				column.setAlignment(HorizontalAlignment.RIGHT);
				// format value as US currency
				column.setNumberFormat(NumberFormat.getCurrencyFormat());
				configs.add(column);
			
				column = new ColumnConfig("change", "Change", 85);
				// align value to right
				column.setAlignment(HorizontalAlignment.RIGHT);
				// gimme a richer formatting our formatChangeCol method
				column.setRenderer(new GridCellRenderer<ModelData>() {
					@Override
					public Object render(ModelData model, String property,
							ColumnData config, int rowIndex, int colIndex,
							ListStore<ModelData> store, Grid<ModelData> grid) {						
						return formatChangeCol((Double) model.get(property));
					}
				});
				configs.add(column);
			
				column = new ColumnConfig("date", "Last Updated", 125);
				// align value to right
				column.setAlignment(HorizontalAlignment.RIGHT);
				// format date value as Oct 1, 1960
				column.setDateTimeFormat(DateTimeFormat
						.getFormat("MMM dd, yyyy"));
				configs.add(column);
			
				// Create a model from the list of column configurations				
				ColumnModel cm = new ColumnModel(configs);
			
				// Aggregation of averages
				AggregationRowConfig<Stock> aggrgatn = new AggregationRowConfig<Stock>();
				aggrgatn.setHtml("name", "Average");
			
				// show average for data in the column with id of 'last'
				// and format it as a standard decimal
				aggrgatn.setSummaryType("last", SummaryType.AVG);
				aggrgatn.setSummaryFormat("last",
						NumberFormat.getCurrencyFormat());
			
				// show average for data in the column with id of 'change'
				// and format it with a renderer that delegates to formatChangeCol
				aggrgatn.setSummaryType("change", SummaryType.AVG);
				aggrgatn.setRenderer("change",
						new AggregationRenderer<Stock>() {
							@Override
							public Object render(Number value, int colIndex,
									Grid<Stock> grid, ListStore<Stock> store) {
								return formatChangeCol(value.doubleValue());
							}
						});
				cm.addAggregationRow(aggrgatn);
			
				// Maximum aggregation, who's the highest ?
				aggrgatn = new AggregationRowConfig<Stock>();
				aggrgatn.setHtml("name", "Maximum");
			
				// show max value in the column with id of 'last'
				// and format as US currency
				aggrgatn.setSummaryType("last", SummaryType.MAX);
				aggrgatn.setSummaryFormat("last",
						NumberFormat.getCurrencyFormat());
			
				// show max value in the column with id of 'change'
				// and format it with a renderer that delegates to formatChangeCol
				aggrgatn.setSummaryType("change", SummaryType.MAX);
				aggrgatn.setRenderer("change",
						new AggregationRenderer<Stock>() {
							@Override
							public Object render(Number value, int colIndex,
									Grid<Stock> grid, ListStore<Stock> store) {
								return formatChangeCol(value.doubleValue());
							}
						});
				cm.addAggregationRow(aggrgatn);
			
				// Sum aggregation
				aggrgatn = new AggregationRowConfig<Stock>();
				aggrgatn.setHtml("name", "Total");
			
				// show the total for values in the column with id of 'last'
				// and format as US currency
				aggrgatn.setSummaryType("last", SummaryType.SUM);
				aggrgatn.setSummaryFormat("last",
						NumberFormat.getCurrencyFormat());
			
				// show the total for values in the column with id of 'change'
				// and format it with a renderer that delegates to formatChangeCol
				aggrgatn.setSummaryType("change", SummaryType.SUM);
				aggrgatn.setRenderer("change",
						new AggregationRenderer<Stock>() {
							@Override
							public Object render(Number value, int colIndex,
									Grid<Stock> grid, ListStore<Stock> store) {
								return formatChangeCol(value.doubleValue());
							}
						});
				cm.addAggregationRow(aggrgatn);
			
				// Populate store
				ListStore<ModelData> store = new ListStore<ModelData>();
				store.add(LocalData.getStocks());
			
				// Create and configure the grid
				Grid<ModelData> grid = new Grid<ModelData>(store, cm);
				grid.setBorders(true);
				grid.setSize(600, 300);
				grid.setAutoExpandColumn("name");
			
				// show it up, equivalent to
				// RootPanel.get().add(grid)
		
				GxtCookBk.getAppCenterPanel().add(grid);
			}
		};
		recipes.add(aggregatnSmry);

		Recipe chkSelection = new Recipe(
				"Easy Record Selection With CheckBoxes") {
			@Override
			public void onApply() {
				// A list for the column configurations
				List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
			
				// Create columns as ColumnConfig objects, add to the above list
				// CheckBoxSelectionModel becomes one of our columns, usually the first or the last
				CheckBoxSelectionModel<ModelData> selectionMdl = new CheckBoxSelectionModel<ModelData>();
				configs.add(selectionMdl.getColumn());
			
				ColumnConfig column = new ColumnConfig("name", "Company", 200);
				configs.add(column);
			
				column = new ColumnConfig("symbol", "Symbol", 75);
				configs.add(column);				
			
				column = new ColumnConfig("last", "Last", 75);
				// align value to right & format value as US currency
				column.setAlignment(HorizontalAlignment.RIGHT);
				column.setNumberFormat(NumberFormat.getCurrencyFormat());
				configs.add(column);
			
				column = new ColumnConfig("change", "Change", 85);
				// align value to right & format as decimal
				column.setAlignment(HorizontalAlignment.RIGHT);
				column.setNumberFormat(NumberFormat.getDecimalFormat());
				configs.add(column);
			
				column = new ColumnConfig("date", "Last Updated", 125);
				// align value to right & format date as Oct 1, 1960
				column.setAlignment(HorizontalAlignment.RIGHT);
				column.setDateTimeFormat(DateTimeFormat
						.getFormat("MMM dd, yyyy"));
				configs.add(column);
			
				// Populate store
				ListStore<ModelData> store = new ListStore<ModelData>();
				store.add(LocalData.getStocks());
			
				ColumnModel cm = new ColumnModel(configs);
				Grid<ModelData> grid = new Grid<ModelData>(store, cm);
			
				// Configure grid to use our CheckBoxSelectionModel
				// for making record selections, and then add it as
				// a plugin, that way U can select/de-select all 
				// records when U select/de-select the checkbox on
				// header row of the grid.
				grid.setSelectionModel(selectionMdl);
				grid.addPlugin(selectionMdl);
			
				grid.setBorders(true);
				grid.setSize(600, 300);
				grid.setAutoExpandColumn("name");
			
				// show it up, equivalent to
				// RootPanel.get().add(grid)
			
				GxtCookBk.getAppCenterPanel().add(grid);
			}
		};
		recipes.add(chkSelection);

		Recipe editableGrid = new Recipe("Entering Validated Data Into A Grid") {

			private List<ColumnConfig> getColumnCfgs() {
				// A list for the column configurations
				List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
			
				// Create columns as ColumnConfig objects, add to the above list
				ColumnConfig column = new ColumnConfig("name", "Company", 120);
				// Edit this column with a TextField that won't
				// accept an empty value
				TextField<String> txtField = new TextField<String>();
				txtField.setAllowBlank(false);
				column.setEditor(new CellEditor(txtField));
				configs.add(column);
			
				column = new ColumnConfig("last", "Last", 75);
				column.setAlignment(HorizontalAlignment.RIGHT);
				column.setNumberFormat(NumberFormat.getCurrencyFormat());
				// Edit this column with a NumberField that won't
				// accept empty or negative value
				NumberField numField = new NumberField();
				numField.setAllowBlank(false);
				numField.setAllowNegative(false);
				column.setEditor(new CellEditor(numField));				
				configs.add(column);
			
				column = new ColumnConfig("date", "Last Updated", 100);
				DateTimeFormat frmt = DateTimeFormat.getFormat("MMM dd, yyyy");	
				column.setDateTimeFormat(frmt);
				column.setAlignment(HorizontalAlignment.RIGHT);
				// Edit this column with a DateField, configured
				// with a specific date format.
				DateField dateField = new DateField();				
				dateField.getPropertyEditor().setFormat(frmt);
				column.setEditor(new CellEditor(dateField));				
				configs.add(column);
			
				return configs;
			}
			
			@Override
			public void onApply() {	
				// Populate the sore
				ListStore<ModelData> store = new ListStore<ModelData>();
				store.add(LocalData.getStocks());
			
				// Create and configure the editable grid
				List<ColumnConfig> configs = getColumnCfgs();
				ColumnModel cm = new ColumnModel(configs);
				EditorGrid<ModelData> grid = new EditorGrid<ModelData>(store, cm);
				grid.setBorders(true);
				grid.setSize(400, 200);
				grid.setStripeRows(true);
				grid.setAutoExpandColumn("name");
				grid.setStyleAttribute("marginBottom", "15px");
			
				// show it up, equivalent to
				// RootPanel.get().add(grid)
		
				GxtCookBk.getAppCenterPanel().add(grid);
			
				// We are making another grid, so give
				// us a fresh column list and store.
				configs = getColumnCfgs();
				store = new ListStore<ModelData>();
				store.add(LocalData.getStocks());
			
				// This time we use a regular Grid object
				// watch this ...
				cm = new ColumnModel(configs);
				Grid<ModelData> rowEditorGrid = new Grid<ModelData>(store, cm);
			
				// Make this regular Grid editable with the
				// RowEditor plugin, allow edit on double-click event
				RowEditor<ModelData> rowEditor = new RowEditor<ModelData>();
				rowEditor.getMessages().setSaveText("Ok");
				rowEditor.setClicksToEdit(ClicksToEdit.TWO);
				rowEditorGrid.addPlugin(rowEditor);
			
				// Other cosmetics on the Grid!
				rowEditorGrid.setBorders(true);
				rowEditorGrid.setSize(400, 200);
				rowEditorGrid.setStripeRows(true);
				rowEditorGrid.setAutoExpandColumn("name");
			
				// show it up, equivalent to
				// RootPanel.get().add(rowEditorGrid)
				
				GxtCookBk.getAppCenterPanel().add(rowEditorGrid);
			}
		};
		recipes.add(editableGrid);

		Recipe pagedGrid = new Recipe("Automatic Pagination In Grids") {
			@Override
			public void onApply() {
				// Add paging support for a collection of local models.
				// To load remote models use RpcProxy instead e.g
				// RpcProxy<PagingLoadResult<ModelData>>
				// then override the load() method and call your RPC service
				// method from it. Your RPC method should return the right type
				// e.g PagingLoadResult<Customer> instead of ListLoadResult<Customer>
				PagingModelMemoryProxy proxy = new PagingModelMemoryProxy(
						LocalData.getCompanies());
			
				// configure it's loader
				PagingLoader<PagingLoadResult<ModelData>> loader = new BasePagingLoader<PagingLoadResult<ModelData>>(
						proxy);
				loader.setRemoteSort(true);
			
				// create store from the loader
				ListStore<ModelData> store = new ListStore<ModelData>(loader);
			
				// setup columns
				List<ColumnConfig> config = new ArrayList<ColumnConfig>();
				ColumnConfig company = new ColumnConfig("name", "Company", 60);
				config.add(company);
			
				ColumnConfig price = new ColumnConfig("open", "Price", 20);
				config.add(price);
			
				ColumnConfig change = new ColumnConfig("change", "Change", 20);
				config.add(change);
			
				ColumnConfig industry = new ColumnConfig("industry",
						"Industry", 20);
				config.add(industry);
			
				ColumnConfig last = new ColumnConfig("date", "Last Updated", 20);
				last.setDateTimeFormat(DateTimeFormat.getFormat("MM/dd/y"));
				config.add(last);
			
				// setup grid with store and columns
				ColumnModel cm = new ColumnModel(config);
				Grid<ModelData> pagedGrid = new Grid<ModelData>(store, cm);
								
				
				pagedGrid.setStripeRows(true); // show colored strips on rows
			
				// expand/contract columns to fit grid width
				pagedGrid.getView().setForceFit(true);
			
				// bind toolbar to loader
				final PagingToolBar tBar = new PagingToolBar(10);
				EventType eventType = Events.OnClick;
				tBar.addListener(eventType, new Listener<BaseEvent>() {

					@Override
					public void handleEvent(BaseEvent be) {
						// TODO Auto-generated method stub
						Object o = be.getSource();
						GWT.log("o = " + o.toString());
						Info.display("ToolbarEvetn","active page = " + tBar.getActivePage());
					}
					
				});
				
				
				tBar.bind(loader);
			
				// Load first data-set, use pagedGrid.setLoadMask(true) to
				// mask the grid if loading remote models with RpcProxy
				loader.load(0, 10);
			
				// display the grid
				ContentPanel ctPanel = new ContentPanel();
				ctPanel.setHeaderVisible(false);
				ctPanel.setLayout(new FitLayout());
				ctPanel.add(pagedGrid);
				// display paging bar at the bottom
				ctPanel.setBottomComponent(tBar);
				ctPanel.setSize(455, 250);
				
				// show it up, equivalent to
				// RootPanel.get().add(ctPanel)
	
				GxtCookBk.getAppCenterPanel().add(ctPanel);
			}
		};
		recipes.add(pagedGrid);

		Recipe groupedGrid = new Recipe("Data Grouping In Grids") {
			@Override
			public void onApply() {	
				// A list for the column configurations
				// Create columns as ColumnConfig objects, then add to the list
				List<ColumnConfig> config = new ArrayList<ColumnConfig>();
				ColumnConfig company = new ColumnConfig("name", "Company", 60);
				config.add(company);
			
				ColumnConfig price = new ColumnConfig("open", "Price", 20);
				price.setNumberFormat(NumberFormat.getCurrencyFormat());
				config.add(price);
			
				ColumnConfig change = new ColumnConfig("change", "Change", 20);
				config.add(change);
			
				ColumnConfig industry = new ColumnConfig("industry",
						"Industry", 20);
				config.add(industry);
			
				ColumnConfig last = new ColumnConfig("date", "Last Updated", 20);
				last.setDateTimeFormat(DateTimeFormat.getFormat("MM/dd/y"));
				config.add(last);
				
				// Here is what really count.
				// Instead a ListStore, we use a 
				// GroupingStore and call its 
				// groupBy() method appropriately
				GroupingStore<Stock> store = new GroupingStore<Stock>();
				store.add(LocalData.getCompanies());
				store.groupBy("industry");
				ColumnModel cm = new ColumnModel(config);
				Grid<Stock> groupedGrid = new Grid<Stock>(store, cm);
				
				// Yeah, we've got to use a GroupingView too
				GroupingView view = new GroupingView();
				view.setForceFit(true);
				groupedGrid.setView(view);
			
				// Our standard cosmetology
				groupedGrid.setBorders(true);
				groupedGrid.setSize(455, 300);
				groupedGrid.setStripeRows(true);
			
				// show it up, equivalent to
				// RootPanel.get().add(groupedGrid)
		
				GxtCookBk.getAppCenterPanel().add(groupedGrid);

			}
		};
		recipes.add(groupedGrid);

		Recipe gridRenderers = new Recipe("Custom Rendering for Grid Groups") {
			@Override
			public void onApply() {
				// Populate the store and group the 
				// data by on the 'industry' column, can 
				// also use a RpcProxy via a ListLoader
				GroupingStore<Stock> store = new GroupingStore<Stock>();
				store.add(LocalData.getCompanies()); 
				store.groupBy("industry");
			
				List<ColumnConfig> config = new ArrayList<ColumnConfig>();
				ColumnConfig company = new ColumnConfig("name", "Company", 60);
				company.setGroupable(false); // don't allow grouping here
				config.add(company);
			
				ColumnConfig price = new ColumnConfig("open", "Price", 20);
				price.setNumberFormat(NumberFormat.getCurrencyFormat());
				price.setGroupable(false); // don't allow grouping here
				config.add(price);
			
				ColumnConfig change = new ColumnConfig("change", "Change", 20);
				config.add(change);
			
				ColumnConfig industry = new ColumnConfig("industry",
						"Industry", 20);
				config.add(industry);
			
				ColumnConfig last = new ColumnConfig("date", "Last Updated", 20);
				last.setDateTimeFormat(DateTimeFormat.getFormat("MM/dd/y"));
				config.add(last);
			
				// Create and configure Grid
				final ColumnModel cm = new ColumnModel(config);
				Grid<Stock> groupedGrid = new Grid<Stock>(store, cm);
				groupedGrid.setBorders(true);
				groupedGrid.setSize(435, 250);
				groupedGrid.setStripeRows(true);
			
				// Must use a GroupingView for rendering
				GroupingView view = new GroupingView();
				view.setForceFit(true);
				// don't show the grouped column.
				view.setShowGroupedColumn(false);
				// Customize how the groups show up
				view.setGroupRenderer(new GridGroupRenderer() {
					@Override
					public String render(GroupColumnData data) {
						String header = cm.getColumnById(data.field)
								.getHeader();
						String sizeStr = data.models.size() == 1 ? "Item"
								: "Items";
						return header + ": " + data.group + " ("
								+ data.models.size() + " " + sizeStr + ")";
					}
				});
				groupedGrid.setView(view);
			
				// show it up, equivalent to
				// RootPanel.get().add(groupedGrid)
			
				GxtCookBk.getAppCenterPanel().add(groupedGrid);

			}
		};
		recipes.add(gridRenderers);

		Recipe grpSummary = new Recipe("Live Data Summaries") {
			@Override
			public void onApply() {							
				List<ColumnConfig> config = new ArrayList<ColumnConfig>();
			
				// Got to use SummaryColumnConfig instead
				// of just ColumnConfig even on columns we
				// are not summarizing, else we get a ClassCastException
				SummaryColumnConfig<Integer> company = new SummaryColumnConfig<Integer>(
						"name", "Company", 100);
				config.add(company);
			
				// Show the Sum of the 'price'
				// column for each group in the grid.
				// We allow edit on this column to show
				// how changes are instantly reflected.
				SummaryColumnConfig<Double> price = new SummaryColumnConfig<Double>(
						"open", "Price", 75);
				price.setGroupable(false);
				price.setSummaryType(SummaryType.SUM);				
				price.setEditor(new CellEditor(new NumberField()));
				price.setNumberFormat(NumberFormat.getCurrencyFormat());
				// Use a renderer to customize its look,
				// render it as -> Total : USD$ 30.27
				price.setSummaryRenderer(new SummaryRenderer() {
					@Override
					public String render(Number value, Map<String, Number> data) {
						String val = NumberFormat.getFormat("0.00").format(
								value.doubleValue());
						return "Total : "
								+ NumberFormat.getCurrencyFormat().format(
										new Double(val));
					}
				});
				config.add(price);
			
				// Show the Average of the 'change'
				// column for each group in the grid.
				SummaryColumnConfig<Double> change = new SummaryColumnConfig<Double>(
						"change", "Change", 75);
				change.setGroupable(false);
				change.setSummaryType(SummaryType.AVG);
				// Use a renderer to customize its look,
				// render it as -> Avg : 1.09
				change.setSummaryRenderer(new SummaryRenderer() {
					@Override
					public String render(Number value, Map<String, Number> data) {
						return "Avg : "
								+ NumberFormat.getDecimalFormat().format(
										value.doubleValue());
					}
				});
				config.add(change);
			
				// Must use SummaryColumnConfig even
				// when not summarizing
				SummaryColumnConfig<Double> industry = new SummaryColumnConfig<Double>(
						"industry", "Industry", 85);
				industry.setGroupable(false);
				config.add(industry);
			
				// We also allow edit on
				// this column to show how changes are
				// instantly reflected in the summaries. 
				SummaryColumnConfig<Double> last = new SummaryColumnConfig<Double>(
						"date", "Last Updated", 85);
				last.setGroupable(false);
				last.setEditor(new CellEditor(new DateField()));
				
				// Use a custom SummaryType, here we
				// obtain the number of elapsed days
				// between the lowest and highest dates
				// from a group. 
				last.setSummaryType(new SummaryType<Double>() {
					@Override
					public Double render(Object v, final ModelData m, final String field,
							Map<String, Object> data) {
						
						Date now = (Date) m.get(field);
						Date min = now;
						Date max = now;
						
						String minFieldKey = field + "_min";
						String maxFieldKey = field + "_max";
						
						if(data.containsKey(minFieldKey)){
							min = (Date) data.get(minFieldKey);
							if(now.before(min)){
								min = now;
							}
						}
						data.put(minFieldKey, min);
						
						if(data.containsKey(maxFieldKey)){
							max = (Date) data.get(maxFieldKey);
							if(now.after(max)){
								max = now;
							}
						}						
						data.put(maxFieldKey, max);
			
						long diff = Math.abs(max.getTime() - min.getTime());
						long daysDiff = diff / 1000 / 60 / 60 / 24;	
						return new Double(daysDiff);
					}
			
				});
				
				// Use a renderer to customize its look,
				// render it as -> Within 2 days
				last.setDateTimeFormat(DateTimeFormat.getFormat("MMM d, yyy"));
				last.setSummaryRenderer(new SummaryRenderer() {
					@Override
					public String render(Number value, Map<String, Number> data) {
						int intVal = value.intValue();
						return "Within " + (intVal == 0 ? "24 Hours" : intVal + " Days");
					}
				});				
				config.add(last);
				
				// Populate the store and group the 
				// data on the 'industry' column, can
				// also use a RpcProxy via a ListLoader
				GroupingStore<ModelData> store = new GroupingStore<ModelData>();
				store.add(LocalData.getCompanies());
				store.groupBy("industry");
			
				final ColumnModel cm = new ColumnModel(config);
				EditorGrid<ModelData> smryGrid = new EditorGrid<ModelData>(store, cm);
			
				// Got to use a GroupSummaryView view
				GroupSummaryView view = new GroupSummaryView();
				view.setForceFit(true); 
				
				// hide the grouped column
				view.setShowGroupedColumn(false);
				
				// Use a renderer for the groups,
				// render as -> Industry : Computer (5 Companies)
				view.setGroupRenderer(new GridGroupRenderer() {
					@Override
					public String render(GroupColumnData data) {
						String header = cm.getColumnById(data.field)
								.getHeader();
						String sizeStr = data.models.size() == 1 ? "Company"
								: "Companies";
						return header + ": " + data.group + " ("
								+ data.models.size() + " " + sizeStr + ")";
					}
				});
				smryGrid.setView(view);
			
				// Some cosmetics on the Grid
				smryGrid.setBorders(true);
				smryGrid.setSize(485, 300);
				smryGrid.setStripeRows(true);
			
				// show it up, equivalent to
				// RootPanel.get().add(smryGrid)
				
				GxtCookBk.getAppCenterPanel().add(smryGrid);
				
			}
		};
		recipes.add(grpSummary);

		Recipe beanGrid = new Recipe("BeanModel Grid") {
			@Override
			public void onApply() {
				// Make RPC call vai a proxy, see appendixes for info.
				// here we want to fetch a bunch of Customer beans
				final RemoteGatewayAsync rpcService = (RemoteGatewayAsync) GWT
						.create(RemoteGateway.class);
				RpcProxy<ListLoadResult<Customer>> rpcProxy = new RpcProxy<ListLoadResult<Customer>>() {
					@Override
					public void load(Object cfg,
							AsyncCallback<ListLoadResult<Customer>> callback) {
						rpcService
								.listCustomers((ListLoadConfig) cfg, callback);
					}
				};
			
				// setup the store for beans
				ListLoader<ListLoadResult<BeanModel>> loader = new BaseListLoader<ListLoadResult<BeanModel>>(
						rpcProxy, new BeanModelReader());
				ListStore<BeanModel> store = new ListStore<BeanModel>(loader);
			
				// setup column model
				List<ColumnConfig> columns = new ArrayList<ColumnConfig>();
				
				// Show the 'name' property in the bean
				ColumnConfig col = new ColumnConfig("name", "Name", 200);
				columns.add(col);
			
				// Show the 'email' property in the bean
				col = new ColumnConfig("email", "Email", 150);
				columns.add(col);
			
				// Show the 'age' property in the bean
				col = new ColumnConfig("age", "Age", 50);
				col.setAlignment(HorizontalAlignment.RIGHT);
				columns.add(col);
			
				// Create and configure the Grid
				ColumnModel cm = new ColumnModel(columns);
				Grid<BeanModel> beanGrid = new Grid<BeanModel>(store, cm);
				beanGrid.setAutoExpandColumn("name"); 		// expand "name" column as much as possible
				beanGrid.setBorders(true); 					// give us borders
				beanGrid.setLoadMask(true); 				// mask while loading
				beanGrid.setSize(400, 200);
				beanGrid.setStripeRows(true); 				// show colored strips on rows
			
				// Go fetch the data
				loader.load();
				
				// show it up, equivalent to
				// RootPanel.get().add(beanGrid)
			
				GxtCookBk.getAppCenterPanel().add(beanGrid);
			}
		};
		recipes.add(beanGrid);
		
		Recipe gridFilers = new Recipe("Intuitive Record Filtering") {
			
			@Override
			public void onApply() {
				// A list for the column configurations
				List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
			
				ColumnConfig column = new ColumnConfig("name", "Company", 200);
				configs.add(column);
			
				column = new ColumnConfig("symbol", "Symbol", 75);
				configs.add(column);				
			
				column = new ColumnConfig("last", "Last", 75);
				// align value to right & format value as US currency
				column.setAlignment(HorizontalAlignment.RIGHT);
				column.setNumberFormat(NumberFormat.getCurrencyFormat());
				configs.add(column);
			
				column = new ColumnConfig("date", "Last Updated", 125);
				// align value to right & format date as Oct 1, 1960
				column.setAlignment(HorizontalAlignment.RIGHT);
				column.setDateTimeFormat(DateTimeFormat
						.getFormat("MMM dd, yyyy"));
				configs.add(column);								
			
				// Populate store
				ListStore<ModelData> store = new ListStore<ModelData>();
				store.add(LocalData.getStocks());
			
				ColumnModel cm = new ColumnModel(configs);
				Grid<ModelData> grid = new Grid<ModelData>(store, cm);
				
				// Our collection of filters
				GridFilters filters = new GridFilters();
				filters.setLocal(true);
				
				// A string filter for the 
				// column with id of "name"
				StringFilter nameFilter = new StringFilter("name");
				filters.addFilter(nameFilter);
				
				// Another string filter, but for 
				// the column with id of "symbol"
				StringFilter symbolFilter = new StringFilter("symbol");
				filters.addFilter(symbolFilter);
				
				// A numeric filter for the column
				// with id of "last"
				NumericFilter numericFilter = new NumericFilter("last");
				filters.addFilter(numericFilter);
				
				// A date filter for the column
				// with id of "date"
				DateFilter dateFilter = new DateFilter("date");
				filters.addFilter(dateFilter);
				
				// Add them to the grid as a 
				// plug-in an you are done
				// with basic record filtering
				grid.addPlugin(filters);
			
				grid.setBorders(true);
				grid.setSize(600, 300);
				grid.setAutoExpandColumn("name");
			
				// show it up, equivalent to
				// RootPanel.get().add(grid)
			//	centerPanel.add(grid);
				GxtCookBk.getAppCenterPanel().add(grid);
			}
		};
		recipes.add(gridFilers);

		return recipes;
	}

}
