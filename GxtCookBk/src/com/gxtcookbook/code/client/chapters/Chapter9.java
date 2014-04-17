package com.gxtcookbook.code.client.chapters;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.extjs.gxt.charts.client.Chart;
import com.extjs.gxt.charts.client.model.ChartModel;
import com.extjs.gxt.charts.client.model.axis.Label;
import com.extjs.gxt.charts.client.model.axis.XAxis;
import com.extjs.gxt.charts.client.model.axis.YAxis;
import com.extjs.gxt.charts.client.model.charts.AreaChart;
import com.extjs.gxt.charts.client.model.charts.BarChart;
import com.extjs.gxt.charts.client.model.charts.LineChart;
import com.extjs.gxt.charts.client.model.charts.PieChart;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelLookup;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.GroupingStore;
import com.extjs.gxt.ui.client.store.Record;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.grid.GridGroupRenderer;
import com.extjs.gxt.ui.client.widget.grid.GroupColumnData;
import com.extjs.gxt.ui.client.widget.grid.GroupingView;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gxtcookbook.code.client.GxtCookBk;
import com.gxtcookbook.code.client.RemoteGateway;
import com.gxtcookbook.code.client.RemoteGatewayAsync;
import com.gxtcookbook.code.client.data.LocalData;
import com.gxtcookbook.code.client.data.Stock;
import com.gxtcookbook.code.server.model.Customer;

public class Chapter9 extends ChapterAdapter {
	
	private static Chapter9 instance;
	
	public static Chapter9 get(){
		instance = (instance == null ? new Chapter9() : instance);
		return instance;
	}
	
	protected Chapter9(){
		super();
	}

	@Override
	protected String getTitle() {
		return "Data Makeovers with Charts & Visualizations";
	}

	@Override
	protected List<Recipe> applyTheseRecipes() {
		List<Recipe> recipes = new LinkedList<Recipe>();
		
		Recipe barChart = new Recipe("Using A BarChart") {			
			@Override
			public void onApply() {
				Chart chart = new Chart("resources/chart/open-flash-chart.swf");				
				
				final ChartModel model = new ChartModel("Customer Purchases");				
				final BarChart chartCfg = new BarChart();			
				
				final XAxis xAxis = new XAxis();
				model.setXAxis(xAxis);
				
				YAxis yAxis = new YAxis();
				yAxis.setRange(0, 10000, 1000);
				model.setYAxis(yAxis);
										
				chart.setChartModel(model);
				chart.setBorders(true);	
				
				final LayoutContainer chartPanel = new LayoutContainer(new FitLayout());
				chartPanel.setSize(650, 335);
				chartPanel.add(chart);
					
				// put it on screen, equivalent
				// to RootPanel.get().add(chartPanel)				
				GxtCookBk.getAppCenterPanel().add(chartPanel);
				
				// Make RPC call, see appendixes for more info
				final RemoteGatewayAsync rpcService = (RemoteGatewayAsync) GWT.create(RemoteGateway.class);		
				AsyncCallback<List<Customer>> callback = new AsyncCallback<List<Customer>>(){
					@Override
					public void onFailure(Throwable caught) {	
						chartPanel.unmask();
						Info.display("Error", "RPC Error");
					}
					
					@Override
					public void onSuccess(List<Customer> result) {	
						chartPanel.unmask();
						if(result != null){	
							List<Label> labels = new ArrayList<Label>();
							List<Number> values = new ArrayList<Number>();
							for(Customer cust : result) {
								labels.add(new Label(cust.getName()));
								values.add(new Double(cust.getPurchases()));
							}
							xAxis.addLabels(labels);
							chartCfg.addValues(values);							
							
							model.addChartConfig(chartCfg);
							chartPanel.layout();
						}						
					}
				};
				
				// fetch some real customers
				chartPanel.mask();
				rpcService.getCustomers(null, callback);	
			}
		};
		recipes.add(barChart);
		
		Recipe pieChart = new Recipe("Using A PieChart") {
			
			@Override
			public void onApply() {
				Chart chart = new Chart("resources/chart/open-flash-chart.swf");				
				
				final ChartModel model = new ChartModel("Customer Purchases");				
				final PieChart chartCfg = new PieChart();			
				
				chart.setBorders(true);
				chart.setSize(650, 335);
				chart.setChartModel(model);
				
				final LayoutContainer chartPanel = new LayoutContainer(new FitLayout());
				chartPanel.setSize(650, 335);
				chartPanel.add(chart);
								
				// put it on screen, equivalent
				// to RootPanel.get().add(chartPanel)
				GxtCookBk.getAppCenterPanel().add(chartPanel);
				
				// Make RPC call, see appendixes for more info
				final RemoteGatewayAsync rpcService = (RemoteGatewayAsync) GWT.create(RemoteGateway.class);		
				AsyncCallback<List<Customer>> callback = new AsyncCallback<List<Customer>>(){
					@Override
					public void onFailure(Throwable caught) {	
						chartPanel.unmask();
						Info.display("Error", "RPC Error");
					}
					
					@Override
					public void onSuccess(List<Customer> result) {	
						chartPanel.unmask();
						if(result != null){								
							for(Customer cust : result) {								
								chartCfg.addSlice(cust.getPurchases(), cust.getName());
							}						
							
							model.addChartConfig(chartCfg);
							GxtCookBk.getAppCenterPanel().layout();
						}						
					}
				};
				
				// fetch some real customers
				chartPanel.mask();
				rpcService.getCustomers(null, callback);
			}
		};
		recipes.add(pieChart);
		
		Recipe lineChart = new Recipe("Using A LineChart") {
			
			@Override
			public void onApply() {
				// create the Chart object
				Chart chart = new Chart("resources/chart/open-flash-chart.swf");				
				
				// create the model and the
				// line-chart config object
				final ChartModel model = new ChartModel("Customer Purchases");				
				final LineChart chartCfg = new LineChart();			
				
				// setup x and y axis
				final XAxis xAxis = new XAxis();
				model.setXAxis(xAxis);
				
				YAxis yAxis = new YAxis();
				// calibrate y axis from
				// 0 to 10000 in 1000 steps
				yAxis.setRange(0, 10000, 1000);
				model.setYAxis(yAxis);
			
				chart.setChartModel(model);
				chart.setBorders(true);	
				
				// place chart on the screen
				final LayoutContainer chartPanel = new LayoutContainer(new FitLayout());
				chartPanel.setSize(650, 335);
				chartPanel.add(chart);
					
				// put it on screen, equivalent
				// to RootPanel.get().add(chartPanel)
				GxtCookBk.getAppCenterPanel().add(chartPanel);
				
				// Make RPC call, see appendixes for more info
				final RemoteGatewayAsync rpcService = (RemoteGatewayAsync) GWT.create(RemoteGateway.class);		
				AsyncCallback<List<Customer>> callback = new AsyncCallback<List<Customer>>(){
					@Override
					public void onFailure(Throwable caught) {	
						chartPanel.unmask();
						Info.display("Error", "RPC Error");
					}
					
					@Override
					public void onSuccess(List<Customer> result) {	
						chartPanel.unmask();
						if(result != null){	
							List<Label> labels = new ArrayList<Label>();
							List<Number> values = new ArrayList<Number>();
							
							List<BeanModel> beans = BeanModelLookup.get().getFactory(Customer.class).createModel(result);							
							for (BeanModel bean : beans) {
								Customer cust = (Customer) bean.getBean();
								
								labels.add(new Label(cust.getName()));
								values.add(new Double(cust.getPurchases()));
							}
							xAxis.addLabels(labels);
							chartCfg.addValues(values);							
							
							model.addChartConfig(chartCfg);
							chartPanel.layout();
						}						
					}
				};
				
				// fetch some real customers
				chartPanel.mask();
				rpcService.getCustomers(null, callback);
			}
		};
		recipes.add(lineChart);
		
		Recipe areaChart = new Recipe("Using An AreaChart") {
			
			@Override
			public void onApply() {
				// create the Chart object
				Chart chart = new Chart("resources/chart/open-flash-chart.swf");				
				
				// create the model and the
				// area-chart config objects							
				final ChartModel model = new ChartModel("Customer Purchases");				
				
				// create an AreaChart for the Average
				final AreaChart avgCfg = new AreaChart();
				avgCfg.setText("Average");
				avgCfg.setColour("#ff8800");
				
				// create an AreaChart for the total
				final AreaChart totalCfg = new AreaChart();
				totalCfg.setText("Total");
				
				// setup x and y axis
				final XAxis xAxis = new XAxis();
				model.setXAxis(xAxis);
				
				YAxis yAxis = new YAxis();
				// calibrate from 0 to 10000 in 1000 steps
				yAxis.setRange(0, 10000, 1000);
				model.setYAxis(yAxis);
										
				chart.setChartModel(model);
				chart.setBorders(true);	
				
				// wrap chart with a panel
				// for easy refresh
				final LayoutContainer chartPanel = new LayoutContainer(new FitLayout());
				chartPanel.setSize(650, 335);
				chartPanel.add(chart);
					
				// put it on screen, equivalent
				// to RootPanel.get().add(chartPanel)
				GxtCookBk.getAppCenterPanel().add(chartPanel);
				
				// Make RPC call, see appendixes for more info
				final RemoteGatewayAsync rpcService = (RemoteGatewayAsync) GWT.create(RemoteGateway.class);		
				AsyncCallback<List<Customer>> callback = new AsyncCallback<List<Customer>>(){
					@Override
					public void onFailure(Throwable caught) {	
						chartPanel.unmask();
						Info.display("Error", "RPC Error");
					}
					
					@Override
					public void onSuccess(List<Customer> result) {	
						chartPanel.unmask();
						if(result != null){	
							List<Label> labels = new ArrayList<Label>();
							List<Number> values = new ArrayList<Number>();
							List<Number> avgValues = new ArrayList<Number>();
							
							List<BeanModel> beans = BeanModelLookup.get().getFactory(Customer.class).createModel(result);							
							for (BeanModel bean : beans) {
								Customer cust = (Customer) bean.getBean();
								
								labels.add(new Label(cust.getName()));
								values.add(new Double(cust.getPurchases()));
								avgValues.add(new Double(cust.getPurchases()*0.12));
							}
							xAxis.addLabels(labels);
							
							// add values to the average chart.
							avgCfg.addValues(avgValues);
							// add the average chart to the model.
							model.addChartConfig(avgCfg);
							
							// add values to the total chart.
							totalCfg.addValues(values);
							// add the total chart to the model.
							model.addChartConfig(totalCfg);
							chartPanel.layout();
						}						
					}
				};
				
				// fetch some real customers
				chartPanel.mask();
				rpcService.getCustomers(null, callback);
			}
		};
		recipes.add(areaChart);
		
		Recipe boundCmp = new Recipe("Visualizing A Bound Component") {			
			
			@Override
			public void onApply() {
				// create the Chart object
				final Chart chart = new Chart("resources/chart/open-flash-chart.swf");				
				
				// create the model and the
				// pie-chart config object
				final String chartLbl = "Industry Performance";
				final ChartModel chartModel = new ChartModel(chartLbl);				
				final PieChart chartCfg = new PieChart();											
				
				chart.setBorders(true);
				chart.setChartModel(chartModel);
				
				// wrap chart in a 
				// refresh-able panel
				final LayoutContainer chartPanel = new LayoutContainer(new FitLayout());
				chartPanel.setSize(435, 235);
				chartPanel.add(chart);
				
				// put chart on screen, equivalent
				// to RootPanel.get().add(chartPanel)
				GxtCookBk.getAppCenterPanel().add(chartPanel);
				
				// Populate the store and group the 
				// data on the 'industry' column, can 
				// also use a RpcProxy via a ListLoader.
				// Refresh chart after changes are saved
				final GroupingStore<Stock> store = new GroupingStore<Stock>(){
					@Override
					protected void afterCommit(Record record) {
						super.afterCommit(record);
						chartCfg.getValues().clear();
						this.groupBy("industry", true);
						
						ChartModel model = new ChartModel(chartLbl);
						model.addChartConfig(chartCfg);
						chart.setChartModel(model);						
						chartPanel.layout();
					}
				};
				store.add(LocalData.getCompanies()); 
				store.groupBy("industry");
			
				List<ColumnConfig> config = new ArrayList<ColumnConfig>();
				ColumnConfig company = new ColumnConfig("name", "Company", 60);
				company.setGroupable(false); // don't allow grouping here
				config.add(company);
			
				ColumnConfig price = new ColumnConfig("open", "Price", 20);
				price.setNumberFormat(NumberFormat.getCurrencyFormat());
				price.setGroupable(false); // don't allow grouping here	
				
				// We want to enter values here
				NumberField numField = new NumberField();
				numField.setAllowBlank(false);
				numField.setAllowNegative(false);
				price.setEditor(new CellEditor(numField));				
				config.add(price);
			
				ColumnConfig change = new ColumnConfig("change", "Change", 20);
				change.setGroupable(false); // don't allow grouping here
				config.add(change);
			
				ColumnConfig industry = new ColumnConfig("industry",
						"Industry", 20);
				config.add(industry);
			
				ColumnConfig last = new ColumnConfig("date", "Last Updated", 20);
				last.setDateTimeFormat(DateTimeFormat.getFormat("MM/dd/y"));
				last.setGroupable(false); // don't allow grouping here
				config.add(last);
			
				// Create and configure Grid				
				final ColumnModel cm = new ColumnModel(config);				
				EditorGrid<Stock> groupedGrid = new EditorGrid<Stock>(store, cm);
				groupedGrid.setBorders(true);
				groupedGrid.setSize(435, 200);
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
						
						double value = 0.0;
						for(ModelData model : data.models){
							value += new Double(model.get("open").toString()).doubleValue();
						}
						// add a slice to the pie chart.
						chartCfg.addSlice(value, data.gvalue.toString());
						return header + ": " + data.group + " ("
								+ data.models.size() + " " + sizeStr + ")";
					}
				});
				groupedGrid.setView(view);
				groupedGrid.addListener(Events.ViewReady, new Listener<GridEvent<ModelData>>() {
					@Override
					public void handleEvent(GridEvent<ModelData> be) {
						chartModel.addChartConfig(chartCfg);					
					}
				});
			
				// show grid up, equivalent to
				// RootPanel.get().add(groupedGrid)			
				GxtCookBk.getAppCenterPanel().add(groupedGrid);
			
				// disable the button after commit.
				final Button saveBtn = new Button("Save Changes");
				saveBtn.addSelectionListener(new SelectionListener<ButtonEvent>() {					
					@Override
					public void componentSelected(ButtonEvent evt) {
						store.commitChanges();
						saveBtn.disable();
					}
				});
				// put button on screen, equivalent
				// to RootPanel.get().add(saveBtn)		
				GxtCookBk.getAppCenterPanel().add(saveBtn);
				
				// enable the button if we made changes to the grid.
				groupedGrid.addListener(Events.AfterEdit,new Listener<BaseEvent>() {
					@Override
					public void handleEvent(BaseEvent be) {
						// TODO Auto-generated method stub
						saveBtn.setEnabled(store.getModifiedRecords().size() > 0);
					}
				});
			}
		};
		recipes.add(boundCmp);
		
		Recipe remoteData = new Recipe("Visualizing External Data") {
			
			private void fetchData(String url, final AreaChart chartCfg,final LayoutContainer panel){
				/*
				 * If no internet access and the url
				 * referes to a local copy of the resulting 
				 * JSON from world bank, then use this block
				 * instead 
				 */		
				/*
				RemoteGatewayAsync rpcService = (RemoteGatewayAsync) GWT.create(RemoteGateway.class);
				rpcService.getJSON(url, new AsyncCallback<String>() {
					@Override
					public void onFailure(Throwable ex) {
						Info.display("Error", ex.getMessage());	
					}
					
					@Override
					public void onSuccess(String result) {
						GWT.log("got result: " + result);
						JSONValue jsonVal = JSONParser.parseStrict(result);
						drawChart(jsonVal, chartCfg,panel);					
					}
				})
				;*/
				
				RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, URL.encode(url));
				try {					
					builder.sendRequest(null, new RequestCallback(){
						@Override
						public void onError(Request request, Throwable ex) {
							Info.display("Connection Error", ex.getMessage());	     
						}
						
						@Override
						public void onResponseReceived(Request request,
								Response response) {
							// check for an ok html response code (200).
							if (200 == response.getStatusCode()){
								JSONValue jsonVal = JSONParser.parseStrict(response.getText());
								drawChart(jsonVal, chartCfg,panel);	
							} else {
								GWT.log("response:" + response.getText());
								Info.display("Error Status", response.getStatusText());	
							}
						}
					});
				} catch (RequestException ex) {
					Info.display("Connection Error", ex.getMessage());        
				}
			}
			
			private void drawChart(JSONValue json, AreaChart chartCfg, LayoutContainer panel){
				JSONArray data = (JSONArray) json;
				JSONArray values = (JSONArray) data.get(1);
				
				List<Number> chartValues = new ArrayList<Number>();
				
				for(int i = 0; i < values.size(); i++){
					JSONObject dataValue = (JSONObject) values.get(i);
					String strVal = dataValue.get("value").toString();
					strVal = strVal.replaceAll("\"", "");
					chartValues.add(0, new Double(strVal));
				}				
				chartCfg.addValues(chartValues);
				// refresh to reveal data
				panel.layout();
			}
			
			@Override
			public void onApply() {
			
				// create chart object
				Chart chart = new Chart("resources/chart/open-flash-chart.swf");				
				
				// create model and
				// as well as labelled
				// and coloured area-chart
				// config objects
				ChartModel model = new ChartModel("Nigerian Children out of primary school: courtesy World Bank");					
				final AreaChart maleCfg = new AreaChart();	
				maleCfg.setText("Male");
				maleCfg.setColour("#ff8800");
				
				final AreaChart femaleCfg = new AreaChart();	
				femaleCfg.setText("Female");
				femaleCfg.setColour("#808080");
				
				// setup x and y axis
				XAxis xAxis = new XAxis();
				xAxis.addLabels(new Label("2003"), new Label("2004"), new Label("2005"), new Label("2006"), new Label("2007"));
				model.setXAxis(xAxis);
				
				YAxis yAxis = new YAxis();
				yAxis.setTickLength(5);
				yAxis.setRange(500000, 6000000, 500000);
				model.setYAxis(yAxis);
							
				model.addChartConfig(maleCfg);
				model.addChartConfig(femaleCfg);
				
				chart.setChartModel(model);
				chart.setBorders(true);	
				
				// prepare for easy refresh
				final LayoutContainer chartPanel = new LayoutContainer(new FitLayout());
				chartPanel.setSize(685, 335);
				chartPanel.add(chart);
								
				// put it on screen, equivalent
				// to RootPanel.get().add(chartPanel)
				GxtCookBk.getAppCenterPanel().add(chartPanel);

				
				
				// add <inherits name="com.google.gwt.json.JSON" /> and 
				// <inherits name="com.google.gwt.http.HTTP" /> to module XML file
				// http://127.0.0.1:8888/files/maledata.json
				// http://127.0.0.1:8888/files/femaledata.json
				// http://api.worldbank.org/countries/NGA/indicators/SE.PRM.UNER.MA?date=2004:2007&format=json
				// http://api.worldbank.org/countries/NGA/indicators/SE.PRM.UNER.FE?date=2004:2007&format=json
				
				String mUrl = "http://api.worldbank.org/countries/NGA/indicators/SE.PRM.UNER.MA?date=2004:2007&format=json";
				String fUrl = "http://api.worldbank.org/countries/NGA/indicators/SE.PRM.UNER.FE?date=2004:2007&format=json";
				fetchData(mUrl, maleCfg,chartPanel);
				fetchData(fUrl, femaleCfg,chartPanel);
				
			
			}
		};
		recipes.add(remoteData);
		
		Recipe canvas = new Recipe("Drawing on Canvas") {
			
			private void configureCanvas(Canvas canvas, int size){
				canvas.setStyleName("chartcanvas");	        
			    canvas.setWidth(size + "px");
			    canvas.setCoordinateSpaceWidth(size);
			    canvas.setHeight(size + "px");
			    canvas.setCoordinateSpaceHeight(size);
			}
			
			@Override
			public void onApply() {	
				// online demo : http://gwtcanvasdemo.appspot.com/
				
				// is there support ??
				Canvas canvas = Canvas.createIfSupported();			    
			    if (canvas == null) {
			    	Info.display("Notice", "Sorry, your browser doesn't support the HTML5 Canvas");	
			        return;
			    }	
			
			    // ok, configure and
			    // place it on screen
			    configureCanvas(canvas, 350);		        		       
				GxtCookBk.getAppCenterPanel().add(canvas);
			    
			    // draw several filled
			    // rectangles to simulate
			    // a horizontal bar-chart
			    Context2d ctx = canvas.getContext2d();
			    ctx.setFillStyle("#ff8800");
			    ctx.fillRect(20,20,100,50);
			    
			    ctx.setFillStyle("#808080");
			    ctx.fillRect(20,80,155,50);
			    
			    ctx.setFillStyle("#f6f6f6");
			    ctx.fillRect(20,140,190,50);
			    
			    ctx.setFillStyle("#aa7290");
			    ctx.fillRect(20,200,285,50);
			    
			    ctx.setFillStyle("#141414");
			    ctx.fillRect(20,260,80,50);
			    
			    // another canvas to draw
			    // something obscure 
			    canvas = Canvas.createIfSupported();
			    configureCanvas(canvas, 200);		        
			    GxtCookBk.getAppCenterPanel().add(canvas);

			    
			    // gimme' some shapes
			    ctx = canvas.getContext2d();
			    ctx.beginPath();
			    ctx.moveTo(110,75);
				for(int i=0;i<4;i++){  
					for(int j=0;j<3;j++){  
						ctx.beginPath();  
					    int x              = 25+j*50;               	// x coordinate  
					    int y              = 25+i*50;               	// y coordinate  
					    int radius         = 20;                    	// Arc radius  
					    int startAngle     = 0;                     	// Starting point on circle  
					    double endAngle       = Math.PI+(Math.PI*j)/2; 	// End point on circle  
					    boolean anticlockwise  = i%2==0 ? false : true; // clockwise or anticlockwise  
					  
					    ctx.arc(x, y, radius, startAngle, endAngle, anticlockwise); 					    
					    if (i>1){  
					        ctx.fill();  
					    } else {  
					        ctx.stroke();  
					    }  
					}  
				}
			}
		};
		recipes.add(canvas);
		
		return recipes;
	}

}
