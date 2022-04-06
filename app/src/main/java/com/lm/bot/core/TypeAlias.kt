package com.lm.bot.core

import androidx.lifecycle.ViewModel
import com.github.kotlintelegrambot.dispatcher.handlers.CommandHandlerEnvironment
import com.github.kotlintelegrambot.dispatcher.handlers.TextHandlerEnvironment
import com.google.gson.JsonObject
import com.lm.bot.data.model.Joke
import com.lm.bot.data.model.Message
import com.lm.bot.data.retrofit.ApiResponse
import kotlinx.coroutines.channels.ChannelResult
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Provider

typealias CH = CommandHandlerEnvironment

typealias TH = TextHandlerEnvironment

typealias PS = ProducerScope<MutableList<Message>>

typealias CR = ChannelResult<Unit>

typealias MF = MutableStateFlow<MutableList<Message>>

typealias FJ = Flow<ApiResponse<JsonObject>>

typealias Fj = Flow<ApiResponse<Joke>>

typealias PM = @JvmSuppressWildcards Provider<ViewModel>

typealias  VM= Map<Class<out ViewModel>, PM>

