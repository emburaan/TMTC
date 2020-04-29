package com.tmtc.services

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.media.RingtoneManager
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.annotation.Nullable
import androidx.core.app.NotificationCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.dpoints.dpointsmerchant.preferences.UserPreferences.Companion.instance
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.tmtc.R
import com.tmtc.view.modules.dashboard.DashBoardActivity

class FirebaseMessagingServices: FirebaseMessagingService() {
    private val TAG = "FirebaseMessagingServices"
    var bitmap: Bitmap? = null
    var clickAction: String? = null

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        Log.d("Notification Data of Data",p0.data.toString())
        if (p0.data != null) {
            getImage(p0)
        }else if (p0.notification != null){
            getImage(p0)


        }
    }

    private fun getImage(p0: RemoteMessage) {
        val data: Map<String, String> = p0.getData()
        if (p0.data != null){
            Config.title = data.get("title")!!
            Config.body= data.get("body")!!
            val uiHandler = Handler(Looper.getMainLooper())
            uiHandler.post {
                // Get image from data Notification
                if (!Config.urlToImage.trim().equals("")) {
                    Glide.with(applicationContext)
                        .asBitmap()
                        .load(Config.urlToImage)
                        .apply(RequestOptions().override(100, 100))
                        .listener(object : RequestListener<Bitmap?> {
                            override fun onLoadFailed(
                                @Nullable e: GlideException?, model: Any?,
                                target: Target<Bitmap?>?,
                                isFirstResource: Boolean
                            ): Boolean {
                                return false
                            }

                            override fun onResourceReady(
                                resource: Bitmap?,
                                model: Any?,
                                target: Target<Bitmap?>?,
                                dataSource: DataSource?,
                                isFirstResource: Boolean
                            ): Boolean {
                                // resource is your loaded Bitmap

                                sendNotification(null)
                                return true
                            }
                        })
                }
            }

        }

    }

    private fun sendNotification(bitmap: Bitmap?) {
        val defaultSound =
            RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        //Intent intent = new Intent(getApplicationContext(), HomeActivity.class)
        var intent: Intent? = null
        if (instance.isLoggedIn(this)) {
            intent = Intent(this, DashBoardActivity::class.java)
            intent.putExtra("NOTIFICATION", "YES")
        }
        intent!!.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val NOTIFICATION_CHANNEL_ID = "101"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            @SuppressLint("WrongConstant") val notificationChannel =
                NotificationChannel(
                    NOTIFICATION_CHANNEL_ID,
                    "Notification",
                    NotificationManager.IMPORTANCE_MAX
                )
            //Configure Notification Channel
            notificationChannel.description = "News Notifications"
            notificationChannel.enableLights(true)
            notificationChannel.vibrationPattern = longArrayOf(0)
            notificationChannel.enableVibration(false)
            notificationManager.createNotificationChannel(notificationChannel)
        }
        val notificationBuilder =
            NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_marthoma)
                .setContentTitle(Config.title)
                .setAutoCancel(true)
                .setContentText(Config.body)
                .setContentIntent(pendingIntent)
                .setDefaults(Notification.DEFAULT_LIGHTS or Notification.DEFAULT_SOUND)
                .setVibrate(longArrayOf(0L))
        if (bitmap != null) {
            val style =
                NotificationCompat.BigPictureStyle()
            style.bigPicture(bitmap)
            notificationBuilder.setStyle(style)
        }
        notificationBuilder.setWhen(System.currentTimeMillis()).priority = Notification.PRIORITY_MAX
        notificationManager.notify(1, notificationBuilder.build())
    }


}