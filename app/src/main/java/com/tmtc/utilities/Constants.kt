package com.dpoints.dpointsmerchant.utilities

import android.os.Build
import com.tmtc.BuildConfig


val os: String = Build.VERSION_CODES::class.java.fields[Build.VERSION.SDK_INT].name
val deviceName = Build.MANUFACTURER + " " + Build.MODEL
const val appVersion = BuildConfig.VERSION_NAME

const val CONNECTION_ERROR = "Failed to connect. Make sure you have an active internet connection."


class LoginType {
    companion object {
        const val CHANCE_COIN = "chance_coin"
        const val GOOGLE = "google"
        const val FACEBOOK = "facebook"
    }
}

class Action {
    companion object {
        const val ACTION_NOTIFICATION_RECEIVED = "com.dpoints.dpointsmerchant.action.notification.received"
        const val ACTION_COIN_TRANSFER = "COIN_TRANSFER"
    }
}
class Constants {
    companion object{

        const val DATA = "data"

    }
}