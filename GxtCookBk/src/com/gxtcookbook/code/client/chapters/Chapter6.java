package com.gxtcookbook.code.client.chapters;

import java.util.LinkedList;
import java.util.List;



import com.extjs.gxt.ui.client.data.BaseTreeLoader;
import com.extjs.gxt.ui.client.data.ModelIconProvider;
import com.extjs.gxt.ui.client.data.ModelStringProvider;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.data.TreeLoader;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.store.StoreSorter;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ButtonBar;
import com.extjs.gxt.ui.client.widget.layout.FlowData;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.menu.SeparatorMenuItem;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel.CheckCascade;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel.CheckNodes;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.gxtcookbook.code.client.FileModel;
import com.gxtcookbook.code.client.FileService;
import com.gxtcookbook.code.client.FileServiceAsync;
import com.gxtcookbook.code.client.FolderModel;
import com.gxtcookbook.code.client.GxtCookBk;
import com.gxtcookbook.code.client.icons.Icons;

public class Chapter6 extends ChapterAdapter {

	private static Chapter6 instance;

	public static Chapter6 get() {
		instance = (instance == null ? new Chapter6() : instance);
		return instance;
	}

	protected Chapter6() {
		super();
	}

	@Override
	protected String getTitle() {
		return "Data Hierarchy With Trees";
	}

	@Override
	protected List<Recipe> applyTheseRecipes() {
		List<Recipe> recipes = new LinkedList<Recipe>();

		Recipe basicTree = new Recipe("Building A Basic Tree") {
			@Override
			public void onApply() {
				// Just for fun
				final ButtonBar btnBar = new ButtonBar();
				btnBar.disable();
				GxtCookBk.getAppCenterPanel().add(btnBar, new FlowData(5));

				// setup the store and tree
				final TreeStore<FileModel> store = new TreeStore<FileModel>();

				final TreePanel<FileModel> tree = new TreePanel<FileModel>(
						store);
				tree.setSize(265, 330);
				tree.setBorders(true);
				tree.setDisplayProperty("name");

				// set leaf icon
				Icons ICONS = GWT.create(Icons.class);
				tree.getStyle().setLeafIcon(AbstractImagePrototype.create(ICONS.tick()));

				// complete the fun buttons
				Button expandBtn = new Button("Expand All",
						new SelectionListener<ButtonEvent>() {
							@Override
							public void componentSelected(ButtonEvent ce) {
								tree.expandAll();
							}
						});
				btnBar.add(expandBtn);

				Button collapseBtn = new Button("Collapse All",
						new SelectionListener<ButtonEvent>() {
							@Override
							public void componentSelected(ButtonEvent ce) {
								tree.collapseAll();
							}
						});
				btnBar.add(collapseBtn);

				// Populate tree from RPC call
				final FileServiceAsync fileService = (FileServiceAsync) GWT
						.create(FileService.class);
				AsyncCallback<List<FileModel>> callback = new AsyncCallback<List<FileModel>>() {

					@Override
					public void onSuccess(final List<FileModel> result) {
						Scheduler.get().scheduleDeferred(
								new ScheduledCommand() {
									@Override
									public void execute() {
										store.add(result, true);
										tree.unmask();
										btnBar.enable();
									}
								});
					}

					@Override
					public void onFailure(Throwable caught) {
						Info.display("Error", "Cannot Fetch Data for Tree!");
					}
				};

				tree.mask("Busy ...");
				fileService.getAll(null, callback);
				/*
				 * GxtCookbk is the application's entry point class.
				 * We access its main content panel using the 
				 * static GxtCookBk.getAppCenterPanel() call.
				 * We add our tree to the main content panel. 
				 */
				GxtCookBk.getAppCenterPanel().add(tree);
			}
		};
		recipes.add(basicTree);

		Recipe customLabels = new Recipe("Custom Node Labels") {

			@Override
			public void onApply() {
				// setup the store and tree
				final TreeStore<FileModel> store = new TreeStore<FileModel>();

				final TreePanel<FileModel> tree = new TreePanel<FileModel>(
						store);
				tree.setSize(575, 250);
				tree.setBorders(true);
				tree.setLabelProvider(new ModelStringProvider<FileModel>() {

					@Override
					public String getStringValue(FileModel model,
							String property) {
						String label = model.getName().toUpperCase();
						if (model.isLeaf()) {
							label = model.getPath();
						}
						return label;
					}
				});

				// Populate tree from RPC call
				final FileServiceAsync fileService = (FileServiceAsync) GWT
						.create(FileService.class);
				AsyncCallback<List<FileModel>> callback = new AsyncCallback<List<FileModel>>() {

					@Override
					public void onSuccess(final List<FileModel> result) {
						Scheduler.get().scheduleDeferred(
								new ScheduledCommand() {
									@Override
									public void execute() {
										store.add(result, true);
										tree.unmask();
									}
								});
					}

					@Override
					public void onFailure(Throwable caught) {
						Info.display("Error", "Cannot Fetch Data for Tree!");
					}
				};

				tree.mask("Busy ...");
				fileService.getAll(null, callback);
				/*
				 * GxtCookbk is the application's entry point class.
				 * We access its main content panel using the 
				 * static GxtCookBk.getAppCenterPanel() call.
				 * We add our tree to the main content panel. 
				 */
				GxtCookBk.getAppCenterPanel().add(tree);
			}
		};
		recipes.add(customLabels);

		Recipe customIconTree = new Recipe("Decorating Trees With Icons") {
			@Override
			public void onApply() {
				// setup the store and tree
				final TreeStore<FileModel> store = new TreeStore<FileModel>();

				final TreePanel<FileModel> tree = new TreePanel<FileModel>(
						store);
				tree.setSize(285, 450);
				tree.setBorders(true);
				tree.setDisplayProperty("name");

				// set icons
				final Icons ICONS = GWT.create(Icons.class);
				tree.setIconProvider(new ModelIconProvider<FileModel>() {

					@Override
					public AbstractImagePrototype getIcon(FileModel model) {
						if (model.isLeaf()) {
							String fileName = model.getName();
							// get the file extension.
							String ext = fileName.substring(fileName
									.lastIndexOf(".") + 1);
							// set the icon type according to the 
							// file extension.
							if ("class".equals(ext)) {
								return AbstractImagePrototype.create(ICONS.jar());
							} else if ("js".equals(ext)) {
								return AbstractImagePrototype.create(ICONS.json());
							} else if ("css".equals(ext)) {
								return AbstractImagePrototype.create(ICONS.css());
							} else if ("gif".equals(ext) || "png".equals(ext)
									|| "jpg".equals(ext) || "jpeg".equals(ext)) {
								return AbstractImagePrototype.create(ICONS.image());
							}
						}
						return null;
					}
				});

				// Make RPC call
				final FileServiceAsync fileService = (FileServiceAsync) GWT
						.create(FileService.class);
				AsyncCallback<List<FileModel>> callback = new AsyncCallback<List<FileModel>>() {

					@Override
					public void onSuccess(List<FileModel> result) {
						store.add(result, true);
						tree.unmask();
					}

					@Override
					public void onFailure(Throwable caught) {
						Info.display("Error", "Cannot Fetch Data for Tree!");
					}
				};

				tree.mask("Busy ...");
				fileService.getAll(null, callback);
				/*
				 * GxtCookbk is the application's entry point class.
				 * We access its main content panel using the 
				 * static GxtCookBk.getAppCenterPanel() call.
				 * We add our tree to the main content panel. 
				 */
				GxtCookBk.getAppCenterPanel().add(tree);
			}
		};
		recipes.add(customIconTree);

		Recipe treeCtxMenu = new Recipe("Augmenting Trees With ContextMenu") {

			@Override
			public void onApply() {
				// setup the store and tree
				final TreeStore<FileModel> store = new TreeStore<FileModel>();

				// setup tree
				final TreePanel<FileModel> tree = new TreePanel<FileModel>(
						store);
				tree.setSize(285, 450);
				tree.setBorders(true);
				tree.setDisplayProperty("name");

				// setup context menu
				Menu ctxMenu = new Menu();
				tree.setContextMenu(ctxMenu);

				MenuItem info = new MenuItem("Info",
						new SelectionListener<MenuEvent>() {
							@Override
							public void componentSelected(MenuEvent evt) {
								FileModel node = tree.getSelectionModel()
										.getSelectedItem();
								if (node.isLeaf()) {
									Info.display("Tree Node Info",
											"File is at " + node.getPath());
								} else {
									Info.display("Tree Node Info", "Node has "
											+ node.getChildCount()
											+ " children");
								}
							}
						});
				ctxMenu.add(info);
				ctxMenu.add(new SeparatorMenuItem());

				// items in the menu
				final MenuItem expand = new MenuItem("Expand",
						new SelectionListener<MenuEvent>() {
							@Override
							public void componentSelected(MenuEvent evt) {
								FileModel node = tree.getSelectionModel()
										.getSelectedItem();
								if (!node.isLeaf()) {
									tree.setExpanded(node, true, true);
								}
							}
						});
				ctxMenu.add(expand);

				final MenuItem collapse = new MenuItem("Collapse",
						new SelectionListener<MenuEvent>() {
							@Override
							public void componentSelected(MenuEvent evt) {
								FileModel node = tree.getSelectionModel()
										.getSelectedItem();
								if (!node.isLeaf()) {
									tree.setExpanded(node, false, true);
								}
							}
						});
				ctxMenu.add(collapse);

				// let's be smart with the menu
				ctxMenu.addListener(Events.BeforeShow,
						new Listener<MenuEvent>() {
							@Override
							public void handleEvent(MenuEvent evt) {
								FileModel node = tree.getSelectionModel()
										.getSelectedItem();
								expand.setEnabled(!node.isLeaf());
								collapse.setEnabled(!node.isLeaf());
							}
						});

				// Populate tree from RPC call
				final FileServiceAsync fileService = (FileServiceAsync) GWT
						.create(FileService.class);
				AsyncCallback<List<FileModel>> callback = new AsyncCallback<List<FileModel>>() {

					@Override
					public void onSuccess(final List<FileModel> result) {
						Scheduler.get().scheduleDeferred(
								new ScheduledCommand() {
									@Override
									public void execute() {
										store.add(result, true);
										tree.unmask();
									}
								});
					}

					@Override
					public void onFailure(Throwable caught) {
						Info.display("Error", "Cannot Fetch Data for Tree!");
					}
				};

				tree.mask("Busy ...");
				fileService.getAll(null, callback);
				/*
				 * GxtCookbk is the application's entry point class.
				 * We access its main content panel using the 
				 * static GxtCookBk.getAppCenterPanel() call.
				 * We add our tree to the main content panel. 
				 */
				GxtCookBk.getAppCenterPanel().add(tree);
			}
		};
		recipes.add(treeCtxMenu);

		Recipe checkBxTree = new Recipe("Tree With CheckBox Selection") {
			@Override
			public void onApply() {
				// setup the store and tree
				final TreeStore<FileModel> store = new TreeStore<FileModel>();

				final TreePanel<FileModel> tree = new TreePanel<FileModel>(
						store);
				tree.setSize(285, 450);
				tree.setBorders(true);
				tree.setDisplayProperty("name");

				// enable checkbox selection
				tree.setCheckable(true);
				tree.setCheckNodes(CheckNodes.BOTH);
				tree.setCheckStyle(CheckCascade.CHILDREN);

				// Populate tree from RPC call
				final FileServiceAsync fileService = (FileServiceAsync) GWT
						.create(FileService.class);
				AsyncCallback<List<FileModel>> callback = new AsyncCallback<List<FileModel>>() {

					@Override
					public void onSuccess(final List<FileModel> result) {
						Scheduler.get().scheduleDeferred(
								new ScheduledCommand() {
									@Override
									public void execute() {
										store.add(result, true);
										tree.unmask();
									}
								});
					}

					@Override
					public void onFailure(Throwable caught) {
						Info.display("Error", "Cannot Fetch Data for Tree!");
					}
				};

				tree.mask("Busy ...");
				fileService.getAll(null, callback);
			
				/*
				 * GxtCookbk is the application's entry point class.
				 * We access its main content panel using the 
				 * static GxtCookBk.getAppCenterPanel() call.
				 * We add our tree to the main content panel. 
				 */
				GxtCookBk.getAppCenterPanel().add(tree);
			}
		};
		recipes.add(checkBxTree);

		Recipe asyncTree = new Recipe("Building Asynchronous Trees") {
			@Override
			public void onApply() {
				// Make RPC call vai a proxy
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
				TreeStore<FileModel> store = new TreeStore<FileModel>(loader);
				
				final TreePanel<FileModel> tree = new TreePanel<FileModel>(
						store);
				tree.setSize(300, 255);
				tree.setBorders(true);
				tree.setDisplayProperty("name");
				
				// don't use this line
				// it will automatically load all child nodes
				// tree.setAutoLoad(true);
				/*
				 * GxtCookbk is the application's entry point class.
				 * We access its main content panel using the 
				 * static GxtCookBk.getAppCenterPanel() call.
				 * We add our tree to the main content panel. 
				 */
				GxtCookBk.getAppCenterPanel().add(tree);
			}
		};
		recipes.add(asyncTree);

		Recipe treeSorter = new Recipe("Custom Sorting Within Trees") {
			@Override
			public void onApply() {
				// setup the store and tree
				final TreeStore<FileModel> store = new TreeStore<FileModel>();
				
				// give store a sorter
				store.setStoreSorter(new StoreSorter<FileModel>() {
					@Override
					public int compare(Store<FileModel> store, FileModel m1,
							FileModel m2, String property) {
						boolean m1Folder = m1 instanceof FolderModel;
						boolean m2Folder = m2 instanceof FolderModel;
				
						if (m1Folder && !m2Folder) {
							return -1;
						} else if (!m1Folder && m2Folder) {
							return 1;
						}
				
						return m1.getName().compareTo(m2.getName());
					}
				});
				
				final TreePanel<FileModel> tree = new TreePanel<FileModel>(
						store);
				tree.setSize(285, 450);
				tree.setBorders(true);
				tree.setDisplayProperty("name");
				
				// Populate tree from RPC call
				final FileServiceAsync fileService = (FileServiceAsync) GWT
						.create(FileService.class);
				AsyncCallback<List<FileModel>> callback = new AsyncCallback<List<FileModel>>() {
				
					@Override
					public void onSuccess(final List<FileModel> result) {
						Scheduler.get().scheduleDeferred(
								new ScheduledCommand() {
									@Override
									public void execute() {
										store.add(result, true);
										tree.unmask();
									}
								});
					}
				
					@Override
					public void onFailure(Throwable caught) {
						Info.display("Error", "Cannot Fetch Data for Tree!");
					}
				};
				
				tree.mask("Busy ...");
				fileService.getAll(null, callback);
				/*
				 * GxtCookbk is the application's entry point class.
				 * We access its main content panel using the 
				 * static GxtCookBk.getAppCenterPanel() call.
				 * We add our tree to the main content panel. 
				 */
				GxtCookBk.getAppCenterPanel().add(tree);
			}
		};
		recipes.add(treeSorter);

		return recipes;
	}

}
