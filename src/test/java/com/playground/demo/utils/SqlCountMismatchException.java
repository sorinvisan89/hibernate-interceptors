package com.playground.demo.utils;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SqlCountMismatchException extends AssertionError {

    private final long expected;
    private final long recorded;

    public SqlCountMismatchException(long expected, long recorded) {
        super("Expected " + expected + " statements but recorded " + recorded + " instead!");
        this.expected = expected;
        this.recorded = recorded;
    }

}
