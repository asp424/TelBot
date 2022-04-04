package com.lm.bot.domain

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.dispatcher.text
import com.github.kotlintelegrambot.network.fold
import com.lm.bot.data.model.Message
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject


interface BotInteraction {

    fun start()

    fun botInfo(): Flow<Pair<String, String?>>

    val messagesFlow: MutableStateFlow<MutableList<Message>>

    val job: Job

    class Base @Inject constructor(private val botHandler: BotHandler) : BotInteraction {

        override val messagesFlow = MutableStateFlow(mutableListOf<Message>())

        private val ProducerScope<MutableList<Message>>.tBot
            get() = Bot.Builder().also { bB ->
                this.also { pS ->
                    bB.token = botToken
                    bB.dispatch {
                        botHandler.apply {
                            text { onText(this, pS) }
                            command("start") { onStart(this) }
                            command("joke") { onJoke(this) }
                        }
                    }
                }
            }.build()

        private val startBot
            get() = callbackFlow {
                with(tBot) {
                    startPolling(); awaitClose { stopPolling() }
                }
            }.flowOn(IO)

        override fun botInfo() = callbackFlow {
            with(bot { token = botToken }.getMe()) {
                fold({
                    it?.result?.apply { trySendBlocking(Pair(firstName, username)) }
                }, { trySendBlocking(wrong) })
                awaitClose()
            }
        }.flowOn(IO)

        override fun start() {
            job.apply { if (isActive) cancel() }
            job = CoroutineScope(IO).launch { startBot.collect { messagesFlow.value = it } }
        }

        private val wrong by lazy { Pair("Wrong token", "") }

        override var job: Job = Job()

        companion object {
            var botToken = "";
            var id = 0
        }
    }
}
