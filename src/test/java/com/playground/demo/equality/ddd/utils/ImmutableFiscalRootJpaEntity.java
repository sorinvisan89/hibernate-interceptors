package com.playground.demo.equality.ddd.utils;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
public class ImmutableFiscalRootJpaEntity extends ImmutableRootJpaEntity<UUID> {

    @Id
    @Column(name = "ID")
    private UUID id;

    protected ImmutableFiscalRootJpaEntity() {
    }

    protected ImmutableFiscalRootJpaEntity(UUID id) {
        this.id = id;
    }

    @Override
    public UUID getId() {
        return id;
    }
}
