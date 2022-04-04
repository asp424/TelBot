package com.lm.bot.di.dagger.modules

import com.lm.bot.domain.BotCallbacks
import com.lm.bot.service.BotService
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface BotCallbacksModule {

    @Binds
    @Singleton
    fun bindBotCallbacks(botCallbacks: BotCallbacks.Base): BotCallbacks
}