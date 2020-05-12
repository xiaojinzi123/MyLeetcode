package com.xiaojinzi.support;

import com.xiaojinzi.support.annotation.Nullable;

public class Utils {

    public static void checkStringEmpty(@Nullable String value) {
        if (value == null || value.isEmpty()) {
            throw new NullPointerException("String value is empty");
        }
    }

}
