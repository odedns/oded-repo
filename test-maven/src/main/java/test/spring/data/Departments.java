/**
 * File: Departments.java
 * Date: May 15, 2014
 * Author: I070659
 */
package test.spring.data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author I070659
 *
 */
@Entity
public class Departments {
	@Id
	private String deptNo;
	private String deptName;
	
	public Departments(String deptNo, String deptName){
		this.deptName = deptName;
		this.deptNo = deptNo;
	}

	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	

}
