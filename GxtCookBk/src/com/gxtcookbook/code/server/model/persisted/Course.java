package com.gxtcookbook.code.server.model.persisted;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import net.sf.gilead.pojo.gwt.LightEntity;

import com.extjs.gxt.ui.client.data.BeanModelTag;

@Entity
@Table(name="courses")
public class Course extends LightEntity implements BeanModelTag, Serializable {
	
	private static final long serialVersionUID = -1994124167361074317L;
	
	@Id @GeneratedValue
	private Long id;
	
	@Column(name="weight")
	private int weight;
	
	@Column(name="code")
	private String code;
	
	@Column(name="title")
	private String title;
    
    @ManyToOne
    @JoinColumn(name="department")
    private Department department;	
    
    @OneToMany(mappedBy = "course", targetEntity=CourseOfStudy.class, fetch=FetchType.EAGER)
    private Set<CourseOfStudy> courseOfStudy;
	
	public Course() {
		super();
		courseOfStudy = new HashSet<CourseOfStudy>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}	
	
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<CourseOfStudy> getCourseOfStudy() {
		return courseOfStudy;
	}

	public void setCourseOfStudy(Set<CourseOfStudy> courseOfStudy) {
		this.courseOfStudy = courseOfStudy;
	}

	@Override
	public String toString() {
		return getTitle();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
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
		Course other = (Course) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}		

}
