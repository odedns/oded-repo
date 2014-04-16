package jpa.exercise;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Emp {

	@Id
	private int id;
	private String firstName;
	private String lastName;
	@ManyToOne (cascade=CascadeType.ALL)
	private Dept dep;
	
	
	
	public Emp()
	{
		
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public Dept getDep() {
		return dep;
	}



	public void setDep(Dept dep) {
		this.dep = dep;
	}
	
}
