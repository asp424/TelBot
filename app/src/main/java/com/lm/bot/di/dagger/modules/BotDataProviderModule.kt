package com.lm.bot.di.dagger.modules

import com.lm.bot.domain.BotDataProvider
import dagger.Binds
import dagger.Module
import javax.inject.Singleton


@Module
interface BotDataProviderModule {

    @Binds
    @Singleton
    fun bindsBotDataProvider(botDataProvider: BotDataProvider.Base): BotDataProvider
}