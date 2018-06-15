package com.embibe.iibnanded.util

import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar


class Utilities {
    companion object {

        fun showSnackBar(coordinatorLayout: CoordinatorLayout, msg: String) {
            val snackbar = Snackbar
                    .make(coordinatorLayout, msg, Snackbar.LENGTH_LONG)
            snackbar.show()
        }
    }
}