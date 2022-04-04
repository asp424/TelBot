package com.lm.bot.notification

import android.app.Application
import android.app.Notification
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.CATEGORY_SERVICE
import androidx.core.app.NotificationCompat.PRIORITY_MIN
import com.lm.bot.R
import javax.inject.Inject

interface NotificationProvider {

    fun notification(): Notification

    class Base @Inject constructor(private val context: Application) : NotificationProvider {

        override fun notification() = NotificationCompat.Builder(context, channel)
            .setOngoing(true)
            .setContentTitle("      Bot started")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setPriority(PRIORITY_MIN)
            .setCategory(CATEGORY_SERVICE)
            .build()

        private val channel = "my_service"

    }
}