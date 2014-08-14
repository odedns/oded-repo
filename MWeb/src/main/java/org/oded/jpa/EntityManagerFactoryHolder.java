/**
 * File: EntityManagerFactoryHolder.java
 * Date: Aug 5, 2014
 * Author: I070659
 */
package org.oded.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Oded Nissan
 *
 */
public class EntityManagerFactoryHolder {
	
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("MWeb");
	
	
	public static EntityManagerFactory getEntityManagerFactory()
	{
		return(factory);
	}
	

}
