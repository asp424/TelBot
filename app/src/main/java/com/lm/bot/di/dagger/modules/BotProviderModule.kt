package com.lm.bot.di.dagger.modules

import com.lm.bot.domain.BotProvider
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface BotProviderModule {

    @Binds
    @Singleton
    fun bindsBotProvider(botProvider: BotProvider.Base): BotProvider
}