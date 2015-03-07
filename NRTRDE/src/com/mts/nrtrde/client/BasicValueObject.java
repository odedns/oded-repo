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
public class BasicValueObject extends BaseModel implements Serializable {
	
	
	/**
	 * default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	
	
	public BasicValueObject()
	{
		
	}
	
	
	public BasicValueObject(String value)
	{
		set("value",value);
	}
	
	public BasicValueObject(int id, String value)
	{
		set("id",id);
		set("value",value);
	}
	
	public BasicValueObject(int id, int type,String value)
	{
		set("id",id);
		set("type",type);
		set("value",value);
	}
	
	public int getId()
	{
		return(get("id"));
	}
	
	
	public String getValue()
	{
		return(get("value"));
	}

	public int getType()
	{
		return(get("type"));
	}
}
