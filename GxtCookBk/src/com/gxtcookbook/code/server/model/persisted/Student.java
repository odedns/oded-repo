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
@Table(name="students")
public class Student extends LightEntity implements BeanModelTag, Serializable {

	private static final long serialVersionUID = -2480430273462779420L;
	
	@Id @GeneratedValue
	private Long id;
	
	@Column(name="address")
	private String address;
	
	@Column(name="email")
	private String emailId;
	
	@Column(name="lname")
	private String lastName;
	
	@Column(name="fname")
	private String firstName;	
    
    @ManyToOne
    @JoinColumn(name="department")
    private Department department;	
    
    @OneToMany(mappedBy = "student", targetEntity=CourseOfStudy.class, fetch=FetchType.EAGER)
    private Set<CourseOfStudy> courseOfStudy;
	
	public Student() {
		super();
		courseOfStudy = new HashSet<CourseOfStudy>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
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
		return "Student [" + lastName + " " + firstName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
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
		Student other = (Student) obj;
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
			return false;
		return true;
	}
}
