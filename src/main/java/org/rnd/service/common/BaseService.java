package org.rnd.service.common;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class BaseService {

    @Inject
    EntityManager em;

    /**
     * 
     * @param object
     * @return
     */
    @Transactional
    public Object updateEntity(Object entity) {
        return em.merge(entity);
    }

    /**
     * 
     * @param entity
     */
    public void detachEntity(Object entity) {
        em.detach(entity);
    }

    /**
     * 
     * @param entity
     */
    public void deleteEntity(Object entity) {
        em.remove(entity);
    }

    /**
     * @param clazz
     * @param id
     * @return
     */
    public Object findEntity(Class<?> clazz, Object id) {
        return em.find(clazz, id);
    }

    /**
     * 
     * @param entity
     * @return
     */
    @Transactional
    public Object saveEntity(Object entity) {
        em.persist(entity);
        return entity;
    }

    @Transactional
    public Integer executeQuery(String sql) {
        Query query = em.createNativeQuery(sql);
        return query.executeUpdate();
    }

}
