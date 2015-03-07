package com.gxtcookbook.code.server;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class JpaController<K, E> {

    protected Class<E> entityClass;
    protected String entityClassName;
    protected EntityManager em = null;
    protected EntityManagerFactory emf = null;

    protected Logger logger;

    @SuppressWarnings("unchecked")
	public JpaController() {
        ParameterizedType genericSuperclass =  (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];
        entityClassName = this.entityClass.getName();

        emf = Persistence.createEntityManagerFactory("GxtCookBk");
        em = emf.createEntityManager();

        logger = LoggerFactory.getLogger(entityClassName);
    }

    public EntityManager getEntityManager(){
        return em;
    }

    protected void beginTransaction(){
        EntityTransaction tnx = em.getTransaction();
        if(tnx.isActive()){
            return;
        }
        tnx.begin();
    }

    public void rollBack(){
        EntityTransaction tnx = em.getTransaction();
        if(!tnx.isActive()){
            return;
        }
        tnx.rollback();
    }

    public void create(List<E> entities) throws PersistenceException, Exception{
        beginTransaction();
        for (E e : entities) {
            doCreate(e);
        }
        em.getTransaction().commit();
    }

    public void create(E entity) throws PersistenceException, Exception {
        beginTransaction();
        doCreate(entity);
        em.getTransaction().commit();
    }

    private void doCreate(E entity) throws PersistenceException, Exception {
        try {            
            em.persist(entity);
        } catch(Exception eee){
            throw new PersistenceException("Attempt to create pre-existing entity " + this.entityClassName + " Entity", eee);
        }
    }

    public void edit(List<E> entities) throws PersistenceException, Exception {
        beginTransaction();
        for(E e : entities){
            doEdit(e);
        }
        em.getTransaction().commit();
    }

    public void edit(E entity) throws PersistenceException, Exception{
        beginTransaction();
        doEdit(entity);
        em.getTransaction().commit();
    }

    private void doEdit(E entity) throws PersistenceException, Exception {
        try {
            entity = em.merge(entity);
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                throw new PersistenceException("Attempt to edit non-existing " + this.entityClassName + " Entity", ex);
            }
            throw ex;
        }
    }

    public void delete(List<K> keys) throws PersistenceException, Exception {
        beginTransaction();
        for (K k : keys) {
            doDelete(k);
        }
        em.getTransaction().commit();
    }

    public void delete(K key) throws PersistenceException, Exception{
        beginTransaction();
        doDelete(key);
        em.getTransaction().commit();
    }

    private void doDelete(K id) throws PersistenceException {
        try {
            E entity;
            try {
                entity = em.find(this.entityClass, id);
            } catch (EntityNotFoundException enfe) {
                throw new PersistenceException("Attempt to delete non-existing " + this.entityClassName + " Entity", enfe);
            }
            em.remove(entity);
        }catch(PersistenceException ex){
            throw ex;
        }
    }

    public List<E> entities() {
        return entities(true, -1, -1);
    }

    public List<E> entities(int maxResults, int firstResult) {
        return entities(false, maxResults, firstResult);
    }

    @SuppressWarnings("unchecked")
	private List<E> entities(boolean all, int maxResults, int firstResult) {
        try {
            Query q = em.createQuery("SELECT object(e) FROM " + this.entityClassName + " as e");
            beginTransaction();
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            em.getTransaction().commit();
            return q.getResultList();
        }catch(PersistenceException ex){
            throw ex;
        }
    }

    public E byId(Long id) {
        try {
            return em.find(this.entityClass, id);
        }catch(PersistenceException ex){
            throw ex;
        }
    }

    public int count() {
        try {
            beginTransaction();
            Query q = em.createQuery("SELECT count(e) from " + this.entityClassName + " as e");
            em.getTransaction().commit();
            return ((Long) q.getSingleResult()).intValue();
        }catch(PersistenceException ex){
            throw ex;
        }
    }

}
