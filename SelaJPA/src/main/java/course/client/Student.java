/**
 * 
 */
package course.client;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Oded Nissan
 *
 */
@Entity
public class Student {
	@Id
	private int id;
	String name;
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

}
