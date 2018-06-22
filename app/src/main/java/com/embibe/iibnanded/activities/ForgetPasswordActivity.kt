package com.embibe.iibnanded.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.embibe.iibnanded.R
import kotlinx.android.synthetic.main.forget_password.*
import org.jetbrains.anko.toast

class ForgetPasswordActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forget_password)

        btn_back.setOnClickListener(this)
        btn_reset_password.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_back -> {
                finish()
            }

            R.id.btn_reset_password -> {
                toast("Reset Password")
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}