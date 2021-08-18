package com.playground.demo.utils.ddd;

import org.springframework.lang.Nullable;

public interface Persistable<ID> {
    @Nullable
    ID getId();

    boolean isNew();
}