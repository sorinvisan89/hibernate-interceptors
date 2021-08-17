package com.playground.demo.utils;

import net.ttddyy.dsproxy.QueryCount;
import net.ttddyy.dsproxy.QueryCountHolder;

public class SQLStatementCounter {

    public static void reset() {
        QueryCountHolder.clear();
    }

    public static void assertSelectCount(long expectedSelectCount) {
        QueryCount queryCount = QueryCountHolder.getGrandTotal();
        long actualSelectCount = queryCount.getSelect();
        if (expectedSelectCount != actualSelectCount) {
            throw new SqlCountMismatchException(expectedSelectCount, actualSelectCount);
        }
    }
}
