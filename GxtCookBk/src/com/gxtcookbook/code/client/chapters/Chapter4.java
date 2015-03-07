package com.gxtcookbook.code.client.chapters;

import java.util.LinkedList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.custom.Portal;
import com.extjs.gxt.ui.client.widget.custom.Portlet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.HtmlEditor;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowData;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.gxtcookbook.code.client.GxtCookBk;
import com.gxtcookbook.code.client.ext.WizardPanel;

public class Chapter4 extends ChapterAdapter {

	private static Chapter4 instance;

	public static Chapter4 get() {
		instance = (instance == null ? new Chapter4() : instance);
		return instance;
	}

	protected Chapter4() {
		super();
	}

	@Override
	protected String getTitle() {
		return "Crafting UI Real-Estate";
	}

	@Override
	protected List<Recipe> applyTheseRecipes() {
		List<Recipe> recipes = new LinkedList<Recipe>();

		Recipe accordion = new Recipe(
				"Organizing Navigation With AccordionLayout") {
			
			public HtmlContainer makeLinks(String[] links){
				StringBuilder sb = new StringBuilder("<ul class='accordion-list'>");
				for (String link : links) {
					sb.append("<li>").append(link).append("</li>");
				}
				sb.append("</ul>");
				HtmlContainer html = new HtmlContainer(sb.toString());
				return html;
			}

			@Override
			public void onApply() {
				// create the accordion
				ContentPanel accordionCt = new ContentPanel();
				accordionCt.setSize(180, 200);
				accordionCt.setHeading("Navigation");
				accordionCt.setBodyBorder(false);
				accordionCt.setLayout(new AccordionLayout());
				
				// add the products panel
				ContentPanel panel = new ContentPanel();
				panel.setHeading("Products");
				accordionCt.add(panel);
				
				// put links into "products"
				String[] links = new String[]{"view", "create", "search"};
				panel.add(makeLinks(links));
				
				// add the sales panel
				panel = new ContentPanel();
				panel.setHeading("Sales");
				accordionCt.add(panel);
				
				// put links into "sales"
				links = new String[]{"orders", "returns", "invoices"};
				panel.add(makeLinks(links));
				
				// add the reports panel
				panel = new ContentPanel();
				panel.setHeading("Reports");
				accordionCt.add(panel);
				
				// put links into "reports"
				links = new String[]{"summary", "stock", "Ad-hoc"};
				panel.add(makeLinks(links));				
				
				// add the issues panel
				panel = new ContentPanel();
				// setAnimCollapse(false);
				panel.setHeading("Issues");
				panel.setBodyStyle("padding:10px;");
				panel.addText("<p>we don't have any <i>issues</i> right ...<p>");
				accordionCt.add(panel);
				/*
				 * GxtCookbk is the application's entry point class.
				 * We access its main content panel using the 
				 * static GxtCookBk.getAppCenterPanel() call.
				 * We add our panel to the main content panel. 
				 */
				GxtCookBk.getAppCenterPanel().add(accordionCt);
			}
		};
		recipes.add(accordion);

		Recipe anchor = new Recipe("Snap Components Even When Resized") {
			@Override
			public void onApply() {
				
				// create a window
				Window window = new Window();
				window.setPlain(true);
				window.setSize(400, 265);
				window.setHeading("Robust Component Anchoring - Resize Now!");
				window.setLayout(new FitLayout());
			
				// create a FormPanel
				FormPanel form = new FormPanel();
				form.setBorders(false);
				form.setBodyBorder(false);
				form.setLabelWidth(55);
				form.setPadding(5);
				form.setHeaderVisible(false);

				// add TextField to the form
				TextField<String> field = new TextField<String>();
				field.setFieldLabel("Sent To");
				form.add(field, new FormData("100%"));
				// add TextField to the form
				field = new TextField<String>();
				field.setFieldLabel("Subject");
				form.add(field, new FormData("100%"));
				// add HtmlEditor to the form
				HtmlEditor html = new HtmlEditor();
				html.setHideLabel(true);
				form.add(html, new FormData("100% -53"));

				// add the buttons and the form to the window.
				window.addButton(new Button("Send"));
				window.addButton(new Button("Cancel"));
				window.add(form);
				// show the window
				window.show();
			}
		};
		recipes.add(anchor);

		Recipe border = new Recipe("UI Cardiniality With BorderLayout") {
			@Override
			public void onApply() {
				
				// create the main panel
				ContentPanel mainView = new ContentPanel();
				mainView.setSize(550, 380);
				mainView.setHeaderVisible(false);
				mainView.setBodyBorder(false);
				mainView.setLayout(new BorderLayout());
				
				// setup west-side
				ContentPanel panel = new ContentPanel();
				panel.setHeading("West");
				BorderLayoutData westData = new BorderLayoutData(
						LayoutRegion.WEST);
				westData.setSize(130);
				westData.setMinSize(100);
				westData.setMaxSize(180);
				westData.setSplit(true);
				westData.setCollapsible(true);
				westData.setMargins(new Margins(0, 5, 0, 0));
				mainView.add(panel, westData);
								
				// setup center
				panel = new ContentPanel();
				panel.setHeading("Center");
				panel.setStyleAttribute("background", "#fff");
				BorderLayoutData centerData = new BorderLayoutData(
						LayoutRegion.CENTER);
				centerData.setMargins(new Margins(5));
				mainView.add(panel, centerData);

				// setup north-side
				panel = new ContentPanel();
				panel.setHeading("North");
				BorderLayoutData northData = new BorderLayoutData(LayoutRegion.NORTH,100);
				northData.setCollapsible(true);  
				northData.setFloatable(true);  
				northData.setHideCollapseTool(true);  
				northData.setSplit(true);  
				northData.setMargins(new Margins(0, 0, 5, 0));  
				mainView.add(panel, northData);
				
				//setup east-side
				 panel = new ContentPanel();
				 panel.setHeading("East");
				 BorderLayoutData eastData = new BorderLayoutData(
							LayoutRegion.EAST);
					centerData.setMargins(new Margins(0, 0, 0, 5));
				 mainView.add(panel, eastData);
				 
				 // setup south-side
				 panel = new ContentPanel();
				 panel.setHeading("South");
				 BorderLayoutData southData = new BorderLayoutData(LayoutRegion.SOUTH, 100);  
				 southData.setSplit(true);  
				 southData.setCollapsible(true);  
				 southData.setFloatable(true);  
				 southData.setMargins(new Margins(5, 0, 0, 0));
				 mainView.add(panel, southData);

				/*
				 * GxtCookbk is the application's entry point class.
				 * We access its main content panel using the 
				 * static GxtCookBk.getAppCenterPanel() call.
				 * We add our viewPort to the main content panel. 
				 */
				GxtCookBk.getAppCenterPanel().add(mainView);
			}
		};
		recipes.add(border);

		Recipe cards = new Recipe("Building A Basic Wizard With CardLayout") {
			@Override
			public void onApply() {
				
				// create a WizardPanel
				WizardPanel wizardPanel = new WizardPanel();
				wizardPanel.setSize(450, 300);
				wizardPanel.setHeading("GXT Wizard");
				wizardPanel.setStyleAttribute("background", "#fff");

				// create the first card.
				LayoutContainer card = new LayoutContainer();
				card.addText("<h1>Please click Next to sign in ..</h1>");
				// add card to the panel.
				wizardPanel.addCard(card);

				// create the second card.
				card = new LayoutContainer(new FormLayout());
				card.addText("<h1>Enter your login details below</h1>");

				// create username field and add it to the card.
				TextField<String> usrName = new TextField<String>();
				usrName.setName("username");
				usrName.setAllowBlank(false);
				usrName.setFieldLabel("Username");
				card.add(usrName);

				// create the password field and add it to the card.
				TextField<String> pswd = new TextField<String>();
				pswd.setName("pswd");
				pswd.setPassword(true);
				pswd.setAllowBlank(false);
				pswd.setFieldLabel("Password");
				card.add(pswd);
				wizardPanel.addCard(card);

				card = new LayoutContainer();
				card.addText("<h1>Welcome to GXT WizardPanel!</h1>");
				// add card to the panel
				wizardPanel.addCard(card);

				/*
				 * GxtCookbk is the application's entry point class.
				 * We access its main content panel using the 
				 * static GxtCookBk.getAppCenterPanel() call.
				 * We add our panel to the main content panel. 
				 */
				GxtCookBk.getAppCenterPanel().add(wizardPanel);
			}
		};
		recipes.add(cards);

		Recipe rows = new Recipe("RowLayout Vertical And Horizontal Aligning") {
			@Override
			public void onApply() {
				
				// create a ContentPanel with RowLayout
				ContentPanel panel = new ContentPanel();
				panel.setSize(300, 200);
				panel.setHeading("RowLayout: Vertical Orientation");
				panel.setLayout(new RowLayout());
				
				Text item1 = new Text("First Row");
				item1.setBorders(true);
				Text item2 = new Text("Second Row");
				item2.setBorders(true);
				
				// add the rows
				panel.add(item1, new RowData(1, -1, new Margins(4)));
				panel.add(item2, new RowData(1, 1, new Margins(0, 4, 0, 4)));
				
				/*
				 * GxtCookbk is the application's entry point class.
				 * We access its main content panel using the 
				 * static GxtCookBk.getAppCenterPanel() call.
				 * We add our panel to the main content panel, specifying a margin of
				 * 5 pixels for all dimensions of the panel 
				 */
				GxtCookBk.getAppCenterPanel().add(panel, new FlowData(5));
				// create a ContentPanel with RowLayout
				panel = new ContentPanel();
				panel.setSize(300, 200);
				panel.setHeading("RowLayout: Horizontal Orientation");
				panel.setLayout(new RowLayout(Orientation.HORIZONTAL));
				
				item1 = new Text("First Row");
				item1.setBorders(true);
				item2 = new Text("Second Row");
				item2.setBorders(true);
				// add the rows
				panel.add(item1, new RowData(-1, 1, new Margins(4)));
				panel.add(item2, new RowData(1, 1, new Margins(4, 0, 4, 0)));
				
				
				/*
				 * GxtCookbk is the application's entry point class.
				 * We access its main content panel using the 
				 * static GxtCookBk.getAppCenterPanel() call.
				 * We add our panel to the main content panel, specifying a margin of
				 * 5 pixels for all dimentions of the panel 
				 */
				GxtCookBk.getAppCenterPanel().add(panel, new FlowData(5));
			}
		};
		recipes.add(rows);

		Recipe colums = new Recipe(
				"Building &quot;grids&quot; With ColumnLayout") {
			@Override
			public void onApply() {
				LayoutContainer main = new LayoutContainer(new ColumnLayout());
				main.setBorders(true);
				main.setSize(400, 250);
				main.setStyleAttribute("padding", "10px");
				
				LayoutContainer panel = new LayoutContainer(new CenterLayout());
				panel.setHeight(220);
				panel.setBorders(true);
				panel.add(new Html("Left Column"));
				main.add(panel, new ColumnData(.33));
				
				panel = new LayoutContainer(new CenterLayout());
				panel.setHeight(220);
				panel.setBorders(true);
				panel.add(new Html("Mid Column"));
				main.add(panel, new ColumnData(.33));
				
				panel = new LayoutContainer(new CenterLayout());
				panel.setHeight(220);
				panel.setBorders(true);
				panel.add(new Html("Right Column"));
				main.add(panel, new ColumnData(.33));
				
				/*
				 * GxtCookbk is the application's entry point class.
				 * We access its main content panel using the 
				 * static GxtCookBk.getAppCenterPanel() call.
				 * We add our panel to the main content panel. 
				 */
				GxtCookBk.getAppCenterPanel().add(main);
			}
		};
		recipes.add(colums);

		Recipe dashboards = new Recipe("Building DashBoards") {

			private void configurePortlet(final ContentPanel portlet) {
				portlet.setCollapsible(true);
				portlet.setAnimCollapse(false);
				portlet.setStyleName("x-window-tc", true);
				portlet.getHeader().addTool(new ToolButton("x-tool-gear"));
				
				ToolButton closeTb = new ToolButton("x-tool-close", new SelectionListener<IconButtonEvent>(){
					@Override
					public void componentSelected(IconButtonEvent evt) {
						portlet.removeFromParent();
					}
				});
				portlet.getHeader().addTool(closeTb);
			}

			@Override
			public void onApply() {
				Portal portal = new Portal(3);
				portal.setColumnWidth(0, .50);
				portal.setColumnWidth(1, .25);
				portal.setColumnWidth(2, .25);
				portal.setStyleAttribute("backgroundColor", "white");
				
				// create portlet
				Portlet portlet = new Portlet();
				portlet.setHeight(250);
				configurePortlet(portlet);
				portlet.setHeading("Portlet 1");
				portlet.addText("<h1>Portlet 1</h1>");
				// add portlet to the portal
				portal.add(portlet, 0);
				
				// create portlet
				portlet = new Portlet();
				portlet.setHeight(200);
				configurePortlet(portlet);
				portlet.setHeading("Portlet 2");
				portlet.addText("<h1>Portlet 2</h1>");
				
				// add portlet to the portal
				portal.add(portlet, 1);
				
				// create portlet
				portlet = new Portlet();
				portlet.setHeight(65);
				portlet.setHeaderVisible(false);
				portlet.setLayout(new FormLayout());
				
				// create and add search field to the portlet.
				TextField<String> search = new TextField<String>();
				search.setHideLabel(true);
				search.setEmptyText("Search ...");
				search.setStyleAttribute("padding", "10px 0 0 10px");
				portlet.add(search);
				// add portlet to the portal
				portal.add(portlet, 2);
				
				/*
				 * GxtCookbk is the application's entry point class.
				 * We access its main content panel using the 
				 * static GxtCookBk.getAppCenterPanel() call.
				 * We add our portlet to the main content panel. 
				 */
				GxtCookBk.getAppCenterPanel().add(portal);
			}
		};
		recipes.add(dashboards);

		return recipes;
	}

}
