package com.playground.demo.utils;

import net.ttddyy.dsproxy.QueryCount;
import net.ttddyy.dsproxy.QueryCountHolder;

public class SQLStatementCountValidator {

    private SQLStatementCountValidator() {
    }

    public static void reset() {
        QueryCountHolder.clear();
    }

    public static void assertSelectCount(long expectedSelectCount) {
        QueryCount queryCount = QueryCountHolder.getGrandTotal();
        long recordedSelectCount = queryCount.getSelect();
        if (expectedSelectCount != recordedSelectCount) {
            throw new AssertionError("Expected Select count: " + expectedSelectCount + " but actual is: " + recordedSelectCount);
        }
    }

}
