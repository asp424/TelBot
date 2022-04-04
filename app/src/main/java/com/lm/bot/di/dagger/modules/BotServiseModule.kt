package com.lm.bot.di.dagger.modules

import com.lm.bot.service.BotService
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface BotServiseModule {

    @Binds
    @Singleton
    fun bindBotServise(botService: BotService.Base): BotService
}