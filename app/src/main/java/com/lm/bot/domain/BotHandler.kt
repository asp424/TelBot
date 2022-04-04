package com.lm.bot.domain

import androidx.compose.runtime.mutableStateListOf
import com.github.kotlintelegrambot.dispatcher.handlers.CommandHandlerEnvironment
import com.github.kotlintelegrambot.dispatcher.handlers.TextHandlerEnvironment
import com.github.kotlintelegrambot.entities.ChatId
import com.lm.bot.core.errors
import com.lm.bot.core.hi
import com.lm.bot.core.single
import com.lm.bot.data.model.Joke
import com.lm.bot.data.model.Message
import com.lm.bot.data.repository.Repository
import com.lm.retrofit.data.api.APIResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.ChannelResult
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.launch
import javax.inject.Inject

interface BotHandler {

    fun mess(sc: CommandHandlerEnvironment, text: String)

    fun onJoke(sc: CommandHandlerEnvironment): Job

    fun onStart(sc: CommandHandlerEnvironment)

    fun onText(
        scope: TextHandlerEnvironment,
        producerScope: ProducerScope<MutableList<Message>>
    ): ChannelResult<Unit>

    class Base @Inject constructor(private val repository: Repository) : BotHandler {

        override fun onJoke(sc: CommandHandlerEnvironment) =
            CoroutineScope(IO).launch {
                repository.joke().collect {
                    when (it) {
                        is APIResponse.Success -> {
                            it.data?.apply {
                                if (type != single) mess(sc, twoJoke) else mess(sc, joke)
                            }
                        }
                        is APIResponse.Failure -> mess(sc, errors)
                        is APIResponse.Exception -> mess(sc, errors)
                        else -> {}
                    }
                }; cancel()
            }

        override fun onStart(sc: CommandHandlerEnvironment) {
            sc.apply { bot.sendMessage(message.chat.id.cId, hi) }
        }

        override fun onText(
            scope: TextHandlerEnvironment,
            producerScope: ProducerScope<MutableList<Message>>
        ): ChannelResult<Unit> {
            list.add(Message(scope.message.chat.id, scope.text, scope.message.chat.firstName!!))
            return producerScope.trySendBlocking(list)
        }

        override fun mess(sc: CommandHandlerEnvironment, text: String) {
            sc.bot.sendMessage(sc.message.chat.id.cId, text)
        }

        private val Joke.twoJoke get() = "- $setup\n- $delivery"

        private val Long.cId get() = ChatId.fromId(this)

        private val list by lazy { mutableStateListOf<Message>() }

    }
}