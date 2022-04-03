package com.lm.bot.di.dagger.modules

import com.lm.bot.data.api.Handler
import com.lm.bot.data.api.JokeApi
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface HandlerModule {

    @Binds
    @Singleton
    fun bindsHandlerModule(handler: Handler.Base): Handler

}