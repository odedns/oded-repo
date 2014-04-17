package com.gxtcookbook.code.server.model.persisted.mvp;

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.BeanModelTag;

public interface ModelType extends BeanModelTag, Serializable {
	public Long getId();
    public void setId(Long id);
}
