package com.lm.bot.domain

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface BotRepository {

    fun startBot()

    fun botInfo(): Flow<Pair<String, String?>>

    fun sendMessage(id: Long, text: String): Flow<Any>

    class Base @Inject constructor(
        private val botHandler: BotHandler
    ) : BotRepository {

        override fun botInfo() = botHandler.botInfo()

        override fun sendMessage(id: Long, text: String) = botHandler.sendMessage(id, text)

        override fun startBot() = botHandler.startBot()

    }
}
