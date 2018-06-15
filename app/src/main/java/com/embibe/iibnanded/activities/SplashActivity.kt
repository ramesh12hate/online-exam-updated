package com.embibe.iibnanded.activities

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.embibe.iibnanded.R
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {
    internal val mRunnable: Runnable = Runnable {
        if (!isFinishing) {
            startActivity<LoginScreenActivity>().also { finish() }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed(mRunnable, 2000)

    }
}
