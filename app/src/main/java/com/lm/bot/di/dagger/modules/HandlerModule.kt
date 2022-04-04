package com.lm.bot.di.dagger.modules

import com.lm.bot.data.retrofit.Handler
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface HandlerModule {

    @Binds
    @Singleton
    fun bindsHandlerModule(handler: Handler.Base): Handler

}