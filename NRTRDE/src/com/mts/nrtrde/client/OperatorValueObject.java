/**
 * 
 */
package com.mts.nrtrde.client;

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * @author Oded Nissan
 *
 */
public class OperatorValueObject extends BaseModel implements Serializable {
	
	
	/**
	 * default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	
	
	public OperatorValueObject()
	{
		
	}
	
	
	public OperatorValueObject(String id, boolean commercial,String value)
	{
		set("id",id);
		set("commercial",commercial);
		set("value",value);
	}
	
	public String getId()
	{
		return(get("id"));
	}
	
	
	public String getValue()
	{
		return(get("value"));
	}

	public boolean getType()
	{
		return(get("commercial"));
	}
}
