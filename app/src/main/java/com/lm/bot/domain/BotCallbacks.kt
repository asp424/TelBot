package com.lm.bot.domain

import androidx.compose.runtime.mutableStateListOf
import com.github.kotlintelegrambot.dispatcher.handlers.CommandHandlerEnvironment
import com.github.kotlintelegrambot.dispatcher.handlers.TextHandlerEnvironment
import com.github.kotlintelegrambot.entities.ChatId
import com.lm.bot.core.ResourceProvider
import com.lm.bot.data.model.Joke
import com.lm.bot.data.model.Message
import com.lm.bot.data.repository.Repository
import com.lm.bot.data.retrofit.ApiResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.ChannelResult
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.launch
import javax.inject.Inject

interface BotCallbacks {

    fun onJoke(sc: CommandHandlerEnvironment)

    fun onStart(sc: CommandHandlerEnvironment)

    fun onText(
        scope: TextHandlerEnvironment,
        producerScope: ProducerScope<MutableList<Message>>
    ): ChannelResult<Unit>

    fun reCallMessage(sc: CommandHandlerEnvironment, text: String)

    class Base @Inject constructor(
        private val repository: Repository,
        private val rP: ResourceProvider
    ) : BotCallbacks {
        override fun onJoke(sc: CommandHandlerEnvironment) {
            CoroutineScope(Dispatchers.IO).launch {
                repository.joke().collect {
                    when (it) {
                        is ApiResponse.Success -> {
                            it.data?.apply {
                                reCallMessage(sc, if (type != rP.single) twoJoke else joke)
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
            scope.apply { list.add(Message(message.chat.id, text, message.chat.firstName!!)) }
            return producerScope.trySendBlocking(list)
        }

        override fun reCallMessage(sc: CommandHandlerEnvironment, text: String) {
            sc.bot.sendMessage(sc.message.chat.id.cId, text)
        }

        private val Joke.twoJoke get() = "- $setup\n- $delivery"

        private val Long.cId get() = ChatId.fromId(this)

        private val list by lazy { mutableStateListOf<Message>() }

    }
}