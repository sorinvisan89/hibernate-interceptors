package com.playground.demo.equality.ddd.utils;

import org.springframework.data.domain.Persistable;

import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Transient;

@MappedSuperclass
public abstract class RootJpaEntity<I> implements Persistable<I> {
    @Transient
    private boolean isNew = true;

    public RootJpaEntity() {
    }

    public boolean isNew() {
        return this.isNew;
    }

    @PrePersist
    @PostLoad
    protected void markNotNew() {
        this.isNew = false;
    }

    public boolean equals(Object o) {


        if (this.getId() == null) {
            throw new RootEntityIsMissingRequiredIdException();
        } else if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
//        } else if (o instanceof RootJpaEntity) {
            RootJpaEntity<I> entity = (RootJpaEntity)o;
            return this.getId().equals(entity.getId());
        } else {
            return false;
        }
    }

    public int hashCode() {
        if (this.getId() == null) {
            throw new RootEntityIsMissingRequiredIdException();
        } else {
            return this.getId().hashCode();
        }
    }
}
