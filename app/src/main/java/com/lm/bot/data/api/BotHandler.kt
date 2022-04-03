package com.lm.bot.data.api

import androidx.compose.runtime.mutableStateListOf
import com.github.kotlintelegrambot.dispatcher.handlers.CommandHandlerEnvironment
import com.github.kotlintelegrambot.dispatcher.handlers.TextHandlerEnvironment
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.network.fold
import com.lm.bot.data.model.Message
import com.lm.bot.data.repository.Repository
import com.lm.retrofit.data.api.APIResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.ChannelResult
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.launch
import javax.inject.Inject

interface BotHandler {

    fun onJoke(scope: CommandHandlerEnvironment): CommandHandlerEnvironment

    fun onStart(scope: CommandHandlerEnvironment): CommandHandlerEnvironment

    fun onText(
        scope: TextHandlerEnvironment,
        producerScope: ProducerScope<MutableList<Message>>
    ): ChannelResult<Unit>

    class Base @Inject constructor(private val repository: Repository) : BotHandler {

        override fun onJoke(scope: CommandHandlerEnvironment) =
            scope.apply {
                bot.apply {
                    message.chat.apply {
                        CoroutineScope(Dispatchers.IO).launch {
                            repository.joke().collect {
                                when (it) {
                                    is APIResponse.Success -> {
                                        it.data?.apply {
                                            if (get("type").asString != "single")
                                                sendMessage(
                                                    id.cId,
                                                    "- ${get("setup").asString}\n- ${get("delivery").asString}"
                                                )
                                            else sendMessage(id.cId, get("joke").asString)
                                            cancel()
                                        }
                                    }
                                    is APIResponse.Loading -> {}

                                    is APIResponse.Failure -> {
                                        sendMessage(id.cId, error)
                                        cancel()
                                    }

                                    is APIResponse.Exception -> {
                                        sendMessage(id.cId, error)
                                        cancel()
                                    }
                                }
                            }
                        }
                    }
                }
            }

        override fun onStart(scope: CommandHandlerEnvironment) =
            scope.apply { bot.sendMessage(message.chat.id.cId, "Hi there!").fold({}, {}) }

        override fun onText(
            scope: TextHandlerEnvironment,
            producerScope: ProducerScope<MutableList<Message>>
        ): ChannelResult<Unit> {
            list.add(Message(scope.message.chat.id, scope.text, scope.message.chat.firstName!!))
           return producerScope.trySendBlocking(list)
        }



        private val Long.cId get() = ChatId.fromId(this)

        private val error = "Error. Try again."

        private val list by lazy { mutableStateListOf<Message>() }

    }

}