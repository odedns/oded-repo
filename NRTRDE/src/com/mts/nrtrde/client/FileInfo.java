/**
 * 
 */
package com.mts.nrtrde.client;

import java.io.Serializable;
import java.util.Date;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * @author Oded Nissan
 *
 */
public class FileInfo extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public FileInfo()
	{
		
	}
	
	
	public FileInfo(String name, String type, Date date)
	{
	
		set("fileName",name);
		set("type",type);
		set("dateModified",date);
	}
	
	public String getFileName() {
		return get("fileName");
	}
	public void setFileName(String fileName) {
		set("fileName",fileName);
	}
	public String getType() {
		return get("type");
	}
	public void setType(String type) {
		set("type",type);
	}
	public String getDateModified() {
		return get("dateModified").toString();
	}
	public void setDateModified(Date dateModified) {
		set("dateModified",dateModified);
	}

}
