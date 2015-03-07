/**
 * Date: 07/06/2007
 * File: TestBean.java
 * Package: test.spring
 */
package test.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;



/**
 * @author Oded Nissan
 *
 */
public class TestBean {

	private String name;
	private int stat;
	public String getName() {
		return name;
	}
	@Value("myname")
	public void setName(String name) {
		this.name = name;
	}
	public int getStat() {
		return stat;
	}
	@Value("10")
	public void setStat(int stat) {
		this.stat = stat;
	}
	
	public String toString()
	{
		return("");
	}
	
}
