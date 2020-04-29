package com.tmtc.view.modules.login

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.dpoints.dpointsmerchant.datasource.remote.NetworkState
import com.dpoints.dpointsmerchant.preferences.UserPreferences
import com.dpoints.dpointsmerchant.utilities.clearErrorMessage
import com.dpoints.dpointsmerchant.utilities.getVM
import com.dpoints.dpointsmerchant.utilities.onTextChanged
import com.dpoints.dpointsmerchant.view.commons.base.BaseFragment
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.InstanceIdResult
import com.tmtc.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginFragment : BaseFragment() {
    override val layout: Int = R.layout.activity_login
    private val viewModel by lazy { getVM<LoginViewModel>(activity!!) }
    var token:String=""
    fun getFirebseToken():String{

        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener(activity!!){ instanceIdResult: InstanceIdResult ->
            token = instanceIdResult.token
            Log.e("FirebaseToken", token)
        }
        return token
    }

    override fun init(view: View) {
        getFirebseToken()
        login_btn.setOnClickListener {
            if (validation()) {
                viewModel.login(et_phone.text.toString(), et_pass.text.toString(),token)


            }
        }
       //Log.d("roole",UserPreferences.instance.getRole(context!!)!!.roleDesc)
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
                return@Observer showProgress(activity!!)
            }

            hideProgress()

            when (state) {
                is NetworkState.Success -> {
                    Log.d("roole",state.data!!.data!!.tblRoleMaster.roleId.toString())
                    if (state.data!!.message.contains("Invalid PhoneNo or Password."))
                    {
                        onError(state.data.message)
                    }else{
                        UserPreferences.instance.saveUser(context!!,state.data.data!!)
                        UserPreferences.instance.saveRole(context!!,state.data.data.tblRoleMaster)
                        getFragmentManager()!!.popBackStack();
                        Toast.makeText(context,state.data.message, Toast.LENGTH_LONG).show()
                    }

                }
                is NetworkState.Error -> onError(state.message)
                is NetworkState.Failure -> onFailure(getString(R.string.request_error))

                else -> onFailure(getString(R.string.connection_error))
            }
        })
    }
}
