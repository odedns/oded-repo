package com.gxtcookbook.code.server.model;

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.BeanModelTag;

@SuppressWarnings("serial")
public class Review implements BeanModelTag, Serializable {
	
	private int id;
	private String title;
	private String body;
	
	private Customer reviewer;
	
	public Review() {}
	
	public Review(String title, String body){
		this(-1, title, body);
	}
	
	public Review(int id, String title, String body){
		this();
		
		if(id <= 0){
			setId(++this.id);
		}else{
			setId(id);
		}
		
		setTitle(title);
		setBody(body);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String name) {
		this.title = name;
	}
	
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return getTitle();
	}

	public Customer getReviewer() {
		return reviewer;
	}

	public void setReviewer(Customer reviewer) {
		this.reviewer = reviewer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		if (id != other.id)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}
