/**
 * File: Test.java
 * Date: Aug 4, 2014
 * Author: I070659
 */
package org.oded;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.oded.jpa.*;

/**
 * @author I070659
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Hello Test");
		// TODO Auto-generated method stub
				EntityManagerFactory emf = Persistence.createEntityManagerFactory("MWeb");
				
				EntityManager em = emf.createEntityManager();
				Departments dept = em.find(Departments.class, "d009");
				System.out.println("dept = " + dept.getDeptName());
				em.close();
		
				emf.close();
		
	}

}
