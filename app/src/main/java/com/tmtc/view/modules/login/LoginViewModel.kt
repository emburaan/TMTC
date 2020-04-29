package com.tmtc.view.modules.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dpoints.dpointsmerchant.datasource.remote.ApiCallbackImpl
import com.dpoints.dpointsmerchant.datasource.remote.NetworkState
import com.dpoints.dpointsmerchant.utilities.Event
import com.tmtc.datasource.auth1.AuthService
import com.tmtc.datasource.auth1.DataModel
import com.tmtc.datasource.auth1.LoginModelAuth

class LoginViewModel:ViewModel() {

    private val _loginState = MutableLiveData<Event<NetworkState<LoginModelAuth>>>()
    val loginState: LiveData<Event<NetworkState<LoginModelAuth>>> get() = _loginState

    private val _registerState = MutableLiveData<Event<NetworkState<DataModel>>>()
    val registerState: LiveData<Event<NetworkState<DataModel>>> get() = _registerState



    fun login(
        phone: String,
        password: String,
        deviceid:String
    ) {

        _loginState.value = Event(NetworkState.Loading())

        AuthService.instance.login(
            phone,
            password,
            deviceid,
            object : ApiCallbackImpl<LoginModelAuth>(_loginState) {
                override fun onSuccess(success: LoginModelAuth?) {
                   // Log.e("Data",success?.message)
                    _loginState.value = Event(NetworkState.Success(success))
                }
            })
    }


}