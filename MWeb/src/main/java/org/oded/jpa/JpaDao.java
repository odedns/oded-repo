/**
 * File: JpaDao.java
 * Date: Aug 5, 2014
 * Author: I070659
 */
package org.oded.jpa;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;

/**
 * @author Oded Nissan
 *
 */

public abstract class JpaDao<K, E> implements Dao<K, E> {

	protected Class<E> entityClass;
	protected EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public JpaDao() {
			entityManager = EntityManagerFactoryHolder.getEntityManagerFactory().createEntityManager();
			ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
			this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];
	}
	public void persist(E entity) { 
		entityManager.persist(entity); 
	}
	public void remove(E entity) { 
		entityManager.remove(entity); 
	}
	public E findById(K id) { 
		return entityManager.find(entityClass, id); 
	}
	
	public void close()
	{
		this.entityManager.close();
	}
	
	public void begin()
	{
		entityManager.getTransaction().begin();
	}
	
	
	public void commit()
	{
		entityManager.getTransaction().commit();
	}
	
	
	public void rollback()
	{
		entityManager.getTransaction().rollback();
	}
}

