package com.embibe.iibnanded.activities

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.embibe.iibnanded.R
import com.embibe.iibnanded.util.AppPreferenceManager
import com.embibe.iibnanded.util.Constants
import com.embibe.iibnanded.util.Constants.Companion.ISLOGGEDIN
import org.jetbrains.anko.startActivity


class SplashActivity : AppCompatActivity(), Constants {
    private val mRunnable: Runnable = Runnable {
        if (!isFinishing) {
            val isLoggedIn = AppPreferenceManager().getSingleInstance(this).getAppPrefs().getBoolean(ISLOGGEDIN, false)
            if (isLoggedIn) {
                startActivity<DashboardActivity>().also {
                    finish()
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                }
            } else {
                startActivity<LoginScreenActivity>().also {
                    finish()
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                }
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed(mRunnable, 2000)

    }
}
