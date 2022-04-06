package com.lm.bot.notification

import android.app.Application
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_LOW
import android.app.NotificationManager.IMPORTANCE_NONE
import android.content.Context.NOTIFICATION_SERVICE
import android.graphics.Color.BLUE
import androidx.core.app.NotificationCompat.*
import com.lm.bot.R
import com.lm.bot.core.ResourceProvider
import javax.inject.Inject


interface NotificationProvider {

    fun notification(): Notification

    class Base @Inject constructor(
        private val context: Application,
        private val rP: ResourceProvider
    ) : NotificationProvider {

        override fun notification(): Notification {
            createChannel
            return Builder(context, rP.channel)
                .setOngoing(true)
                .setContentTitle("      Bot started")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(PRIORITY_MIN)
                .setCategory(CATEGORY_SERVICE)
                .build()
        }

        private val createChannel by lazy {
            NotificationChannel(rP.channel, rP.name, IMPORTANCE_LOW)
                .apply {
                    lightColor = BLUE
                    lockscreenVisibility = VISIBILITY_PRIVATE
                    manager.createNotificationChannel(this)
                }
        }

        private val manager by lazy {
            (context.applicationContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager?)!!
        }
    }
}