/**
 * File: Departments.java
 * Date: May 15, 2014
 * Author: I070659
 */
package test.spring.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author I070659
 *
 */
@Entity
public class Departments {
	@Id @Column(name="dept_no")
	private String deptNo;
	@Column(name="dept_name")
	private String deptName;
	
	
	public Departments()
	{
	
	}
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
	public String toString()
	{
		return new String(deptNo + "-" + deptName);
	}
	

}
