package com.tmtc.view.modules.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dpoints.dpointsmerchant.datasource.remote.ApiCallbackImpl
import com.dpoints.dpointsmerchant.datasource.remote.NetworkState
import com.dpoints.dpointsmerchant.utilities.Event
import com.tmtc.datasource.auth1.AuthService
import com.tmtc.datasource.auth1.LoginModel
import com.tmtc.datasource.auth1.LoginModel1

class LoginViewModel:ViewModel() {

    private val _loginState = MutableLiveData<Event<NetworkState<LoginModel>>>()
    val loginState: LiveData<Event<NetworkState<LoginModel>>> get() = _loginState



    fun login(
        phone: String,
        password: String
    ) {

        _loginState.value = Event(NetworkState.Loading())

        AuthService.instance.login(
            phone,
            password,
            object : ApiCallbackImpl<LoginModel>(_loginState) {
                override fun onSuccess(success: LoginModel?) {
                   // Log.e("Data",success?.message)
                    _loginState.value = Event(NetworkState.Success(success))
                }
            })
    }
}