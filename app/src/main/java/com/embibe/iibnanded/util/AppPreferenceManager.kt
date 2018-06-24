package com.embibe.iibnanded.util

import android.content.Context
import android.content.SharedPreferences


class AppPreferenceManager {
    companion object {
        private var appPrefs: SharedPreferences? = null
    }

    var singleInstance: AppPreferenceManager? = null

    private var context: Context? = null

    /**
     * Returns a Single instance of the Preference manager.
     */
    fun getSingleInstance(c: Context): AppPreferenceManager {
        context = c
        if (singleInstance == null) {
            synchronized(AppPreferenceManager::class.java) {
                if (singleInstance == null) {
                    singleInstance = AppPreferenceManager()

                }
            }
        }
        init()
        return this!!.singleInstance!!
    }

    /**
     * Given method creates a new SharedPrefs for the application and initializes it
     * with some values.
     */
    private fun init() {
        try {
            appPrefs = context!!.getSharedPreferences("OnlineAppSharedPrefs", Context.MODE_PRIVATE)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun getAppPrefs(): SharedPreferences {
        return appPrefs!!
    }

}