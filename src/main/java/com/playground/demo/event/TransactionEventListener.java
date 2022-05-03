package com.playground.demo.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@Slf4j
public class TransactionEventListener {

    @TransactionalEventListener
    public void handleTransactionCommitted(final CustomEvent event) {
        log.info("Received a custom event={}", event);
    }
}
