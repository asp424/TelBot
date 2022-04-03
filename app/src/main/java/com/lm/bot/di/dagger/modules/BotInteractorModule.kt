package com.lm.bot.di.dagger.modules

import com.lm.bot.domain.BotInteraction
import dagger.Binds
import dagger.Module
import javax.inject.Singleton


@Module
interface BotInteractorModule {

    @Binds
    @Singleton
    fun bindBotInteractor(botInteractor: BotInteraction.Base): BotInteraction
}