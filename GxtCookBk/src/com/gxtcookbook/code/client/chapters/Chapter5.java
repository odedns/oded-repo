package com.gxtcookbook.code.client.chapters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.extjs.gxt.ui.client.binding.Converter;
import com.extjs.gxt.ui.client.binding.FieldBinding;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.core.El;
import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelFactory;
import com.extjs.gxt.ui.client.data.BeanModelLookup;
import com.extjs.gxt.ui.client.data.BeanModelReader;
import com.extjs.gxt.ui.client.data.ListLoadConfig;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.ListLoader;
import com.extjs.gxt.ui.client.data.LoadEvent;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ModelProcessor;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FormEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.LoadListener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.DateWrapper;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Slider;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.CheckBoxGroup;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.DualListField;
import com.extjs.gxt.ui.client.widget.form.DualListField.Mode;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FileUploadField;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Encoding;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Method;
import com.extjs.gxt.ui.client.widget.form.ListField;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.SliderField;
import com.extjs.gxt.ui.client.widget.form.SpinnerField;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.Validator;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gxtcookbook.code.client.GxtCookBk;
import com.gxtcookbook.code.client.RemoteGateway;
import com.gxtcookbook.code.client.RemoteGatewayAsync;
import com.gxtcookbook.code.client.ext.XSlider;
import com.gxtcookbook.code.server.model.Customer;
import com.gxtcookbook.code.server.model.Review;

public class Chapter5 extends ChapterAdapter {
	
	private static Chapter5 instance;
	
	public static Chapter5 get(){
		instance = (instance == null ? new Chapter5() : instance);
		return instance;
	}
	
	protected Chapter5(){
		super();
	}

	@Override
	protected String getTitle() {
		return "Engaging Users With Forms & Data Input";
	}

	@Override
	protected List<Recipe> applyTheseRecipes() {
		List<Recipe> recipes = new LinkedList<Recipe>();
		
		Recipe simpleForm = new Recipe("Building A Simple Form With Basic Validation"){
			@Override
			public void onApply() {
				// basic form configuration
				final FormPanel formPanel = new FormPanel();
				formPanel.setSize(465, 320);
				formPanel.setHeaderVisible(false);
				formPanel.setStyleAttribute("backgroundColor", "#fff");
				
				// setup layout structure
				FormData formData = new FormData("100%");
		        LayoutContainer main = new LayoutContainer(new ColumnLayout());

		        FormLayout formLayout = new FormLayout(LabelAlign.TOP);
		        formLayout.setLabelSeparator("");		        
		        LayoutContainer left = new LayoutContainer(formLayout);
		        left.setStyleAttribute("paddingRight", "10px");
		        main.add(left, new ColumnData(.5));

		        formLayout = new FormLayout(LabelAlign.TOP);
		        formLayout.setLabelSeparator("");
		        LayoutContainer right = new LayoutContainer(formLayout);
		        right.setStyleAttribute("paddingLeft", "10px");
		        main.add(right, new ColumnData(.5));
				
		        // setup text field
				TextField<String> name = new TextField<String>();
				name.setName("name");
				name.setMinLength(4);
				name.setMaxLength(35);
				name.setAllowBlank(false);
				name.setMessageTarget("tooltip");
				name.setFieldLabel("Full name");
				name.setEmptyText("Enter your full name");
				left.add(name, formData);
				
				// setup spinner field
				SpinnerField age = new SpinnerField();
				age.setName("age");
				age.setMinValue(18);
				age.setMaxValue(50);
				age.setFieldLabel("Age");
				age.setAllowBlank(false);
				age.setAllowDecimals(false);
				age.setAllowNegative(false);
				age.setMessageTarget("tooltip");
				age.setPropertyEditorType(Integer.class);
				age.setFormat(NumberFormat.getFormat("00"));
				left.add(age, new FormData("35%"));
				
				// setup number field
				NumberField weight = new NumberField();
				weight.setFieldLabel("Weight");
				weight.setName("weight");
				weight.setAllowNegative(false);
				weight.setAllowDecimals(true);	
				weight.setMinValue(35);
				weight.setMaxValue(150);
				weight.setMessageTarget("tooltip");
				weight.setPropertyEditorType(Double.class);
				weight.setFormat(NumberFormat.getFormat("00.0"));
				left.add(weight, new FormData("35%"));
						
				// setup radio buttons
				RadioGroup genderGrp = new RadioGroup("gender");
				genderGrp.setFieldLabel("Gender");
				Radio maleRd = new Radio();
				maleRd.setBoxLabel("Male");
				genderGrp.add(maleRd);
				Radio femaleRd = new Radio();
				femaleRd.setBoxLabel("Female");
				genderGrp.add(femaleRd);
				genderGrp.setMessageTarget("tooltip");
				left.add(genderGrp, formData);
				
				// text field for password entry
				TextField<String> pswd = new TextField<String>();
				pswd.setName("pswd");
				pswd.setPassword(true);
				pswd.setMinLength(8);
				pswd.setAllowBlank(false);
				pswd.setFieldLabel("Password");
				pswd.setMessageTarget("tooltip");
				left.add(pswd, new FormData("55%"));
				
				// text field for email entry
				TextField<String> email = new TextField<String>();
				email.setName("email");
				email.setAllowBlank(false);
				email.setMessageTarget("tooltip");
				email.setRegex("^(\\w+)([\\-+.][\\w]+)*@(\\w[\\-\\w]*\\.){1,5}([A-Za-z]){2,6}$");
				email.getMessages().setRegexText("Invalid Email Address");
			    email.setFieldLabel("Email");
			    left.add(email, formData);				
				
				// group fields with fieldset
				FieldSet skills = new FieldSet();
				formLayout = new FormLayout(LabelAlign.TOP);
		        formLayout.setLabelSeparator("");
				skills.setLayout(formLayout);
				skills.setHeading("Skills");
				right.add(skills, formData);
				
				// setup check boxes
				CheckBoxGroup langGrp = new CheckBoxGroup();
				langGrp.setFieldLabel("Languages");
				skills.add(langGrp);
				
				CheckBox javaBox = new CheckBox();
				javaBox.setName("lang");
				javaBox.setBoxLabel("Java");
				langGrp.add(javaBox);
				
				CheckBox phpBox = new CheckBox();
				phpBox.setName("lang");
				phpBox.setBoxLabel("PHP");
				langGrp.add(phpBox);
				
				CheckBox pythonBox = new CheckBox();
				pythonBox.setName("lang");
				pythonBox.setBoxLabel("Python");
				langGrp.add(pythonBox);
				
				// setup datefield
				DateField lastActive = new DateField();				
				Date minVal = new Date(new DateWrapper(2005, 0, 1).getTime());
				lastActive.setMinValue(minVal);
				lastActive.setMaxValue(new Date());
				lastActive.setName("lastactive");				
				lastActive.setFieldLabel("Last Active");
				lastActive.getPropertyEditor().setFormat(DateTimeFormat.getFormat("d MMM, yyyy"));
				skills.add(lastActive, new FormData("65%"));
								
				// setup slider field
				Slider slider = new Slider();
			    slider.setMinValue(0);
			    slider.setMaxValue(100);
			    slider.setValue(0);
			    slider.setIncrement(10);
			    slider.setMessage("{0} %");

			    SliderField sliderField = new SliderField(slider);
			    sliderField.setName("skill_level");
			    sliderField.setFieldLabel("Proficiency");
			    skills.add(sliderField);				

				// setup textarea
			    TextArea comments = new TextArea();
			    comments.setName("comments");
			    comments.setHeight(65);
			    comments.setAllowBlank(false);
			    comments.setFieldLabel("Comments");
			    comments.setMessageTarget("tooltip");
			    comments.setPreventScrollbars(true);	
			    right.add(comments, formData);
				
				formPanel.add(main, formData);
					
				// buttons and validation enforement
				Button resetBtn = new Button("Reset", new SelectionListener<ButtonEvent>() {					
					@Override
					public void componentSelected(ButtonEvent evt) {
						formPanel.reset();
					}
				});
				formPanel.addButton(resetBtn);
								
				Button submitBtn = new Button("Submit", new SelectionListener<ButtonEvent>() {					
					@Override
					public void componentSelected(ButtonEvent evt) {}
				});
				formPanel.addButton(submitBtn);
				
				FormButtonBinding btnBinder = new FormButtonBinding(formPanel);
				btnBinder.addButton(submitBtn);
				
				// serve it up now!
				GxtCookBk.getAppCenterPanel().add(formPanel);
			}
		};
		recipes.add(simpleForm);
		
		Recipe basicCombo = new Recipe("Showing Options With Combos"){
			@Override
			public void onApply() {
				// setup form
				final FormPanel formPanel = new FormPanel();
				formPanel.setSize(300, 220);				
				formPanel.setLabelSeparator("");
				formPanel.setHeaderVisible(false);
				formPanel.setStyleAttribute("backgroundColor", "#fff");				
				
				// Make RPC call via a proxy
				final RemoteGatewayAsync rpcService = (RemoteGatewayAsync) GWT.create(RemoteGateway.class);
				RpcProxy<ListLoadResult<Customer>> rpcProxy = new RpcProxy<ListLoadResult<Customer>>() {
				    @Override
				    public void load(Object cfg, AsyncCallback<ListLoadResult<Customer>> callback) {
				    	GWT.log("calling listCustomer");
				    	rpcService.listCustomers((ListLoadConfig) cfg, callback);
				    }
				};
				
				// setup the store
				ListLoader<ListLoadResult<ModelData>> loader = new BaseListLoader<ListLoadResult<ModelData>>(rpcProxy, new BeanModelReader());		        
				final ListStore<BeanModel> customerStore = new ListStore<BeanModel>(loader);		        
				
				// setup the combo
				ComboBox<BeanModel> customer = new ComboBox<BeanModel>();			    
				customer.setValueField("id");
				customer.setDisplayField("name");
				customer.setName("customer");
				customer.setFieldLabel("Customer");
				customer.setAllowBlank(false);
				customer.setMessageTarget("tooltip");
				customer.setTriggerAction(TriggerAction.ALL);
				customer.setStore(customerStore);
				customer.setLoadingText("loading please wait ...");
				customer.setEmptyText("choose a customer ...");
				formPanel.add(customer, new FormData("85%"));		        
				
				// just a dummy
				TextArea comments = new TextArea();
				comments.setName("comments");
				comments.setHeight(105);
				comments.setAllowBlank(false);
				comments.setFieldLabel("Comments");
				comments.setMessageTarget("tooltip");
				comments.setPreventScrollbars(true);	
				formPanel.add(comments, new FormData("100%"));
				
				Button resetBtn = new Button("Reset", new SelectionListener<ButtonEvent>() {					
					@Override
					public void componentSelected(ButtonEvent evt) {
						formPanel.reset();
					}
				});
				formPanel.addButton(resetBtn);
				
				// serve it
				GxtCookBk.getAppCenterPanel().add(formPanel);
			}
		};
		recipes.add(basicCombo);
		
		Recipe comboModel = new Recipe("Customizing A Combo's Bound Model"){
			@Override
			public void onApply() {
				// setup form
				final FormPanel formPanel = new FormPanel();
				formPanel.setSize(300, 235);				
				formPanel.setLabelSeparator("");
				formPanel.setHeaderVisible(false);
				formPanel.setStyleAttribute("backgroundColor", "#fff");				
				
				// make RPC call via a proxy
				final RemoteGatewayAsync rpcService = (RemoteGatewayAsync) GWT.create(RemoteGateway.class);
				RpcProxy<ListLoadResult<Customer>> rpcProxy = new RpcProxy<ListLoadResult<Customer>>() {
				    @Override
				    public void load(Object cfg, AsyncCallback<ListLoadResult<Customer>> callback) {
				    	rpcService.listCustomers((ListLoadConfig) cfg, callback);
				    }
				};
				
				// setup store
				ListLoader<ListLoadResult<ModelData>> loader = new BaseListLoader<ListLoadResult<ModelData>>(rpcProxy, new BeanModelReader());
				final ListStore<BeanModel> customerStore = new ListStore<BeanModel>(loader);
				
				// setup combo
				ComboBox<BeanModel> customer = new ComboBox<BeanModel>();			    
				customer.setValueField("id");				
				customer.setName("customer");
				customer.setFieldLabel("Customer");
				customer.setAllowBlank(false);
				customer.setMessageTarget("tooltip");
				customer.setTriggerAction(TriggerAction.ALL);
				customer.setStore(customerStore);
				customer.setLoadingText("loading please wait ...");
				customer.setEmptyText("choose a customer ...");
				customer.setDisplayField("agegrpstr");
				
				// customize the models
				customer.getView().setModelProcessor(new ModelProcessor<BeanModel>() {					
					@Override
					public BeanModel prepareData(BeanModel model) {
						Customer cust = (Customer) model.getBean();
						String group = "Adult";
						int age = cust.getAge();
						if(age >=20 && age <= 30){
							group = "Youth";
						} else if(age >=13 && age <= 19){
							group = "Teen";
						} else if(age >=3 && age <= 12){
							group = "Minor";
						}
						model.set("agegrp", group);
						model.set("agegrpstr", cust.getName() + " (" + group + ")");
						return model;
					}
				});
				formPanel.add(customer, new FormData("85%"));
				
				// just a dummy
				TextArea comments = new TextArea();
				comments.setName("comments");
				comments.setHeight(105);
				comments.setAllowBlank(false);
				comments.setFieldLabel("Comments");
				comments.setMessageTarget("tooltip");
				comments.setPreventScrollbars(true);	
				formPanel.add(comments, new FormData("100%"));
								
				Button resetBtn = new Button("Reset", new SelectionListener<ButtonEvent>() {					
					@Override
					public void componentSelected(ButtonEvent evt) {
						formPanel.reset();
					}
				});
				formPanel.addButton(resetBtn);
				
				// serve it up
				GxtCookBk.getAppCenterPanel().add(formPanel);
			}
		};
		recipes.add(comboModel);
		
		Recipe linkedCombos = new Recipe("Linking Combos") {			
			@Override
			public void onApply() {
				// setup form
				final FormPanel formPanel = new FormPanel();
				formPanel.setSize(375, 185);				
				formPanel.setLabelSeparator("");
				formPanel.setHeaderVisible(false);
				formPanel.setStyleAttribute("backgroundColor", "#fff");				
				
				// make RPC calls
				final RemoteGatewayAsync rpcService = (RemoteGatewayAsync) GWT.create(RemoteGateway.class);
				
				// proxy for customer RPC calls
				RpcProxy<ListLoadResult<Customer>> custRpcProxy = new RpcProxy<ListLoadResult<Customer>>() {
				    @Override
				    public void load(Object cfg, AsyncCallback<ListLoadResult<Customer>> callback) {
				    	rpcService.listCustomers((ListLoadConfig) cfg, callback);
				    }
				};
				
				// store for customer combo
				ListLoader<ListLoadResult<ModelData>> custLoader = new BaseListLoader<ListLoadResult<ModelData>>(custRpcProxy, new BeanModelReader());
				ListStore<BeanModel> custStore = new ListStore<BeanModel>(custLoader);
				
				// customer combo
				final ComboBox<BeanModel> customerCombo = new ComboBox<BeanModel>();			    
				customerCombo.setValueField("id");	
				customerCombo.setDisplayField("name");
				customerCombo.setName("customer");
				customerCombo.setFieldLabel("Customer");
				customerCombo.setAllowBlank(false);
				customerCombo.setMessageTarget("tooltip");
				customerCombo.setTriggerAction(TriggerAction.ALL);
				customerCombo.setStore(custStore);
				customerCombo.setLoadingText("loading please wait ...");
				customerCombo.setEmptyText("choose a customer ...");
				formPanel.add(customerCombo, new FormData("75%"));
				
				// proxy for review RPC calls
				RpcProxy<ListLoadResult<Review>> revRpcProxy = new RpcProxy<ListLoadResult<Review>>() {
				    @Override
				    public void load(Object cfg, AsyncCallback<ListLoadResult<Review>> callback) {
				    	rpcService.listReviews((ListLoadConfig) cfg, callback);
				    }
				};
				
				// store for review combo
				BaseListLoader<ListLoadResult<ModelData>> revLoader = new BaseListLoader<ListLoadResult<ModelData>>(revRpcProxy, new BeanModelReader());
				ListStore<BeanModel> revStore = new ListStore<BeanModel>(revLoader);
				
				// review combo
				final ComboBox<BeanModel> reviewCombo = new ComboBox<BeanModel>();			    
				reviewCombo.setValueField("id");	
				reviewCombo.setDisplayField("title");
				reviewCombo.setName("review");
				reviewCombo.setFieldLabel("Review");
				reviewCombo.setAllowBlank(false);
				reviewCombo.setMessageTarget("tooltip");
				reviewCombo.setTriggerAction(TriggerAction.ALL);
				reviewCombo.setStore(revStore);
				reviewCombo.setLoadingText("loading please wait ...");
				reviewCombo.setEmptyText("choose a customer review ...");
				reviewCombo.setUseQueryCache(false);
				formPanel.add(reviewCombo, new FormData("95%"));
				
				// always configure loader before it loads
				revLoader.addLoadListener(new LoadListener(){
					@Override
					public void loaderBeforeLoad(LoadEvent evt) {
						super.loaderBeforeLoad(evt);
						if(customerCombo.isValid(true)){
							BeanModel model = customerCombo.getValue();
							Customer cust = (Customer) model.getBean();
							evt.<ModelData> getConfig().set("customer", cust.getId());
						}else{
							evt.<ModelData> getConfig().set("customer", null);
						}
					}
				});
				
				customerCombo.addSelectionChangedListener(new SelectionChangedListener<BeanModel>() {					
					@Override
					public void selectionChanged(SelectionChangedEvent<BeanModel> sel) {
						// Put the review combo in context.
						// Give the user a visual cue that he is about
						// to load reviews for this customer selection
						reviewCombo.clear();
						BeanModel model = sel.getSelectedItem();
						Customer cust = (Customer) model.getBean();
						reviewCombo.setEmptyText("choose a review for " + cust.getName());
						
						// kill two birds with one stone
						// we can do without the LoadListener added to revLoader
						// by un-commenting the next section
						
						/*
						 ListLoadConfig cfg = (ListLoadConfig) revLoader.getLastConfig();
						 cfg = (cfg == null ? new BasePagingLoadConfig() : cfg);
						 cfg.set("customer", cust.getId());
						 revLoader.useLoadConfig(cfg);
						*/
					}
				});
								
				Button resetBtn = new Button("Reset", new SelectionListener<ButtonEvent>() {					
					@Override
					public void componentSelected(ButtonEvent evt) {
						formPanel.reset();
					}
				});
				formPanel.addButton(resetBtn);
				
				// serve it up
				GxtCookBk.getAppCenterPanel().add(formPanel);
			}
		};
		recipes.add(linkedCombos);
		
		Recipe multiSelect = new Recipe("Capturing Multiple Input Selection") {			
			@Override
			public void onApply() {
				// setup form
				final FormPanel formPanel = new FormPanel();
				formPanel.setSize(350, 250);				
				formPanel.setLabelSeparator("");
				formPanel.setHeaderVisible(false);
				formPanel.setStyleAttribute("backgroundColor", "#fff");				
				
				// make RPC calls via a proxy
				final RemoteGatewayAsync rpcService = (RemoteGatewayAsync) GWT.create(RemoteGateway.class);				
				RpcProxy<ListLoadResult<Customer>> rpcProxy = new RpcProxy<ListLoadResult<Customer>>() {
				    @Override
				    public void load(Object cfg, AsyncCallback<ListLoadResult<Customer>> callback) {
				    	rpcService.listCustomers((ListLoadConfig) cfg, callback);
				    }
				};
				
				// setup store
				ListLoader<ListLoadResult<ModelData>> loader = new BaseListLoader<ListLoadResult<ModelData>>(rpcProxy, new BeanModelReader());
				ListStore<BeanModel> customerStore = new ListStore<BeanModel>(loader);
				
				// set up list field
				final ListField<BeanModel> customers = new ListField<BeanModel>();
				customers.setHeight(65);
				customers.setName("customers");
				customers.setValueField("id");
				customers.setDisplayField("name");
				customers.setFieldLabel("Customers");
				customers.setMessageTarget("tooltip");
				customers.setStore(customerStore);
				formPanel.add(customers, new FormData("70%"));
				
				// set up dual list field
				DualListField<BeanModel> winners = new DualListField<BeanModel>();  
				winners.setMode(Mode.INSERT);  
				winners.setFieldLabel("Winners");  
				winners.setStyleAttribute("marginTop", "8px");
				  
				final ListField<BeanModel> srcList = winners.getFromList();  
				srcList.setDisplayField("name");      
				srcList.setStore(new ListStore<BeanModel>(loader));  
				
				final ListField<BeanModel> destList = winners.getToList();  
				destList.setDisplayField("name");
				destList.setStore(new ListStore<BeanModel>()); 
				formPanel.add(winners, new FormData("98%"));
								
				Button resetBtn = new Button("Reset", new SelectionListener<ButtonEvent>() {					
					@Override
					public void componentSelected(ButtonEvent evt) {
						formPanel.reset();
					}
				});
				formPanel.addButton(resetBtn);
				
				// load all stores bound to this loader.
				// the first listfield and the "source" 
				// listfield within the dual listfield will be loaded 
				loader.load();
				
				// serve it up
				GxtCookBk.getAppCenterPanel().add(formPanel);			
			}
		};
		recipes.add(multiSelect);
		
		Recipe fileUpload = new Recipe("Simple FileUpload & Processing") {			
			@Override
			public void onApply() {
				// basic form configuration
				final FormPanel formPanel = new FormPanel();
				formPanel.setSize(300, 120);				
				formPanel.setLabelSeparator("");
				formPanel.setHeaderVisible(false);
				formPanel.setLabelAlign(LabelAlign.TOP);
				formPanel.setStyleAttribute("backgroundColor", "#fff");
				
				// configure form for file upload				
				formPanel.setMethod(Method.POST);
				formPanel.setEncoding(Encoding.MULTIPART);
				formPanel.setAction(GWT.getModuleBaseURL() + "uploadgateway");		
				
				// setup file upload field
				FileUploadField fileField = new FileUploadField();
				fileField.setName("gxtupload");
				fileField.setAllowBlank(false);
				fileField.setFieldLabel("Uplaod File (csv, xls)");
				
				// only accept certain files
				fileField.setValidator(new Validator() {
					@Override
					public String validate(Field<?> field, String value) {
						value = value.toLowerCase();
				        String result = "Invalid File Type, Pls Be Seroius";
				        if(value.endsWith(".csv") || value.endsWith(".xls")){
				           result = null;
				        }
				        return result;
					}
				});
				formPanel.add(fileField, new FormData("90%"));
				
				// reset button
				Button resetBtn = new Button("Reset", new SelectionListener<ButtonEvent>() {					
					@Override
					public void componentSelected(ButtonEvent evt) {
						formPanel.reset();
					}
				});
				formPanel.addButton(resetBtn);
								
				// submit the form
				Button submitBtn = new Button("Upload It", new SelectionListener<ButtonEvent>() {					
					@Override
					public void componentSelected(ButtonEvent evt) {
						formPanel.mask("Gimme a minute ...");
						formPanel.submit();
					}
				});
				formPanel.addButton(submitBtn);
				
				// bind the submit button to the "validity" of the form
				FormButtonBinding btnBinder = new FormButtonBinding(formPanel);
				btnBinder.addButton(submitBtn);
								
				// reset and unmask the form
				// after file upload
				formPanel.addListener(Events.Submit, new Listener<FormEvent>() {
					public void handleEvent(FormEvent evt) {
						formPanel.reset();
						formPanel.unmask();
					};
				});
				
				// serve it up
				GxtCookBk.getAppCenterPanel().add(formPanel);
			}
		};
		recipes.add(fileUpload);
		
		Recipe formBinding = new Recipe("Binding Data Into Forms") {
			@Override
			public void onApply() {
				// configure form
				final FormPanel formPanel = new FormPanel();
				formPanel.setSize(300, 365);
				formPanel.setBodyBorder(false);
				formPanel.setLabelSeparator("");
				formPanel.setHeaderVisible(false);
				formPanel.setStyleAttribute("backgroundColor", "#fff");				
				
				// make RPC calls via a proxy
				final RemoteGatewayAsync rpcService = (RemoteGatewayAsync) GWT.create(RemoteGateway.class);				
				RpcProxy<ListLoadResult<Customer>> custRpcProxy = new RpcProxy<ListLoadResult<Customer>>() {
				    @Override
				    public void load(Object cfg, AsyncCallback<ListLoadResult<Customer>> callback) {
				    	rpcService.listCustomers((ListLoadConfig) cfg, callback);
				    }
				};
				
				// setup customers combo
				ListLoader<ListLoadResult<ModelData>> custLoader = new BaseListLoader<ListLoadResult<ModelData>>(custRpcProxy, new BeanModelReader());
				ListStore<BeanModel> custStore = new ListStore<BeanModel>(custLoader);
						        
				final ComboBox<BeanModel> customerCB = new ComboBox<BeanModel>();
				customerCB.setMinChars(5);
				customerCB.setValueField("id");	
				customerCB.setDisplayField("name");
				customerCB.setName("customer");
				customerCB.setHideLabel(true);
				customerCB.setHideTrigger(true);			    
				customerCB.setStore(custStore);
				customerCB.setLoadingText("searching ...");
				customerCB.setEmptyText("find a customer ...");
				customerCB.setTriggerAction(TriggerAction.QUERY);
				customerCB.setStyleAttribute("marginBottom", "10px");
				formPanel.add(customerCB, new FormData("90%"));		        
				
				// bind these fields
				TextField<String> name = new TextField<String>();
				name.setName("name");
				name.setAllowBlank(false);
				name.setFieldLabel("Full Name");
				name.setStyleAttribute("marginBottom", "6px");
				formPanel.add(name, new FormData("85%"));
				
				NumberField age = new NumberField();
				age.setName("age");
				age.setMinValue(1);
				age.setFieldLabel("Age");
				age.setAllowBlank(false);
				age.setAllowNegative(false);
				age.setAllowDecimals(false);		        
				age.setPropertyEditorType( Integer.class );
				age.setStyleAttribute("marginBottom", "6px");
				formPanel.add(age, new FormData("45%"));
				
				RpcProxy<ListLoadResult<Review>> revRpcProxy = new RpcProxy<ListLoadResult<Review>>() {
				    @Override
				    public void load(Object cfg, AsyncCallback<ListLoadResult<Review>> callback) {
				    	rpcService.listReviews((ListLoadConfig) cfg, callback);
				    }
				};
				
				BaseListLoader<ListLoadResult<ModelData>> revLoader = new BaseListLoader<ListLoadResult<ModelData>>(revRpcProxy, new BeanModelReader());
				ListStore<BeanModel> revStore = new ListStore<BeanModel>(revLoader);
				
				FieldSet reviewSet = new FieldSet();
				FormLayout frmLayout = new FormLayout(LabelAlign.TOP);
				frmLayout.setLabelSeparator("");
				reviewSet.setLayout(frmLayout);
				reviewSet.setHeading("Review(s)");
				
				final ListField<BeanModel> reviews = new ListField<BeanModel>();
				reviews.setHeight(75);
				reviews.setName("reviews");
				reviews.setValueField("id");
				reviews.setDisplayField("title");
				reviews.setFieldLabel("Subject");
				reviews.setStore(revStore);			    
				reviews.setStyleAttribute("marginBottom", "6px");
				reviewSet.add(reviews, new FormData("85%"));
				
				final TextArea comments = new TextArea();
				comments.setName("comments");
				comments.setHeight(50);
				comments.setAllowBlank(false);
				comments.setFieldLabel("Comment");
				comments.setMessageTarget("tooltip");
				comments.setPreventScrollbars(true);	
				reviewSet.add(comments, new FormData("90%"));
				
				formPanel.add(reviewSet, new FormData("100%"));   
				
				// binding setup
				final FormBinding formBind = new FormBinding(formPanel, true);
				formBind.removeFieldBinding( formBind.getBinding(customerCB) );		        
				
				// custom binding for reviews listfield
				final BeanModelFactory reviewModelFtry;
				reviewModelFtry = BeanModelLookup.get().getFactory(Review.class);
				
				FieldBinding reviewBinder = new FieldBinding(reviews, reviews.getName());
				reviewBinder.setConverter(new Converter() {
					@Override
					public Object convertFieldValue(Object value) {		        		
						if (value instanceof ModelData) {
				            ModelData val = (ModelData) value;
				            return val.get(reviews.getValueField());
				        } else {
				            return value;
				        }
					}
					
					@Override
					public Object convertModelValue(Object value) {
						if(value instanceof Collection<?>){
							@SuppressWarnings("unchecked")
							List<Review> valList = new ArrayList<Review>( (Collection<Review>) value );
									        			
							List<BeanModel> models = reviewModelFtry.createModel(valList);
							reviews.setSelection(models);
							
							BeanModel model = models.get(0);
							int pos = reviews.getStore().indexOf(model);
							ListView<BeanModel> listView = reviews.getListView();
							if(pos < listView.getElements().size()){
								El.fly(listView.getElement(pos)).scrollIntoView(listView.getElement(), false);
							}
							return model;
						}
						return null;
					}
				});
				formBind.addFieldBinding(reviewBinder);
				
				// do actual binding
				customerCB.addSelectionChangedListener(new SelectionChangedListener<BeanModel>() {
					@Override
					public void selectionChanged(SelectionChangedEvent<BeanModel> evt) {
						final BeanModel model = evt.getSelectedItem();
						if(model != null){
							Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand(){
								@Override
								public void execute() {									
									formBind.bind(model);
								}
							});					
						}
					}
				});
				
				// show the comment for a review
				reviews.addSelectionChangedListener(new SelectionChangedListener<BeanModel>() {
					@Override
					public void selectionChanged(SelectionChangedEvent<BeanModel> evt) {
						BeanModel selection = evt.getSelectedItem();
						if(selection != null){		        			
							Review review = (Review) evt.getSelectedItem().getBean();		        			
				    		comments.setValue(review.getBody());
						}			        			        		
					}
				});
				reviews.getStore().getLoader().load();		        		        
				
				// basic reset button
				Button resetBtn = new Button("Reset", new SelectionListener<ButtonEvent>() {					
					@Override
					public void componentSelected(ButtonEvent evt) {
						formPanel.reset();
					}
				});
				formPanel.addButton(resetBtn);
					
				// send bound model down the wire
				Button submitBtn = new Button("Submit", new SelectionListener<ButtonEvent>() {					
					@Override
					public void componentSelected(ButtonEvent evt) {
						BeanModel model = (BeanModel) formBind.getModel();
						Customer cust = (Customer) model.getBean();
						// MessageBox.alert("Msg", cust.toString() + " : " + cust.getReviews().toString(), null);
						final MessageBox box = MessageBox.wait("Progress","Saving Customer Please Wait...", "Saving...");
						rpcService.saveCustomer(cust, new AsyncCallback<Void>() {
							@Override
							public void onFailure(Throwable caught) {
								box.close();
								Info.display("Error", caught.getMessage());
								
							}
				
							@Override
							public void onSuccess(Void result) {
								box.close();
								Info.display("Message", "Saved!");
								
							}
							
						});
					}
				});
				formPanel.addButton(submitBtn);
				
				// only submit form after validating
				FormButtonBinding btnBinder = new FormButtonBinding(formPanel);
				btnBinder.addButton(submitBtn);
								
				// serve it up
				GxtCookBk.getAppCenterPanel().add(formPanel);
			}
		};
		recipes.add(formBinding);
		
		Recipe customField = new Recipe("Building A Better Slider Field") {			
			@Override
			public void onApply() {
				// configure form
				final FormPanel formPanel = new FormPanel();
				formPanel.setSize(300, 140);
				formPanel.setLabelSeparator("");
				formPanel.setHeaderVisible(false);
				formPanel.setLabelAlign(LabelAlign.TOP);
				formPanel.setStyleAttribute("backgroundColor", "#fff");	
				
				// single value detection
				XSlider slider = new XSlider();
				slider.setMinValue(1);
				slider.setMaxValue(5);
				slider.setValue(1);
				slider.setIncrement(1);
				slider.setTipRenderer(new XSlider.TipRenderer() {					
					@Override
					public String format(Slider slider, int value) {
						String tip = "";
						switch (value) {
						case 1:
							tip = "Novice";
							break;
						case 2:
							tip = "Beginner";
							break;
						case 3:
							tip = "Intermediate";
							break;
						case 4:
							tip = "Advanced";
							break;
						case 5:
							tip = "Expert";
							break;
						}
						return tip;
					}
				});
				
				SliderField sliderField = new SliderField(slider);
				sliderField.setName("skill_level");
				sliderField.setFieldLabel("Proficiency");
				formPanel.add(sliderField, new FormData("85%"));
				
				// range value detection
				slider = new XSlider();
				slider.setMinValue(1);
				slider.setMaxValue(50);
				slider.setValue(1);
				slider.setIncrement(1);
				slider.setTipRenderer(new XSlider.TipRenderer() {					
					@Override
					public String format(Slider slider, int value) {
						String tip = "";
						if(value >= 1 && value <= 12){
							tip = "Minor";
						} else if(value >= 13 && value <= 19){
							tip = "Teen";
						} else if(value >= 20 && value <= 30){
							tip = "Youth";
						} else if(value >= 31 && value <= 45){
							tip = "Adult";
						} else if(value >= 46){
							tip = "Mature Adult";
						}
						return tip;
					}
				});
				
				sliderField = new SliderField(slider);
				sliderField.setName("agegroup");
				sliderField.setFieldLabel("Age Group");
				formPanel.add(sliderField, new FormData("85%"));
				
				// basic reset button
				Button resetBtn = new Button("Reset", new SelectionListener<ButtonEvent>() {					
					@Override
					public void componentSelected(ButtonEvent evt) {
						formPanel.reset();
					}
				});
				formPanel.addButton(resetBtn);
						
				// serve it up
				GxtCookBk.getAppCenterPanel().add(formPanel);
			}
		};
		recipes.add(customField);
		
		return recipes;
	}

}
