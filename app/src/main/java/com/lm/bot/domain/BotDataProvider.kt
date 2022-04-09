package com.lm.bot.domain

import androidx.compose.runtime.mutableStateListOf
import com.lm.bot.core.MF
import com.lm.bot.data.model.Message
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

interface BotDataProvider {

    val messagesFlow: MutableStateFlow<Message>

    var job: Job

    var botToken: String

    class Base @Inject constructor(): BotDataProvider{

        override val messagesFlow: MutableStateFlow<Message> = MutableStateFlow(Message())

        override var job: Job = Job()

        override var botToken = ""

    }
}