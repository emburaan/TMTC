package com.tmtc.view.modules.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.dpoints.dpointsmerchant.datasource.remote.NetworkState
import com.dpoints.dpointsmerchant.utilities.clearErrorMessage
import com.dpoints.dpointsmerchant.utilities.getVM
import com.dpoints.dpointsmerchant.utilities.onTextChanged
import com.dpoints.dpointsmerchant.view.commons.base.BaseActivity
import com.tmtc.R
import com.tmtc.datasource.auth1.LoginModel1
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {
    override val layout: Int = R.layout.activity_login
    private val viewModel by lazy { getVM<LoginViewModel>(this) }
    override fun init() {

        login_btn.setOnClickListener {
            if (validation()) {
                viewModel.login(et_phone.text.toString(), et_pass.text.toString())
            }
        }

        et_phone.onTextChanged { _, _, _, _ -> til_phone.clearErrorMessage() }
        et_pass.onTextChanged { _, _, _, _ -> til_pass.clearErrorMessage() }
        avi.smoothToShow()

        addObserver()

    }




    private fun validation()= when {
        et_phone.text?.toString().isNullOrEmpty() -> {
            til_phone.error = getString(R.string.error_username)
            et_phone.requestFocus()
            false
        }
        (!(et_phone.text.toString().length in 10..12)) -> {
            til_phone.error = getString(R.string.exceed_length_10)
            et_phone.requestFocus()
            false
        }
        (!(et_pass.text.toString().length in 8..15)) -> {
            til_pass.error = getString(R.string.exceed_pass)
            et_pass.requestFocus()
            false
        }

        else -> true
    }

    private fun addObserver() {
        viewModel.loginState.observe(this, Observer {
            it ?: return@Observer
            val state = it.getContentIfNotHandled() ?: return@Observer
            Log.d("state",state.toString())

            if (state is NetworkState.Loading) {
                return@Observer showProgress(this)
            }

            hideProgress()

            when (state) {
                is NetworkState.Success -> {
                  if (state.data!!.message.contains("Invalid Phone No. or Password")){
                      onError(state.data.message)
                  }else{
                      onSuccess(state.data.message)
                  }

                }
                is NetworkState.Error -> Toast.makeText(this,"loged error",Toast.LENGTH_SHORT).show()
                is NetworkState.Failure -> Toast.makeText(this,"loged failure",Toast.LENGTH_SHORT).show()

                else -> Log.d("failure","failure")
            }
        })
    }
}
