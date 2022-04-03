package com.lm.bot.data

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.PRIORITY_MIN
import com.lm.bot.R
import com.lm.bot.core.appComponent
import com.lm.bot.data.model.Message
import com.lm.bot.domain.BotInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface BotService {

    fun startBot(): Flow<MutableList<Message>>

    class Base @Inject constructor() : BotService, Service() {

        @Inject
        lateinit var botInteractor: BotInteractor

        private var job: Job = Job()

        override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
            appComponent.inject(this)
            job.apply { if (isActive) cancel() }
            job = CoroutineScope(IO).launch { startBot().collect { messagesFlow.value = it } }
            startForeground()
            return START_STICKY
        }

        private fun startForeground() {
            startForeground(
                101, NotificationCompat.Builder(this, can)
                    .setOngoing(true)
                    .setContentTitle("      Bot started")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setPriority(PRIORITY_MIN)
                    .setCategory(Notification.CATEGORY_SERVICE)
                    .build()
            )
        }

        override fun onDestroy() {
            super.onDestroy(); job.cancel()
        }

        override fun startBot() = botInteractor.startBot()

        override fun onBind(intent: Intent?): IBinder? = null

        private val can = "my_service"

    }

    companion object {
        var botToken = ""
        var id = 0
        val messagesFlow = MutableStateFlow(mutableListOf<Message>())
    }
}