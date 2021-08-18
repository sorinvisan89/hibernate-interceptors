package com.playground.demo.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.EmptyInterceptor;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * This interceptor is thread safe since it will be constructed per session, not per session factory.
 */
@Slf4j
public class MyInterceptor extends EmptyInterceptor {

    public MyInterceptor() {
        log.info("MyInterceptor started session : " + Thread.currentThread().getName());
    }

    /**
     * Called before an object is saved.
     *
     * @param entity        The entity instance whose state is beign inserted.
     * @param id            The id of the entity.
     * @param state         The state of the entity which will be inserted.
     * @param propertyNames The names of the entity property.
     * @param types         The types of the entity properties
     * @return true if the interceptor modified the state in any way.
     */
    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        if (entity instanceof Auditable) {
            final Auditable auditable = (Auditable) entity;
            log.info("onSave: " + auditable.getEntityName() + " " +  auditable.getEntityId());
        }
        return super.onSave(entity, id, state, propertyNames, types);
    }


    /**
     * @param entity        The entity instance detected as being dirty and beign flushed.
     * @param id            The id of the entity
     * @param currentState  The current state
     * @param previousState The entity state at load time into the in-memory state.
     * @param propertyNames The  entity property names
     * @param types         The entity property types
     * @return True if the interceptor modified the state in any way.
     */
    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        if (entity instanceof Auditable) {
            final Auditable auditable = (Auditable) entity;
            log.info("onFlushDirty: " + auditable.getEntityName(), auditable.getEntityId());
            log.info("onFlushDirty: current= " + Arrays.toString(currentState) + " vs previous= " + Arrays.toString(previousState));
        }
        return super.onFlushDirty(entity, id, currentState, previousState, propertyNames, types);
    }

    /**
     * Called before an object is deleted.
     *
     * @param entity        The entity instance beign deleted.
     * @param id            The id of the entity.
     * @param state         The state of the entity.
     * @param propertyNames The names of the entity properties
     * @param types         The types of the entity properties
     */
    @Override
    public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        if (entity instanceof Auditable) {
            final Auditable auditable = (Auditable) entity;
            log.info("onFlushDirty: " + auditable.getEntityName() + " " + auditable.getEntityId());
        }
        super.onDelete(entity, id, state, propertyNames, types);
    }

    /**
     * Called after a flush that actually ends in an execution of SQL which will synchronize the in-memory state with the DB.
     *
     * @param entities The entities that were previously flushed
     */
    @Override
    public void postFlush(final Iterator entities) {

        List<Auditable> auditableList = new ArrayList<>();

        while (entities.hasNext()) {
            final Object entity = entities.next();
            if (entity instanceof Auditable) {
                auditableList.add((Auditable) entity);
            }
        }

        auditableList.forEach(auditable -> log.info("postFlush: " + auditable.getEntityName() + " " + auditable.getEntityId()));

        super.postFlush(entities);
    }

    /**
     * Called before a Hibernate Transaction begins.
     *
     * @param tx The current transaction that begins
     */
    @Override
    public void afterTransactionBegin(Transaction tx) {
        log.info("afterTransactionBegin");
        super.afterTransactionBegin(tx);
    }

    /**
     * Called before a transaction is committed.
     * This is not before rollback, the transaction can be rolled back.
     *
     * @param tx The current transaction
     */
    @Override
    public void beforeTransactionCompletion(Transaction tx) {
        log.info("beforeTransactionCompletion");
        super.beforeTransactionCompletion(tx);
    }
}