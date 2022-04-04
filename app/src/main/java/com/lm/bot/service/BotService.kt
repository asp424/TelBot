package com.lm.bot.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.lm.bot.core.BotApp
import com.lm.bot.domain.BotDataProvider
import com.lm.bot.domain.BotRepository
import com.lm.bot.notification.NotificationProvider
import javax.inject.Inject

interface BotService {

    class Base @Inject constructor() : BotService, Service() {

        @Inject
        lateinit var botRepository: BotRepository

        @Inject
        lateinit var notification: NotificationProvider

        @Inject
        lateinit var botDataProvider: BotDataProvider

        override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
            (applicationContext as BotApp).appComponent.inject(this)
            botRepository.startBot()
            startForeground(101, notification.notification())
            return START_STICKY
        }

        override fun onDestroy() {
            super.onDestroy(); stopSelf(); botDataProvider.job.cancel()
        }

        override fun onBind(intent: Intent?): IBinder? = null

    }
}