package com.lm.bot.data.bot

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.compose.runtime.mutableStateListOf
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.dispatcher.text
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.network.fold
import com.lm.bot.data.model.Message
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch


private val _message = MutableStateFlow(mutableListOf<Message>())
val message get() = _message.asStateFlow()

interface Repository {

    fun startBot(): Flow<MutableList<Message>>

    class Base : Repository, Service() {

        private var job: Job = Job()

        override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
            job.apply { if (isActive) cancel() }
            job = CoroutineScope(IO).launch { startBot().collect { _message.value = it } }
            return START_STICKY
        }

        override fun onDestroy() {
            super.onDestroy(); job.cancel()
        }

        override fun startBot() = callbackFlow {
            mutableStateListOf<Message>().apply {
                with(bot {
                    token = botToken
                    dispatch {
                        text {
                            add(
                                Message(
                                    message.chat.id,
                                    text,
                                    message.chat.firstName!!
                                )
                            )
                            trySendBlocking(this@apply)
                            bot.sendMessage(ChatId.fromId(message.chat.id), text = text)
                        }
                        command("start") {
                            bot.sendMessage(ChatId.fromId(message.chat.id), "Hi there!")
                                .fold({
                                }, {

                                })
                        }
                    }
                }) { startPolling(); awaitClose { stopPolling() } }
            }
        }

        override fun onBind(intent: Intent?): IBinder? = null
    }

    companion object {
        var botToken = ""
        var id = 0
    }
}