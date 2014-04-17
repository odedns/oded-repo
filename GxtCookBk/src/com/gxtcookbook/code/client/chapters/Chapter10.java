package com.gxtcookbook.code.client.chapters;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.vectomatic.dnd.DataTransfer;
import org.vectomatic.dnd.DropPanel;
import org.vectomatic.file.File;
import org.vectomatic.file.events.DragOverEvent;
import org.vectomatic.file.events.DragOverHandler;
import org.vectomatic.file.events.DropEvent;
import org.vectomatic.file.events.DropHandler;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.BaseTreeLoader;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelReader;
import com.extjs.gxt.ui.client.data.ListLoadConfig;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.ListLoader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.data.TreeLoader;
import com.extjs.gxt.ui.client.dnd.DND.Feedback;
import com.extjs.gxt.ui.client.dnd.DND.Operation;
import com.extjs.gxt.ui.client.dnd.DragSource;
import com.extjs.gxt.ui.client.dnd.DropTarget;
import com.extjs.gxt.ui.client.dnd.GridDragSource;
import com.extjs.gxt.ui.client.dnd.GridDropTarget;
import com.extjs.gxt.ui.client.dnd.ListViewDragSource;
import com.extjs.gxt.ui.client.dnd.ListViewDropTarget;
import com.extjs.gxt.ui.client.dnd.StatusProxy;
import com.extjs.gxt.ui.client.dnd.TreePanelDragSource;
import com.extjs.gxt.ui.client.dnd.TreePanelDropTarget;
import com.extjs.gxt.ui.client.event.DNDEvent;
import com.extjs.gxt.ui.client.fx.Draggable;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.ListViewSelectionModel;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabItem.HeaderItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel.TreeNode;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.media.client.Audio;
import com.google.gwt.media.client.MediaBase;
import com.google.gwt.media.client.Video;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Image;
import com.gxtcookbook.code.client.FileModel;
import com.gxtcookbook.code.client.FileService;
import com.gxtcookbook.code.client.FileServiceAsync;
import com.gxtcookbook.code.client.FolderModel;
import com.gxtcookbook.code.client.GxtCookBk;
import com.gxtcookbook.code.client.RemoteGateway;
import com.gxtcookbook.code.client.RemoteGatewayAsync;
import com.gxtcookbook.code.client.data.LocalData;
import com.gxtcookbook.code.client.icons.IconSet;
import com.gxtcookbook.code.server.model.Customer;

public class Chapter10 extends ChapterAdapter {

	private static Chapter10 instance;
	private static LayoutContainer centerPanel = GxtCookBk.getAppCenterPanel();

	protected Chapter10() {
		super();
	}

	public static Chapter10 get() {
		instance = (instance == null ? new Chapter10() : instance);
		return instance;
	}

	@Override
	protected String getTitle() {
		return "Drag and Drop";
	}

	@Override
	protected List<Recipe> applyTheseRecipes() {
		List<Recipe> recipes = new LinkedList<Recipe>();

		Recipe dragable = new Recipe("Drag Any Component") {

			private Button makeBtn(String label) {
				Button btn = new Button(label);
				btn.setStyleAttribute("margin", "8px");
				return btn;
			}
			
			@Override
			public void onApply() {
				Draggable canDrag = null;
			
				// Drag within the browser's
				// viewable region (viewport)
				Button btn1 = makeBtn("Drag me within viewport");
				canDrag = new Draggable(btn1);
				// centerPanel is the main content panel of the application.
				// it is initialized statically in the recipe class.
				centerPanel.add(btn1);
			
				// Constrained vertically,
				// thus we can only drag
				// horizontally
				Button btn2 = makeBtn("Drag me within viewport, but only horizontally");
				canDrag = new Draggable(btn2);
				canDrag.setConstrainVertical(true);
				centerPanel.add(btn2);
			
				// Constrained horizontally,
				// thus we can only drag
				// vertically
				Button btn3 = makeBtn("Drag me within viewport, but only vertically");
				canDrag = new Draggable(btn3);
				canDrag.setConstrainHorizontal(true);
				// centerPanel is the main content panel of the application.
				// it is initialized statically in the recipe class.
				centerPanel.add(btn3);
			
				ContentPanel textPanel = new ContentPanel();
				textPanel.setWidth(250);
				textPanel.setStyleAttribute("margin", "8px");
				textPanel.addText("<p>This dude is draggable, only with the header, but constrained to a region</p>");
			
				// drag with the header,
				// and only within the region
				// defined by centerPanel
				canDrag = new Draggable(textPanel, textPanel.getHeader());
				canDrag.setContainer(centerPanel);
				// centerPanel is the main content panel of the application.
				// it is initialized statically in the recipe class.
				centerPanel.add(textPanel);
			}

			
		};
		recipes.add(dragable);

		Recipe dndInCmp = new Recipe("Simple DnD Within Components") {

			@Override
			public void onApply() {
				centerPanel.setLayout(new ColumnLayout());
				
				// 1. Simple DnD in Grid
				// A list for the column configurations
				List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
				
				// This is how you would make a normal column,
				// give it an id, label, and initial width
				// the id is a property in the bean you are trying to display
				ColumnConfig column = new ColumnConfig("name", "Company", 200);
				configs.add(column);
				
				column = new ColumnConfig("last", "Last", 50);
				column.setAlignment(HorizontalAlignment.RIGHT);
				column.setNumberFormat(NumberFormat.getCurrencyFormat());
				configs.add(column);
				
				column = new ColumnConfig("change", "Change", 100);
				column.setAlignment(HorizontalAlignment.RIGHT);
				column.setNumberFormat(NumberFormat.getCurrencyFormat());
				configs.add(column);
				
				// Populate the store with data
				ListStore<ModelData> gridStore = new ListStore<ModelData>();
				gridStore.add(LocalData.getStocks());
				
				// Create the grid with a ColumnModel instantiated
				// from our list of column configurations, and a store
				ColumnModel cm = new ColumnModel(configs);
				Grid<ModelData> grid = new Grid<ModelData>(gridStore, cm);
				
				// Some cosmetics on our beloved grid
				grid.setHeight(300);
				grid.setBorders(true);
				// show color strips for alternate rows
				grid.setStripeRows(true);
				// expand the 'name' column as much as possible
				grid.setAutoExpandColumn("name");
				
				// DnD Setup
				// allow DnD re-ordering of columns
				grid.setColumnReordering(true);
				
				// allow DnD re-ordering of rows
				new GridDragSource(grid);
				GridDropTarget gridTarget = new GridDropTarget(grid);
				gridTarget.setAllowSelfAsSource(true);
				gridTarget.setFeedback(Feedback.INSERT);
				
				// show it up, equivalent to
				// RootPanel.get().add(grid)
				centerPanel.add(grid, new ColumnData(.35));
				
				// 2. Simple DnD in Tree
				// Make RPC call via a proxy
				final FileServiceAsync fileService = (FileServiceAsync) GWT
						.create(FileService.class);
				RpcProxy<List<FileModel>> rpcProxy = new RpcProxy<List<FileModel>>() {
					@Override
					public void load(Object cfg,
							AsyncCallback<List<FileModel>> callback) {
						fileService
								.getFolderChildren((FileModel) cfg, callback);
					}
				};
				
				// setup the store
				TreeLoader<FileModel> loader = new BaseTreeLoader<FileModel>(
						rpcProxy) {
					@Override
					public boolean hasChildren(FileModel parent) {
						return parent instanceof FolderModel;
					}
				};
				TreeStore<FileModel> treeStore = new TreeStore<FileModel>(
						loader);
				TreePanel<FileModel> tree = new TreePanel<FileModel>(treeStore);
				tree.setHeight(300);
				tree.setBorders(true);
				tree.setDisplayProperty("name");
				// load nodes automatically
				tree.setAutoLoad(true);
				
				// Move nodes around
				// within the tree.
				new TreePanelDragSource(tree);
				TreePanelDropTarget treeTarget = new TreePanelDropTarget(tree);
				treeTarget.setAllowSelfAsSource(true);
				treeTarget.setFeedback(Feedback.BOTH);
				
				// Place tree on screen like 
				// RootPanel.get().add(tree)
				centerPanel.add(tree, new ColumnData(.40));
			}
		};
		recipes.add(dndInCmp);

		Recipe dndAcrossCmp = new Recipe("DnD Across Components") {

			private ModelData createRoot(String name, String gender) {
				ModelData m = new BaseModelData();
				m.set("name", name);
				m.set("gender", gender);
				return m;
			}
			// generate a template for the list
			private String getTemplate(){
				StringBuilder sb = new StringBuilder("<tpl for=\".\">");
				sb.append("<div class=\"x-customer-item\">");
				sb.append("<div class=\"name\">{name}</div>");
				sb.append("<div class=\"email\">E-mail:{email}</div>");
				sb.append("<div class=\"purchases\">Purchases:{purchases}</div>");
				sb.append("</div></tpl>");
				return sb.toString();
			}
			
			@Override
			public void onApply() {								
				// some layout structure
				centerPanel.setLayout(new ColumnLayout());
				LayoutContainer treeCt = new LayoutContainer();
				centerPanel.add(treeCt, new ColumnData(.35));
				
				// Setup Tree from TreeStore
				TreeStore<ModelData> treeStore = new TreeStore<ModelData>();
				TreePanel<ModelData> tree = new TreePanel<ModelData>(treeStore);
				tree.setDisplayProperty("name");
			
				// some eye-candy
				tree.getStyle().setNodeCloseIcon(AbstractImagePrototype.create(IconSet.misc.people()));
				tree.getStyle().setNodeOpenIcon(AbstractImagePrototype.create(IconSet.misc.people()));
				treeCt.add(tree);				
			
				// add some root nodes
				// we will be dropping stuff
				// here later
				treeStore.add(createRoot("Male", "male"), false);
				treeStore.add(createRoot("Female", "female"), false);
				tree.setLeaf(treeStore.getRootItems().get(0), false);
				tree.setLeaf(treeStore.getRootItems().get(1), false);
				
				TreePanelDropTarget treeTarget = new TreePanelDropTarget(tree){
					// This won't let you drop
					// just about anything here!
					// If U dragged "males" from the
					// list then U can only drop it
					// under the "Male" tree-node
					@Override
					protected void handleAppend(DNDEvent evt, TreeNode item) {
						if(item != null){
							 ModelData treeNodeModel = item.getModel();
							 String nodeGrp = treeNodeModel.get("gender").toString();
							 
							 List<BeanModel> sel = evt.getData();
							 BeanModel bm = sel.get(0);
							 String modelGrp = bm.get("gender").toString();
							 if(!nodeGrp.equalsIgnoreCase(modelGrp)){
								 evt.setCancelled(true);
								 return;
							 }						 
							 super.handleAppend(evt, item);
						}							 
					}
				};	
				
				// only components with this
				// same group can participate
				// in this DnD operation
				treeTarget.setGroup("GenderBiased");
				
				// Items dragged here are
				// "moved" from their source
				// and appended to any existing
				// nodes within where they are dropped.
				treeTarget.setOperation(Operation.MOVE);
				treeTarget.setFeedback(Feedback.APPEND);								
			
				// Setup ListView, will act as our drag source
				// Make RPC call via a proxy, see appendixes for info.
				// here we want to fetch a bunch of Customer beans
				final RemoteGatewayAsync rpcService = (RemoteGatewayAsync) GWT
						.create(RemoteGateway.class);
				RpcProxy<ListLoadResult<Customer>> rpcProxy = new RpcProxy<ListLoadResult<Customer>>() {
					@Override
					public void load(Object cfg,
							AsyncCallback<ListLoadResult<Customer>> callback) {
						rpcService.listCustomers((ListLoadConfig) cfg, callback);
					}
				};
			
				// setup the store for beans
				ListLoader<ListLoadResult<BeanModel>> loader = new BaseListLoader<ListLoadResult<BeanModel>>(
						rpcProxy, new BeanModelReader());
				ListStore<BeanModel> store = new ListStore<BeanModel>(loader);
			
				// Create the list-view,
				// giving it the store of beans
				// and the template from a call
				// to our private getTemplate().
				// We also configure setItemSelector()
				// and setSelectStyle() responsible for
				// how items in the list behave when
				// they are selected.
				final ListView<BeanModel> listView = new ListView<BeanModel>();
				listView.setStore(store);
				listView.setItemSelector("div.x-customer-item");
				listView.setSelectStyle("x-customer-item-sel");
				listView.setTemplate(getTemplate());
			
				// select only males or females,
				// not both! don't need it for DnD,
				// but U'll like it.
				listView.setSelectionModel(new ListViewSelectionModel<BeanModel>() {
					@Override
					protected void doSelect(List<BeanModel> models,
							boolean keepExisting, boolean supressEvent) {
						if(locked){
							return;
						}							
						
						if(selectionMode == SelectionMode.SINGLE) {
							BeanModel m = models.size() > 0 ? models.get(0) : null;
							if(m != null) {
								doSingleSelect(m, supressEvent);
							}
						} else {
							if(lastSelected != null) {																
								String selectionGrp = lastSelected.get("gender").toString();
								for(int i = (models.size() - 1); i >= 0; i--) {
									BeanModel m = models.get(i);
									String currGender = m.get("gender").toString();
									if(!selectionGrp.equalsIgnoreCase(currGender)) {
										// phew - often throws UnsupportedOperationException
										models.remove(m);
									}
								}
							}
							doMultiSelect(models, keepExisting, supressEvent);
						}
					}
				});
			
				// set some state info
				// are we dragging "males" or "females"
				ListViewDragSource listViewSrc = new ListViewDragSource(listView) {
					@Override
					protected void onDragStart(DNDEvent evt) {
						super.onDragStart(evt);
						evt.setData(listView.getSelectionModel().getSelection());
					}
				};
				
				// only components with this
				// same group can participate
				// in this DnD operation
				listViewSrc.setGroup("GenderBiased");
			
				// allow DnD re-ordering of items within the list
				ListViewDropTarget listViewTarget = new ListViewDropTarget(listView);				
				listViewTarget.setAllowSelfAsSource(true);
				listViewTarget.setOperation(Operation.MOVE);
				listViewTarget.setFeedback(Feedback.INSERT);
				
				// only components with this
				// same group can participate
				// in this DnD operation
				listViewTarget.setGroup("GenderBiased");
			
				// Display the list from within a panel
				ContentPanel ctPanel = new ContentPanel();
				ctPanel.setBodyBorder(false);
				ctPanel.setHeaderVisible(false);
				ctPanel.setButtonAlign(HorizontalAlignment.CENTER);
				ctPanel.setLayout(new FitLayout());
				ctPanel.setHeight(244);
				ctPanel.add(listView);
			
				// show it up, equivalent to
				// RootPanel.get().add(ctPanel)
				centerPanel.add(ctPanel, new ColumnData(.47));
			
				// All is now set,
				// for the ListView.
				// Go fetch the data!
				loader.load();
				
				// A second "control" tree
				// configured much the same
				// as the earlier one but
				// given a different group
				// It has a conspicous border, 
				// try dragging into it ...
				treeStore = new TreeStore<ModelData>();
				tree = new TreePanel<ModelData>(treeStore);
				tree.setBorders(true);
				tree.setDisplayProperty("name");				
			
				treeStore.add(createRoot("Male", "male"), false);
				treeStore.add(createRoot("Female", "female"), false);
				tree.setLeaf(treeStore.getRootItems().get(0), false);
				tree.setLeaf(treeStore.getRootItems().get(1), false);
				treeCt.add(tree);
				
				treeTarget = new TreePanelDropTarget(tree);
				
				// only components with this
				// same group can participate
				// in this DnD operation
				treeTarget.setGroup("ControlTree");
			}

		};
		recipes.add(dndAcrossCmp);

		Recipe dndUx = new Recipe("DnD From Desktop With HTML5") {
			
			private MediaBase handleMedia(MediaBase media, String path){
				media.setSrc(path);
				media.setControls(true);			
				// pre-load it right away
				media.load();
				return media;
			}
			
			@Override
			public void onApply() {
				// A wrapper container
				final LayoutContainer arena = new LayoutContainer(new FitLayout());
				arena.setBorders(true);
				arena.setSize(350, 250);
				
				// The panel where we'll
				// drag stuff into
				final DropPanel dropPanel = new DropPanel();
				
				// add drag-over handler and
				// prevent default behaviour
				// else browser's in-built
				// DnD handling will kick-in
				dropPanel.addDragOverHandler(new DragOverHandler() {					
					@Override
					public void onDragOver(DragOverEvent evt) {
						evt.preventDefault();
					}
				});
				
				// When items are dragged here
				// inspect their file type,
				// display images and playback
				// audio/video files
				dropPanel.addDropHandler(new DropHandler() {					
					@Override
					public void onDrop(DropEvent evt ) {
						// Hey browser we got this
						// we know what we're doing
						evt.preventDefault();
						
						// Obtain the dragged file
						// and some meta about it.
						DataTransfer dtTrnsfr = evt.getDataTransfer();					
						Iterator<File> fIterator = dtTrnsfr.getFiles().iterator();
						File file = fIterator.next();
						String fType = file.getType();
						String fPath = file.createObjectURL();
						
						if(fType.startsWith("image")){ // display images
							dropPanel.clear();
							Image img = new Image(fPath);							
							img.setWidth(String.valueOf(arena.getWidth()));
							img.setHeight(String.valueOf(arena.getHeight()));
							dropPanel.add(img);							
						} else if(fType.startsWith("audio")){ // playback for audio
							Audio audio = Audio.createIfSupported();
							if(audio == null){								
								Info.display("DnD Info", "Unsupported Operation Or Format!");
								return;
							}
							
							dropPanel.clear();
							audio = (Audio) handleMedia(audio, fPath);
							dropPanel.add(audio);
						} else if(fType.startsWith("video")){ // playback for video
							Video video = Video.createIfSupported();
							if(video == null){
								Info.display("DnD Info", "Unsupported Operation Or Format!");
								return;
							}
							
							dropPanel.clear();
							video = (Video) handleMedia(video, fPath);
							video.setSize(String.valueOf(arena.getWidth()), String.valueOf(arena.getHeight()));
							dropPanel.add(video);
						} else {
							Info.display("Hey C'mon", "Are U dragging a Mule in here? Gimme image or audio/video files");
						}
						
						// refresh the screen
						arena.layout(true);						
					}
				});	
				
				// add DnD target panel
				// to our wrapper panel
				arena.add(dropPanel);
				
				// show it up, equivalent to
				// RootPanel.get().add(arena)
				centerPanel.add(arena);
			}			
		};
		recipes.add(dndUx);
		
		Recipe dragTab = new Recipe("Custom DnD Implementation On Tabs"){
			private int index = 0;
			private TabPanel tabPanel;
			
			@Override
			public void onApply() {
				tabPanel = new TabPanel();
				tabPanel.setSize(550, 400);
				tabPanel.setCloseContextMenu(true);
			
				while(index < 5) {
					addTab();
				}
				
				// display it, equivalent to
				// RootPanel.get().add(tabPanel)
				centerPanel.add(tabPanel);
				tabPanel.setSelection( tabPanel.getItem(index-1) );
			}
			
			/*
			 * Add a tab to the TabPanel
			 */
			private void addTab() {
				// make tab,
				final TabItem item = new TabItem();
				item.setClosable(true);
				item.setText("New Tab " + ++index);				
				
				// Place a panel in
				// the Tab 
				ContentPanel textPanel = new ContentPanel();
				textPanel.setWidth(150);
				textPanel.setStyleAttribute("margin", "8px");
				textPanel.setTitle("In Tab : " + index);
				textPanel.addText("<p>Originally in Tab " + index + "</p>");							
				item.add(textPanel);
				
				// add tab to tabpanel
				tabPanel.add(item);
				
				// Configure DnD
				// support on the tab
				supportDrag(item);
				supportDrop(item);
			
			}
			
			// Swap or re-order tab with
			// DnD and allow contained
			// panel to be dragged
			private void supportDrag(final TabItem tabItem) {	
				
				// Make header item draggable
				// and set it as the data
				final HeaderItem headerItem = tabItem.getHeader();
				DragSource source = new DragSource(headerItem) {
					@Override
					protected void onDragStart(DNDEvent event) {
						event.setData(headerItem);
						event.getStatus().update(headerItem.getText());
					}
				};
				source.setGroup("DDtabs");
				

				// Make the panel inside
				// the tab draggable and
				// set it as the data
				// been dragged.
				final ContentPanel textPanel = (ContentPanel) tabItem.getItem(0);
				source = new DragSource(textPanel){
					@Override
					protected void onDragStart(DNDEvent event) {
						event.setData(textPanel);
						event.getStatus().update(textPanel.getTitle());
					}
				};
				source.setGroup("DDtabs");			
			}
			
			
			private void supportDrop(final TabItem tabItem) {

				 // When dropping stuff
				 // on a HeaderItem
				final HeaderItem headerItem = tabItem.getHeader();
				DropTarget target = new DropTarget(headerItem) {
					
					 // If what is been dragged
					 // is not a HeaderItem, then
					 // select the tab, perhaps we
					 // want to drop the item inside
					 // the tab itself!
					@Override
					protected void onDragEnter(DNDEvent evt) {
						super.onDragEnter(evt);						
						if(evt.getData() instanceof HeaderItem){
							return;
						}						
						tabPanel.setSelection(tabItem);
					}
					
					 // When a tab's header is moved
					 // to a new position, swap the tab
					 // and make it the active one
					@Override
					protected void onDragDrop(DNDEvent event) {
						super.onDragDrop(event);
			
						// Insert the dragged tab at
						// the position of the target tab
						if(event.getData() instanceof HeaderItem){
							TabItem tabOfDraggedHeader = (TabItem) ((HeaderItem) event.getData()).getParent();						
							int indexTarget = tabPanel.indexOf(tabItem);
							tabPanel.insert(tabOfDraggedHeader, indexTarget);
							tabPanel.setSelection(tabOfDraggedHeader);
						}
							
					}
				};
				target.setGroup("DDtabs");
				
				target = new DropTarget(tabItem){
					
					 // Don't allow tab headers to be
					 // dragged into tabs!.
					@Override
					protected void onDragEnter(DNDEvent evt) {
						super.onDragEnter(evt);
						if(evt.getData() instanceof HeaderItem){
							evt.setCancelled(true);							
							StatusProxy status = StatusProxy.get();
							status.setStatus(false);
							evt.setStatus(status);
						}
					};
					
					 // U are dropping a Component
					 // here, so we'll just add it.
					@Override
					protected void onDragDrop(DNDEvent evt) {
						super.onDragDrop(evt);						
						tabItem.add((Component) evt.getData());
						tabItem.layout();						
					};
				};
				target.setGroup("DDtabs");
				target.setOperation(Operation.MOVE);
				target.setFeedback(Feedback.APPEND);
			}
		};
		recipes.add(dragTab);

		return recipes;
	}

}
