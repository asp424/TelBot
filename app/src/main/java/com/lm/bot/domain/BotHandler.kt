package com.lm.bot.domain

import androidx.compose.runtime.mutableStateListOf
import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.dispatcher.handlers.CommandHandlerEnvironment
import com.github.kotlintelegrambot.dispatcher.handlers.TextHandlerEnvironment
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.network.fold
import com.lm.bot.core.ResourceProvider
import com.lm.bot.data.retrofit.ApiResponse
import com.lm.bot.data.model.Joke
import com.lm.bot.data.model.Message
import com.lm.bot.data.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.ChannelResult
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface BotHandler {

    fun reCallMessage(sc: CommandHandlerEnvironment, text: String)

    fun sendMessage(bot: Bot, id: Long, text: String): Flow<Any>

    fun onJoke(sc: CommandHandlerEnvironment)

    fun onStart(sc: CommandHandlerEnvironment)

    fun onText(
        scope: TextHandlerEnvironment,
        producerScope: ProducerScope<MutableList<Message>>
    ): ChannelResult<Unit>

    class Base @Inject constructor(
        private val repository: Repository,
        private val rP: ResourceProvider
    ) : BotHandler {

        override fun onJoke(sc: CommandHandlerEnvironment) {
            CoroutineScope(IO).launch {
                repository.joke().collect {

                    when (it) {
                        is ApiResponse.Success -> {
                            it.data?.apply {
                                if (type != rP.single) reCallMessage(sc, twoJoke)
                                else reCallMessage(sc, joke)
                            }
                        }
                        is ApiResponse.Failure -> reCallMessage(sc, rP.error)

                        is ApiResponse.Exception -> reCallMessage(sc, rP.error)
                        else -> {}
                    }
                }; cancel()
            }
        }

        override fun onStart(sc: CommandHandlerEnvironment) {
            sc.apply { bot.sendMessage(message.chat.id.cId, rP.hi) }
        }

        override fun onText(
            scope: TextHandlerEnvironment,
            producerScope: ProducerScope<MutableList<Message>>
        ): ChannelResult<Unit> {
            list.add(Message(scope.message.chat.id, scope.text, scope.message.chat.firstName!!))
            return producerScope.trySendBlocking(list)
        }

        override fun reCallMessage(sc: CommandHandlerEnvironment, text: String) {
            sc.bot.sendMessage(sc.message.chat.id.cId, text)
        }

        override fun sendMessage(bot: Bot, id: Long, text: String) =
            flow {
                bot.sendMessage(id.cId, text).fold({
                    CoroutineScope(IO).launch { emit(checkNotNull(it)); cancel() }
                }, {
                    CoroutineScope(IO).launch {
                        emit(checkNotNull(it.exception?.message)); cancel()
                    }
                })
            }

        private val Joke.twoJoke get() = "- $setup\n- $delivery"

        private val Long.cId get() = ChatId.fromId(this)

        private val list by lazy { mutableStateListOf<Message>() }

    }
}