package com.lm.bot.domain

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.dispatcher.text
import com.lm.bot.core.PS
import com.lm.bot.core.ResourceProvider
import javax.inject.Inject

interface BotProvider {

    fun pollingBot(scope: PS): Bot

    fun handleBot(): Bot

    class Base @Inject constructor(
        private val rP: ResourceProvider,
        private val botCallbacks: BotCallbacks,
        private val bP: BotDataProvider
    ) : BotProvider {

        override fun pollingBot(scope: PS) =
            Bot.Builder().also { bB ->
                bB.token = bP.botToken
                bB.dispatch {
                    botCallbacks.apply {
                        text { onText(this, scope) }
                        command(rP.start) { onStart(this) }
                        command(rP.joke) { onJoke(this) }
                    }
                }
            }.build()

        override fun handleBot() = bot { token = bP.botToken }
    }
}