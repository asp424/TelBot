package com.lm.bot.domain

import android.util.Log
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.network.fold
import com.lm.bot.core.ResourceProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

interface BotHandler {

    fun startBot()

    fun botInfo(): Flow<Pair<String, String?>>

    class Base @Inject constructor(
        private val rP: ResourceProvider,
        private val botProvider: BotProvider,
        private val botDataProvider: BotDataProvider
    ) : BotHandler {

        override fun startBot() {
            botDataProvider.apply {
                job.apply { if (isActive) cancel() }
                job = CoroutineScope(IO).launch {
                    driveBot.collect {
                        Log.d("My", it.size.toString())
                        it.forEach { m ->
                            messagesFlow.value = m
                        }
                    }
                }
            }
        }

        override fun botInfo() = callbackFlow {
            with(botProvider.handleBot().getMe()) {
                fold({
                    it?.result?.apply { trySendBlocking(Pair(firstName, username)) }
                }, { trySendBlocking(rP.wrong) }); awaitClose()


            }
        }.flowOn(IO)

        private val driveBot
            get() = callbackFlow {
                with(botProvider.pollingBot(this)) { startPolling(); awaitClose { stopPolling() } }
            }.flowOn(IO)

        private val Long.cId get() = ChatId.fromId(this)

    }
}