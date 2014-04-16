/**
 * 
 */
package jpa.exercise;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * @author ...
 *
 */
@Entity

public class Dept {
	@Id
	private int id;
	private String name;
	@OneToMany(mappedBy="dep")
	private List<Emp> emps = new LinkedList<Emp>();
	
	
	public Dept()
	{
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<Emp> getEmps() {
		return emps;
	}


	public void setEmps(List<Emp> emps) {
		this.emps = emps;
	}

}
