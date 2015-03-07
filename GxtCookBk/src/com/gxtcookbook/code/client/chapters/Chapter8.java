package com.gxtcookbook.code.client.chapters;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.core.Template;
import com.extjs.gxt.ui.client.core.XTemplate;
import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelLookup;
import com.extjs.gxt.ui.client.data.BeanModelReader;
import com.extjs.gxt.ui.client.data.ListLoadConfig;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.ListLoader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ModelProcessor;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Params;
import com.extjs.gxt.ui.client.util.Util;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.RowExpander;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
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

public class Chapter8 extends ChapterAdapter {

	private static Chapter8 instance;

	public static Chapter8 get() {
		instance = (instance == null ? new Chapter8() : instance);
		
		return instance;
	}
	
	protected Chapter8(){
		super();
	}

	@Override
	protected String getTitle() {
		return "Templates & Views";
	}

	@Override
	protected List<Recipe> applyTheseRecipes() {
		List<Recipe> recipes = new LinkedList<Recipe>();
		
		Recipe tplLocal = new Recipe("Format Data With Basic Template") {
			
			/*
			 * Local convenience method
			 * to return the HTML string
			 * for constructing the Template.
			 * The HTML string contains place-holders
			 * wrapped in curly-brackets e.g {age},
			 * this will be replaced by the corresponding
			 * 'age' value in the object we apply the
			 * Template to. We can also format a value
			 * just like we are doing with {purchases},
			 * appending :number(00.00) to it means {purchases}
			 * should be formated as a standard decimal.
			 */
			private String getTemplate(){ 
			    StringBuilder sb = new StringBuilder();
			    sb.append("<p>Age: {age}</p>");
				sb.append("<p>Sex: {gender}</p>");
				sb.append("<p>Name: {name}</p>"); 			    	
				sb.append("<p>Email: {email}</p>");
				sb.append("<p>Purchases: {purchases:number(\"00.00\")}</p>");
			    return sb.toString();
			};
			
			@Override
			public void onApply() {
				// Create a Template with the HTML from getTemplate
				final Template tpl = new Template(getTemplate());
				
				// Forge some local data for demonstration
				// we use a Params object and set some
				// properties like age and gender on it.
				Params localData = new Params();
				localData.set("age", 31);
				localData.set("gender", "Male");
				localData.set("email", "chalu@lol.com");
				localData.set("name", "Odili Charles Opute");
				localData.set("purchases", 9350);
				
				// We will display the Template in this panel
				ContentPanel panel = new ContentPanel();				
				panel.setWidth(325);
				panel.setAutoHeight(true);
				panel.setHeaderVisible(false);
				panel.setBodyStyle("padding:7px");
								
				// Apply the Template on the data,
				// then use return HTML as body for
				// the panel above.
				String htmlStr = tpl.applyTemplate(localData);
				panel.addText(htmlStr);
				
				// put it on screen, equivalent to
				// RootPanel.get().add(panel)
				
				GxtCookBk.getAppCenterPanel().add(panel);
				
				// Let's deal with data across the wire this time
				// so we need another panel, just so our code is clean
				final ContentPanel panel_2 = new ContentPanel();				
				panel_2.setWidth(325);
				panel_2.setAutoHeight(true);
				panel_2.setHeaderVisible(false);
				panel_2.setBodyStyle("padding:7px");
				panel_2.setStyleAttribute("marginTop", "10px");
				
				// put it on screen, equivalent to
				// RootPanel.get().add(panel_2)
				
				GxtCookBk.getAppCenterPanel().add(panel_2);
				
				// Make RPC call, see appendixes for more info
				final RemoteGatewayAsync rpcService = (RemoteGatewayAsync) GWT.create(RemoteGateway.class);		
				AsyncCallback<Customer> callback = new AsyncCallback<Customer>() {
					@Override
					public void onFailure(Throwable caught) {
						Info.display("Error", "RPC Error");
					}
					
					@Override
					public void onSuccess(Customer result) {
						if(result != null){	
							// Just give us the data in a way 
							// we can use it with Templates.
							// We will be using the Util.getJsObject()
							// method for that, it expects a ModelData
							// object which our remote Customer is
							// exactly not, but can be made to comply
							// with since it implements BeanModelTag.
							BeanModel data = BeanModelLookup.get().getFactory(Customer.class).createModel(result);
							
							// Apply the Template to the Customer data
							// and overwrite the body of panel_2 with
							// the returned HTML.
							tpl.overwrite(panel_2.getBody().dom, Util.getJsObject(data));
						}
					}
				};
				
				// Gimme the Customer with 'id' 3!
				rpcService.getCustomer(3, callback);
			}
		};
		recipes.add(tplLocal);
		
		Recipe tplLogic = new Recipe("Doing Logic in Templates") {
			// Introduce the if and for keywords
			// used with the special tpl tag in
			// Templates. Here, if the 'all' 
			// parameter is not true then we
			// limit the template to act on data
			// where the age property is above 30,
			// and we are using the 'for' keyword
			// to iterate over the 'reviews' of a Customer 
			// and then display the title of the Review
			// Note how '>' (greater-than) is 
			// encoded as &gt <tpl if="age &gt 30">
			// and how we use slashes to escape the string.
			private String getTemplate(boolean all){ 
			    StringBuilder sb = new StringBuilder();			    
			    if(!all){
			    	sb.append("<tpl if=\"age &gt; 30\">");
			    }else{
			    	sb.append("<tpl>");
			    }
			    sb.append("<p>Age: {age}</p>");
				sb.append("<p>Sex: {gender}</p>");
				sb.append("<p>Name: {name}</p>"); 			    	
				sb.append("<p>Email: {email}</p>");
				sb.append("<p>Reviews : </p>");
				sb.append("<ul>");
				sb.append("<tpl for=\"reviews\">");	
				sb.append("<li>{title}</li>");
				sb.append("</tpl>");
				sb.append("</ul>");
				sb.append("<hr />");
				sb.append("</tpl>");
			    return sb.toString();
			};
			
			// We will reuse this code block severally
			// so a convenience function is handy!
			private void configurePanel(ContentPanel panel){
				panel.setSize(325, 185);				
				panel.setHeaderVisible(false);
				panel.setBodyStyle("padding:7px");
				panel.setScrollMode(Scroll.AUTOY);
				panel.setStyleAttribute("marginTop", "15px");
			}
			
			@Override
			public void onApply() {	
				// allPanel is where we display
				// the templated data without
				// applying the if condition in
				// the Template.
				final ContentPanel allPanel = new ContentPanel();
				configurePanel(allPanel);																
				
				// abv30Panel renders the results
				// of applying the if condition in
				// the Template, displaying only
				// customers who are above 30
				final ContentPanel abv30Panel = new ContentPanel();
				configurePanel(abv30Panel);
				
				// put them on screen, equivalent
				// to RootPanel.get().add(...)
				
				GxtCookBk.getAppCenterPanel().add(allPanel);
				GxtCookBk.getAppCenterPanel().add(abv30Panel);
				
				
				// Make RPC call, see appendixes for more info
				final RemoteGatewayAsync rpcService = (RemoteGatewayAsync) GWT.create(RemoteGateway.class);		
				AsyncCallback<List<Customer>> callback = new AsyncCallback<List<Customer>>() {
					@Override
					public void onFailure(Throwable caught) {
						// We are back, with errors anyway,
						// so turn off the 'loading' signal
						// on both panels
						allPanel.unmask();						
						abv30Panel.unmask();
						
						Info.display("Error", "RPC Error");
					}
					
					@Override
					public void onSuccess(List<Customer> result) {
						if(result != null){	
							// Create the templates as XTemplate objects
							// instead of Template objects, 
							// else we can't utilize the 'if' and 'for' logic.
							// We have one for all customers and another for
							// those above 30, note the boolean flags passed
							// into the call to our getTemplate() private method.
							XTemplate allTpl = XTemplate.create(getTemplate(true));
							XTemplate abv30Tpl = XTemplate.create(getTemplate(false));
							
							// Just give us the customer data 
							// in a way that is usable with Templates.
							// The Util.getJsObject() method expects a
							// ModelData which our remote Customer is
							// exactly not, but can be made to comply
							// with since it implements BeanModelTag.
							List<BeanModel> beans = BeanModelLookup.get().getFactory(Customer.class).createModel(result);
							
							// Apply the templates to each Customer bean,
							// remember that we are using the 'for' keyword
							// in <tpl> to display the title of a Review as we 
							// iterate over the 'reviews' of a Customer, so
							// we use Util.getJsObject(bean, 2) to say give us
							// this bean as a JsObject that has a child ('reviews')
							// which itself needs processing as a JsObject.
							for (BeanModel bean : beans) {								
								allPanel.addText( allTpl.applyTemplate(Util.getJsObject(bean, 2)) );
								abv30Panel.addText( abv30Tpl.applyTemplate(Util.getJsObject(bean, 2)) );
							}
							
							// turn off the 'loading' signal
							// on both panels, and render 
							// their contents again properly.
							allPanel.unmask();
							allPanel.layout();
							
							abv30Panel.unmask();
							abv30Panel.layout();
						}
					}
				};
				
				// show a 'loading' signal
				// to give the user a visual cue
				// that we are 'busy'
				allPanel.mask();
				abv30Panel.mask();
				
				// Now go 'over-board' and
				// fetch some real customers
				rpcService.getCustomers((ListLoadConfig) null, callback);																
			}
		};
		recipes.add(tplLogic);
		
		Recipe tplMath = new Recipe("Doing Math in Templates") {
			
			// Give us a template string.
			// Here we intend to calculate an
			// estimate of a Customer's average
			// annual purchase from the 
			// 'purchases' value. We also use 
			// {#} to show auto numbering for the
			// Customer's reviews as we iterate
			// over them with the 'for' attribute
			// in the <tpl> tag.
			private String getTemplate(){ 
			    StringBuilder sb = new StringBuilder();
				sb.append("<p>Name: {name}</p>");
				sb.append("<p>Email: {email}</p>");
				sb.append("<p>Purchase: {purchases}</p>");
				sb.append("<p>Annual Purchase Attribution: {purchases*0.0038}%</p>");
				sb.append("<p>Reviews :</p>");
				sb.append("<ul>");
				sb.append("<tpl for=\"reviews\">");	
				sb.append("<li>({#}) {title}</li>");
				sb.append("</tpl>");
				sb.append("</ul>");
			    return sb.toString();
			};
			
			@Override
			public void onApply() {
				// We will display the templated
				// data on this content panel
				final ContentPanel panel = new ContentPanel();
				panel.setSize(325, 185);				
				panel.setHeaderVisible(false);
				panel.setBodyStyle("padding:7px");
				panel.setScrollMode(Scroll.AUTOY);
				
				// put it on screen, equivalent
				// to RootPanel.get().add(panel)
			
				GxtCookBk.getAppCenterPanel().add(panel);
				
				// Create the template as XTemplate object
				// instead of Template, else we 
				// can't do any real math logic.
				final XTemplate tpl = XTemplate.create(getTemplate());
				
				// Make RPC call, see appendixes for info
				final RemoteGatewayAsync rpcService = (RemoteGatewayAsync) GWT.create(RemoteGateway.class);		
				AsyncCallback<Customer> callback = new AsyncCallback<Customer>() {
					@Override
					public void onFailure(Throwable caught) {
						panel.unmask();
						Info.display("Error", "RPC Error");
					}
					
					@Override
					public void onSuccess(Customer result) {						
						if(result != null){	
							// Just give us the customer data 
							// in a way that is usable with Templates.
							// The Util.getJsObject() method expects a
							// ModelData which our remote Customer is
							// exactly not, but can be made to comply
							// with since it implements BeanModelTag.
							BeanModel bean = BeanModelLookup.get().getFactory(Customer.class).createModel(result);
							
							// Apply the templates to each Customer bean,
							// remember that we are using the 'for' keyword
							// in <tpl> to display the title of a Review as we 
							// iterate over the 'reviews' of a Customer, so
							// we use Util.getJsObject(bean, 2) to say give us
							// this bean as a JsObject that has a child ('reviews')
							// which itself needs processing as a JsObject.
							tpl.overwrite(panel.getBody().dom, Util.getJsObject(bean, 2));						
						}
						panel.unmask();
					}
				};
				
				// show 'busy' signal on the
				// panel while we fetch data
				panel.mask();
				rpcService.getCustomer(0, callback);
			}
		};
		recipes.add(tplMath);
		
		Recipe chkBoxTpl = new Recipe("Custom ComboBox Displays") {
			
			// Simple template used to
			// iterate over all items in
			// a list (<tpl for=".">),
			// displaying the 'name' attribute
			// of the intended data as bold and
			// wrapped in a DIV styled as a combo
			// list item with 'x-combo-list-item'
			private String getTemplate(){ 
			    StringBuilder sb = new StringBuilder();
			    sb.append("<tpl for=\".\">");
				sb.append("<div class=\"x-combo-list-item\" >");
				sb.append("<span><b>{name}</b></span>"); 			    	
				sb.append("</div>");
				sb.append("</tpl>");
			    return sb.toString();
			}
			
			// Advance template used to
			// iterate over all items in
			// a list (<tpl for=".">),
			// displaying the 'name' attribute
			// of the intended data flanked with
			// a standard 'gender' icon on the 
			// left and 'age' attribute of the
			// intended data on the right, all
			// wrapped in a DIV styled as a combo
			// list item with 'x-combo-list-item'.
			// Some extra CSS was used to achieve
			// the final output, see the section
			// designated 'Combo with custom template'
			// in the GxtCookBk.css file
			private String getAdvTemplate(){ 
			    StringBuilder sb = new StringBuilder();
			    sb.append("<tpl for=\".\">");
				sb.append("<div class=\"x-combo-list-item\" >");
				sb.append("<span class=\"tpl-lft {gender}\"></span>");
				sb.append("<span class=\"tpl-lft\">{name}</span>");
				sb.append("<span class=\"tpl-rgt\">{age} Yrs</span>");
				sb.append("</div>");
				sb.append("</tpl>");
			    return sb.toString();
			}
		  	
		  	// A handy way to easily configure our Combo's
			private void configureCombo(ComboBox<BeanModel> combo, String label){
				combo.setValueField("id");
				combo.setDisplayField("name");
				combo.setFieldLabel(label);
				combo.setTriggerAction(TriggerAction.ALL);	
				combo.setEmptyText("choose a customer ...");
				combo.setLoadingText("loading please wait ...");
			}
			
			@Override
			public void onApply() {
				// A form to render the combo's
				FormPanel panel = new FormPanel();
				panel.setWidth(350);
				panel.setLabelSeparator("");
				panel.setHeaderVisible(false);
				panel.setLabelAlign(LabelAlign.TOP);
				
				// Make RPC call via a proxy, see appendixes for info
				final RemoteGatewayAsync rpcService = (RemoteGatewayAsync) GWT.create(RemoteGateway.class);
				RpcProxy<ListLoadResult<Customer>> rpcProxy = new RpcProxy<ListLoadResult<Customer>>() {
				    @Override
				    public void load(Object cfg, AsyncCallback<ListLoadResult<Customer>> callback) {
				    	rpcService.listCustomers((ListLoadConfig) cfg, callback);
				    }
				};
				
				// setup the store used by the combo's
				ListLoader<ListLoadResult<ModelData>> loader = new BaseListLoader<ListLoadResult<ModelData>>(rpcProxy, new BeanModelReader());		        
				ListStore<BeanModel> customerStore = new ListStore<BeanModel>(loader);
				
				// The first combo
				// this one uses the simple
				// template to show bold names,
				// we'll call them 'Bold Customers'
				ComboBox<BeanModel> combo1 = new ComboBox<BeanModel>();				
				combo1.setStore(customerStore);
				combo1.setTemplate(getTemplate());
				configureCombo(combo1, "Bold Customers");
				panel.add(combo1);
				
				// The second combo
				// this one uses the advance
				// template to show customer name
				// with their gender on the left
				// and their age on the right,
				// we'll call them 'Gender Sensitive Customers'
				ComboBox<BeanModel> combo2 = new ComboBox<BeanModel>();			
				combo2.setStore(customerStore);
				combo2.setTemplate(getAdvTemplate());
				configureCombo(combo2, "Gender Sensitive Customers");
				panel.add(combo2);
				
				// put the form on screen, equivalent
				// to RootPanel.get().add(panel)
				
				GxtCookBk.getAppCenterPanel().add(panel);
			
			
			}
		};
		recipes.add(chkBoxTpl);
		


		Recipe rowExpandr = new Recipe("Give Details With RowExpander") {
			
			private native String getTemplate()/*-{
				var str = "<p><b>Company:</b> {name}</p>";
				str += "<p><b>Summary:</b> {about}</p>";
				return str;
			}-*/;
			
			@Override
			public void onApply() {
				// A list for the column configurations
				List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
			
				// Create columns as ColumnConfig objects, add to the above list
				ColumnConfig column = new ColumnConfig("name", "Company", 200);
				configs.add(column);
			
				column = new ColumnConfig("last", "Last", 75);
				//format value as US currency
				column.setNumberFormat(NumberFormat.getCurrencyFormat());
				configs.add(column);
			
				column = new ColumnConfig("date", "Last Updated", 125);
				// format date as Oct 1, 1960
				column.setDateTimeFormat(DateTimeFormat
						.getFormat("MMM dd, yyyy"));
				configs.add(column);
			
				// Create the expander with a Template,
				// its just HTML with mapped place-holders 
				// (properties in the intended bean model) 
				// wrapped in curly brackets e.g {name} or {about}
				// RowExpander is a special ColumnConfig
				XTemplate tpl = XTemplate.create(getTemplate());
				RowExpander expander = new RowExpander();
				expander.setTemplate(tpl);
			
				// make the expander the first column!
				configs.add(0, expander);
			
				ListStore<ModelData> store = new ListStore<ModelData>();
				store.add(LocalData.getStocks());
			
				ColumnModel cm = new ColumnModel(configs);
				Grid<ModelData> grid = new Grid<ModelData>(store, cm);
				
				// Our RowExpander template uses a {about} place-holder
				// meaning it expects an 'about' property in the bean model.
				// We'll quickly setup one with a ModelProcessor since there's 
				// no 'about' property in our beans.
				grid.setModelProcessor(new ModelProcessor<ModelData>() {
					@Override
					public ModelData prepareData(ModelData model) {
						Stock stk = (Stock) model;
						double last = stk.getLast();
						Date date = stk.getLastTrans();
						double change = stk.getChange();
			
						StringBuilder sb = new StringBuilder(stk.getName());
						sb.append(" identified as ").append(stk.getSymbol());
						sb.append(change < 0 ? ", lost " : ", gained ");
						sb.append(NumberFormat.getDecimalFormat().format(
								Math.abs(change)));
						sb.append(" over its ").append(
								NumberFormat.getCurrencyFormat().format(last));
						sb.append(" share value on ");
						sb.append(DateTimeFormat.getFormat("MMMM dd, yyyy")
								.format(date));
			
						// Put the 'about' property in this model
						stk.set("about", sb.toString());
						return stk;
					}
				});
				
				// RowExpander is a special 
				// ColumnConfig, actually a plugin!
				grid.addPlugin(expander);
			
				grid.setBorders(true);
				grid.setSize(400, 300);
				grid.setAutoExpandColumn("name");
			
				// show it up, equivalent to
				// RootPanel.get().add(grid)
			
				GxtCookBk.getAppCenterPanel().add(grid);
			}
		};
		recipes.add(rowExpandr);
		
		Recipe listView = new Recipe("Custom Displays Using ListView") {		
			
			// Give us a template string,
			// however we are using JSNI,
			// hence the JavaScript syntax.
			// Can equally be achieved with a
			// StringBuilder by appending 
			// these same strings to it.
			private native String getTemplate()/*-{
				return ['<tpl for=".">',  
			       '<div class="x-customer-item">', 
				       '<div class="name">{name}</div>',  
				       '<div class="email">E-mail:{email}</div>',  
				       '<div class="purchases">Purchases:{purchases}</div>',  
			       '</div>',  
			   '</tpl>',  
			   ''].join("");
			}-*/;
			
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
				
				// Create the list-view,
				// giving it the store of beans
				// and the template from a call
				// to our getTemplate().
				// We also configure setItemSelector()
				// and setSelectStyle() responsible for
				// how items in the list behave when
				// they are selected.
			   ListView<BeanModel> listView = new ListView<BeanModel>();
			   listView.setStore(store);
			   listView.setLoadingText("Loading ...");
			   listView.setItemSelector("div.x-customer-item");
			   listView.setSelectStyle("x-customer-item-sel");
			   listView.setTemplate(getTemplate());
			  
			   // Display it from within a panel
			   ContentPanel ctPanel = new ContentPanel();   
			   ctPanel.setBodyBorder(false);   
			   ctPanel.setHeaderVisible(false);   
			   ctPanel.setButtonAlign(HorizontalAlignment.CENTER);   
			   ctPanel.setLayout(new FitLayout());   
			   ctPanel.setSize(470, 270);  
			   ctPanel.add(listView);   
			   
			   // All is now set,
			   // go fetch the data!
			   loader.load();
			   
			   // show it up, equivalent to
			   // RootPanel.get().add(ctPanel)
				
				GxtCookBk.getAppCenterPanel().add(ctPanel);
			}
		};
		recipes.add(listView);
		
		return recipes;
	}

}
