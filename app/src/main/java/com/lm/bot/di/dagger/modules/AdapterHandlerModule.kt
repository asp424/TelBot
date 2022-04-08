package com.lm.bot.di.dagger.modules

import com.lm.bot.domain.BotDataProvider
import com.lm.bot.ui.recycler_view.AdapterHandler
import com.lm.bot.ui.recycler_view.AdapterHandlerImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface AdapterHandlerModule {

    @Binds
    @Singleton
    fun bindsAdapterHandler(adapterHandler: AdapterHandlerImpl): AdapterHandler
}