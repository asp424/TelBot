package com.lm.bot.data

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.lm.bot.core.appComponent
import com.lm.bot.data.model.Message
import com.lm.bot.domain.BotInteraction
import com.lm.bot.notification.NotificationProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface BotService {

    fun startBot(): Flow<MutableList<Message>>

    class Base @Inject constructor() : BotService, Service() {

        @Inject
        lateinit var botInteractor: BotInteraction

        @Inject
        lateinit var notification: NotificationProvider

        private var job: Job = Job()

        override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
            appComponent.inject(this)
            job.apply { if (isActive) cancel() }
            job = CoroutineScope(IO).launch { startBot().collect { botInteractor.messagesFlow.value = it } }
            startForeground(101, notification.notification())
            return START_STICKY
        }

        override fun onDestroy() {
            super.onDestroy(); job.cancel()
        }

        override fun startBot() = botInteractor.startBot()

        override fun onBind(intent: Intent?): IBinder? = null

    }

    companion object { var botToken = "" ; var id = 0 }
}