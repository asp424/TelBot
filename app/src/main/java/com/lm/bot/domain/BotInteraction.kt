package com.lm.bot.domain

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.dispatcher.text
import com.github.kotlintelegrambot.network.fold
import com.lm.bot.core.ResourceProvider
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

    fun startBot()

    fun botInfo(): Flow<Pair<String, String?>>

    fun sendMessage(id: Long, text: String): Flow<Any>

    val messagesFlow: MutableStateFlow<MutableList<Message>>

    val job: Job

    class Base @Inject constructor(
        private val botHandler: BotHandler,
        private val rP: ResourceProvider
    ) : BotInteraction {

        override val messagesFlow = MutableStateFlow(mutableListOf<Message>())

        private val ProducerScope<MutableList<Message>>.tBot
            get() = Bot.Builder().also { bB ->
                this.also { pS ->
                    bB.token = rP.botToken
                    bB.dispatch {
                        botHandler.apply {
                            text { onText(this, pS) }
                            command(rP.start) { onStart(this) }
                            command(rP.joke) { onJoke(this) }
                        }
                    }
                }
            }.build()

        private val driveBot
            get() = callbackFlow {
                with(tBot) { startPolling(); awaitClose { stopPolling() } }
            }.flowOn(IO)

        override fun botInfo() = callbackFlow {
            with(handleBot.getMe()) {

                fold({
                    it?.result?.apply { trySendBlocking(Pair(firstName, username)) }

                }, { trySendBlocking(rP.wrong) }); awaitClose()
            }
        }.flowOn(IO)

        override fun sendMessage(id: Long, text: String) =
            botHandler.sendMessage(handleBot, id, text)

        override fun startBot() {
            job.apply { if (isActive) cancel() }
            job = CoroutineScope(IO).launch { driveBot.collect { messagesFlow.value = it } }
        }

        override var job: Job = Job()

        private val handleBot get() = bot { token = rP.botToken }

    }
}
