package com.playground.demo.equality.ddd.utils;

import org.springframework.lang.Nullable;

public interface Persistable<ID> {
    @Nullable
    ID getId();

    boolean isNew();
}