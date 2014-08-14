/**
 * File: Dao.java
 * Date: Aug 5, 2014
 * Author: I070659
 */
package org.oded.jpa;

/**
 * @author I070659
 *
 */

public interface Dao<K, E> {

	void persist(E entity);
	void remove(E entity);
	E findById(K id);

}