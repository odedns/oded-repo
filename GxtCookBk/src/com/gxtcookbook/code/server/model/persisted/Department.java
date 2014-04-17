package com.gxtcookbook.code.server.model.persisted;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import net.sf.gilead.pojo.gwt.LightEntity;
import com.extjs.gxt.ui.client.data.BeanModelTag;

@Entity
@Table(name="departments")
public class Department extends LightEntity implements BeanModelTag, Serializable {

	private static final long serialVersionUID = 2747251298457352300L;

	@Id @GeneratedValue
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="code")
    private String code;
    
    @OneToMany(mappedBy = "department", targetEntity=Course.class, fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    private Set<Course> courses;    
    
    @OneToMany(mappedBy = "department", targetEntity=Student.class, fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private Set<Student> students;
    
	public Department() {
		super();
		courses = new HashSet<Course>();
		students = new HashSet<Student>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Override
	public String toString() {
		return getName();
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	
	public void addCourse(Course course){
		if(!getCourses().contains(course)){
			course.setDepartment(this);
			getCourses().add(course);
		}
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	
	public void addStudent(Student student){
		if(!getStudents().contains(student)){
			student.setDepartment(this);
			getStudents().add(student);
		}
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
		Department other = (Department) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

}
