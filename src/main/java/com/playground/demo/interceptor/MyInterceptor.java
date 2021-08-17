package com.playground.demo.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.EmptyInterceptor;
import org.hibernate.Transaction;

@Slf4j
public class MyInterceptor extends EmptyInterceptor {

    public MyInterceptor() {
        log.info("MyInterceptor started session : " + Thread.currentThread().getName());
    }

    @Override
    public void afterTransactionCompletion(Transaction tx) {

    }
}