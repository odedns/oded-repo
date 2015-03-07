/*
 * Ext GWT 2.2.3 - Ext for GWT
 * Copyright(c) 2007-2010, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package com.gxtcookbook.code.client;

import java.util.List;

import com.extjs.gxt.ui.client.data.RemoteSortTreeLoadConfig;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Async <code>FileService<code> interface.
 */
public interface FileServiceAsync {

	public void getAll(FileModel model, AsyncCallback<List<FileModel>> children);

	public void getFolderChildren(FileModel model,
			AsyncCallback<List<FileModel>> children);

	public void getFolderChildren(RemoteSortTreeLoadConfig loadConfig,
			AsyncCallback<List<FileModel>> children);

}
