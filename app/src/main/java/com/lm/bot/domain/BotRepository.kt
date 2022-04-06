package com.lm.bot.domain

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface BotRepository {

    fun startBot()

    fun botInfo(): Flow<Pair<String, String?>>

    class Base @Inject constructor(
        private val botHandler: BotHandler
    ) : BotRepository {

        override fun botInfo() = botHandler.botInfo()

        override fun startBot() = botHandler.startBot()

    }
}
