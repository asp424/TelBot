package com.lm.bot.di.dagger.modules

import com.lm.bot.data.api.BotHandler
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface BotHandlerModule {

    @Binds
    @Singleton
    fun bindsBotHandler(botHandler: BotHandler.Base): BotHandler
}