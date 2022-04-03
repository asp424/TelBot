package com.lm.bot.domain

import android.util.Log
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.text.style.TextAlign
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.dispatcher.text
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.network.fold
import com.lm.bot.data.BotService.Companion.botToken
import com.lm.bot.data.model.Message
import com.lm.bot.data.repository.Repository
import com.lm.retrofit.data.api.APIResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject


interface BotInteractor {

    fun startBot(): Flow<MutableList<Message>>

    fun botInfo(): Flow<Pair<String, String?>>

    class Base @Inject constructor(private val repository: Repository) : BotInteractor {
        override fun startBot() = callbackFlow {
            with(bot {
                token = botToken
                dispatch {
                    text {
                        message.chat.apply {
                            bot.apply {
                                list.add(Message(id, text, firstName!!))
                                trySendBlocking(list)
                                sendMessage(id.cId, text)
                                command("start") {
                                    sendMessage(id.cId, "Hi there!")
                                        .fold({
                                        }, {

                                        })
                                }
                                command("joke") {
                                    CoroutineScope(IO).launch {
                                        repository.joke().collect {
                                            when (it) {
                                                is APIResponse.Success -> {
                                                    Log.d("My", it.toString())
                                                    //sendMessage(id.cId, it)
                                                }

                                                is APIResponse.Loading -> {}

                                                is APIResponse.Failure -> {}

                                                is APIResponse.Exception -> {}

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }) { startPolling(); awaitClose { stopPolling() } }
        }

        override fun botInfo() =
            callbackFlow {
                with(bot { token = botToken }.getMe()) {
                    fold({
                        it?.result?.apply { trySendBlocking(Pair(firstName, username)) }
                    }, { trySendBlocking(Pair("Wrong token", "")) })
                    awaitClose()
                }
            }.flowOn(IO)


        private val Long.cId get() = ChatId.fromId(this)

        private val list by lazy { mutableStateListOf<Message>() }
    }
}
