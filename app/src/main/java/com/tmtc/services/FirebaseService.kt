package com.dpoints.dpointsmerchant.services

import android.app.ActivityManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.dpoints.dpointsmerchant.preferences.UserPreferences
import com.dpoints.dpointsmerchant.utilities.Action
import com.tmtc.R

class FirebaseService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

    /*    if (UserPreferences.instance.isLoggedIn(this)) {
            with(remoteMessage?.data ?: return) {
                showNotification(this)
                handleData(this)
            }
        }*/
    }
    private fun showNotification(data: Map<String, String>) {
        /*val pendingIntent = PendingIntent.getActivities(this, 0, arrayOf(Intent(data["click_action"])),
            PendingIntent.FLAG_UPDATE_CURRENT)
        val builder = NotificationCompat.Builder(this, getString(R.string.default_web_client_id))
            .setAutoCancel(true)
            .setContentTitle(data["title"])
            .setContentText(data["body"])
            .setSmallIcon(R.mipmap.ic_launcher)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            .setContentIntent(pendingIntent)
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
        manager?.notify(0, builder.build())*/
    }
    private fun handleData(data: Map<String, String>) {
        if (!isAppInBackground(this)) {
            val intent = if (data["type"] == Action.ACTION_COIN_TRANSFER) {
                Intent(Action.ACTION_COIN_TRANSFER).apply {
                    putExtra("title", data["title"])
                    putExtra("body", data["body"])
                }
            } else {
                    Intent(Action.ACTION_NOTIFICATION_RECEIVED).apply {
                    putExtra("title", data["title"])
                    putExtra("body", data["body"])
                }
            }

            LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
        }
    }
    private fun isAppInBackground(context: Context): Boolean {
        var isInBackground = true
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val runningProcesses = am.runningAppProcesses
        for (processInfo in runningProcesses) {
            if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                for (activeProcess in processInfo.pkgList) {
                    if (activeProcess == context.packageName) {
                        isInBackground = false
                    }
                }
            }
        }
        return isInBackground
    }
}