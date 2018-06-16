package com.embibe.iibnanded.network.utils;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by IoT-Engg team on 11/06/18.
 */
public class RequestTypes {
    public static final int NONE = 0;
    public static final int LOGIN = 1;
    public static final int GET_DASHBOARD_INFO = 2;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({NONE,
            LOGIN,
            GET_DASHBOARD_INFO
    }
    )

    public @interface Interface {
    }
}
