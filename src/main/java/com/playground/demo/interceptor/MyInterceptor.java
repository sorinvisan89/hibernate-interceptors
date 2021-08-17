package com.playground.demo.interceptor;

import com.playground.demo.entity.Audit;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.EmptyInterceptor;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
public class MyInterceptor extends EmptyInterceptor {

    private final List<Audit> auditUpdateDeleteEntries = new ArrayList<>();
    private final List<Audit> auditInsertEntries = new ArrayList<>();

    public MyInterceptor() {
        log.info("MyInterceptor started session : " + Thread.currentThread().getName());
    }

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        if (entity instanceof Auditable) {

        }
        return super.onSave(entity, id, state, propertyNames, types);
    }


    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        if (entity instanceof Auditable) {

        }
        return super.onFlushDirty(entity, id, currentState, previousState, propertyNames, types);
    }

    @Override
    public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        if (entity instanceof Auditable) {

        }
        super.onDelete(entity, id, state, propertyNames, types);
    }

    @Override
    public void postFlush(final Iterator entities){
        super.postFlush(entities);
    }

    @Override
    public void afterTransactionBegin(Transaction tx) {
        auditInsertEntries.clear();
        auditUpdateDeleteEntries.clear();

        super.afterTransactionBegin(tx);
    }

    @Override
    public void beforeTransactionCompletion(Transaction tx) {
        super.beforeTransactionCompletion(tx);
    }
}