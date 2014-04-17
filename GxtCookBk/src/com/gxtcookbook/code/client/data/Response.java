package com.gxtcookbook.code.client.data;

import java.util.LinkedHashSet;
import java.util.Set;

import com.extjs.gxt.ui.client.data.BaseModelData;

@SuppressWarnings("serial")
public class Response extends BaseModelData {
	private String status;
    private Set<String> messages;

    private static Response instance;

    public static final String STATUS_OK = "ok";
    public static final String STATUS_ERR = "err";
    public static final String STATUS_NOTICE = "notice";

    protected Response(){
        messages = new LinkedHashSet<String>();
    }

    public static Response get(){
        instance = (instance == null ? new Response() : instance);
        return instance;
    }

    public Response OK(){
        return this.OK("Ok");
    }
    
    public Response clr(){
    	messages.clear();
    	return this.OK();
    }

    public Response OK(String msg){
        status = STATUS_OK;
        messages.add(msg);
        return this;
    }

    public Response ERR(String msg){
        messages.add(msg);
        status = STATUS_ERR;
        return this;
    }

    public Response NOTICE(String msg){
        messages.add(msg);
        status = STATUS_NOTICE;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Set<String> getMessages() {
        return messages;
    }
}
