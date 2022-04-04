package com.lm.bot.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.lm.bot.core.appComponent
import com.lm.bot.domain.BotInteraction
import com.lm.bot.notification.NotificationProvider
import javax.inject.Inject

interface BotService {

    class Base @Inject constructor() : BotService, Service() {

        @Inject
        lateinit var botInteraction: BotInteraction

        @Inject
        lateinit var notification: NotificationProvider

        override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
            appComponent.inject(this)
            botInteraction.start()
            startForeground(101, notification.notification())
            return START_STICKY
        }

        override fun onDestroy() { super.onDestroy(); botInteraction.job.cancel() }

        override fun onBind(intent: Intent?): IBinder? = null

    }
}