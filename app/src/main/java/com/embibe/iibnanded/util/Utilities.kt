package com.embibe.iibnanded.util

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import com.embibe.iibnanded.R
import com.embibe.iibnanded.util.Constants.Companion.AMBER
import com.embibe.iibnanded.util.Constants.Companion.PURPLE
import com.embibe.iibnanded.util.Constants.Companion.THEME
import com.embibe.iibnanded.util.Constants.Companion.VOILET


class Utilities : Constants {

    companion object {

        fun showSnackBar(coordinatorLayout: CoordinatorLayout, msg: String) {
            val snackbar = Snackbar
                    .make(coordinatorLayout, msg, Snackbar.LENGTH_LONG)
            snackbar.show()
        }


        fun setTheme(activity: Context, Theme: Int) {

            val mAppPrefs = AppPreferenceManager().getSingleInstance(activity)

            when (Theme) {
                1 -> mAppPrefs.getAppPrefs().edit().putInt(THEME, VOILET).apply()
                2 -> mAppPrefs.getAppPrefs().edit().putInt(THEME, PURPLE).apply()
                3 -> mAppPrefs.getAppPrefs().edit().putInt(THEME, AMBER).apply()
            }
        }

        fun setTheme(mContext: Context) {
            val mAppPrefs = AppPreferenceManager().getSingleInstance(mContext)
            when (mAppPrefs.getAppPrefs().getInt(THEME, VOILET)) {
                VOILET -> mContext.setTheme(R.style.voiletTheme)
                PURPLE -> mContext.setTheme(R.style.purpleTheme)
                AMBER -> mContext.setTheme(R.style.amberTheme)
                else -> mContext.setTheme(R.style.voiletTheme)
            }
        }
    }
}