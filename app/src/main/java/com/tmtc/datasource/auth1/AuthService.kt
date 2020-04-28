package com.tmtc.datasource.auth1

import com.dpoints.dpointsmerchant.datasource.remote.ApiCallback
import com.dpoints.dpointsmerchant.datasource.remote.ApiClient
import com.dpoints.dpointsmerchant.datasource.remote.ApiResult
import com.dpoints.dpointsmerchant.successsource.remote.CallbackImpl
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


class AuthService private constructor(){

    companion object{val instance:AuthService by lazy { AuthService() }}

    fun login(
        phone:String,
        password:String,
        deviceid:String,
        callback: ApiCallback<LoginModelAuth>
    ){
        val service = ApiClient.retrofit.create(Servces::class.java)
        val call = service.login(phone,password,deviceid)
        call.enqueue(CallbackImpl(callback))
    }


}

interface Servces {

    @POST("login")
    @FormUrlEncoded
    fun login(
        @Field("phone") phone: String,
        @Field("password") password: String,
        @Field("deviceid") deviceid:String

    ): Call<ApiResult<LoginModelAuth>>



}
