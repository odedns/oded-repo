package com.gxtcookbook.code.server.model.persisted;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.sf.gilead.pojo.gwt.LightEntity;

import com.extjs.gxt.ui.client.data.BeanModelTag;

@Entity
@Table(name="courseofstudy")
public class CourseOfStudy extends LightEntity implements BeanModelTag, Serializable {

	private static final long serialVersionUID = -6715247221908981060L;

	@Id @GeneratedValue
	private Long id;
	
	@ManyToOne
    @JoinColumn(name="course")
	private Course course;
	
	@ManyToOne
    @JoinColumn(name="student")
	private Student student;
	
	public CourseOfStudy() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((course == null) ? 0 : course.hashCode());
		result = prime * result + ((student == null) ? 0 : student.hashCode());
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
		CourseOfStudy other = (CourseOfStudy) obj;
		if (course == null) {
			if (other.course != null)
				return false;
		} else if (!course.equals(other.course))
			return false;
		if (student == null) {
			if (other.student != null)
				return false;
		} else if (!student.equals(other.student))
			return false;
		return true;
	}

}
