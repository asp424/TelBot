package com.lm.bot.domain

import com.lm.bot.data.model.Message
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

interface BotDataProvider {

    val messagesFlow: MutableStateFlow<MutableList<Message>>

    var job: Job

    var botToken: String

    class Base @Inject constructor(): BotDataProvider{

        override val messagesFlow = MutableStateFlow(mutableListOf<Message>())

        override var job: Job = Job()

        override var botToken = ""

    }
}