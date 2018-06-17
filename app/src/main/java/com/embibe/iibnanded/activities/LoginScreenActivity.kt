package com.embibe.iibnanded.activities

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import com.embibe.iibnanded.R
import com.embibe.iibnanded.model.LoginModel
import com.embibe.iibnanded.rest.ApiClient
import com.embibe.iibnanded.rest.ApiInterface
import com.embibe.iibnanded.util.Utilities
import kotlinx.android.synthetic.main.activity_login_screen.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern


class LoginScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        setViews()
    }

    private lateinit var apiService: ApiInterface
    private lateinit var mProgressDialog: ProgressDialog

    /**
     * Initialise the views and click listeners
     */
    private fun setViews() {
        //Initialise the API client
        apiService = ApiClient.client.create(ApiInterface::class.java)

        //Initialise the progress dialog and set title and message to progress dialog
        mProgressDialog = ProgressDialog(this)
        mProgressDialog.setTitle("Fetching data")
        mProgressDialog.setMessage("Please wait a bit..")


        tv_forget_password.setOnClickListener {
            //TODO Call the Forget Password API
            toast("Navigate to Forget Password Screen")
        }


        btn_sign_in.setOnClickListener {
            if (et_username.text.isNotEmpty()) {
                if (isValidEmail(et_username.text.toString()) || isValidMobile(et_username.text.toString())) {
                    if (et_password.text.isNotEmpty()) {
                        callLoginAPI(et_username.text.toString().trim(), et_password.text.toString().trim())
                    } else
                        Utilities.showSnackBar(coordinatorLayout, "Please enter password")

                } else
                    Utilities.showSnackBar(coordinatorLayout, "Enter Valid User Name")

            } else {
                Utilities.showSnackBar(coordinatorLayout, "Please enter credentials")
            }
        }

    }

    /**
     * @param userName username entered by user
     * @param password password enetered by user
     * Method is used to check if user is valid or not
     */
    private fun callLoginAPI(userName: String, password: String) {
        startActivity<DashboardActivity>().apply {
            finish()
            return
        }
        mProgressDialog.show()

        val call = apiService.validateLogin(userName, password)

        call.enqueue(object : Callback<LoginModel> {
            override fun onResponse(call: Call<LoginModel>, response: Response<LoginModel>) {
                val responseMessage = response.body().responseMessage
                Log.d("Login", responseMessage)

                when (response.body().responseCode) {
                    1 -> {
                        startActivity<DashboardActivity>().apply {
                            finish()
                        }
                    }

                    2 -> {
                        alert(responseMessage) {
                            title = "Alert"
                            positiveButton("ok") {
                                it.dismiss()
                                startActivity<DashboardActivity>().apply {
                                    finish()
                                }

                            }
                        }.show()
                    }
                }

                if (mProgressDialog.isShowing)
                    mProgressDialog.dismiss()
            }

            override fun onFailure(call: Call<LoginModel>, t: Throwable) {
                // Log error here since request failed
                Log.e("Login", t.toString())

                if (mProgressDialog.isShowing)
                    mProgressDialog.dismiss()
            }
        })
    }

    /**
     * @param target string which we want to validate
     * @return boolean based on validation
     */
    private fun isValidEmail(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    /**
     * @param phone the string which we ant to validate
     * @return boolean based on validation
     */
    private fun isValidMobile(phone: String): Boolean {

        return if (!Pattern.matches("[a-zA-Z]+", phone))
            !(phone.length > 10)
        else
            false
    }

}