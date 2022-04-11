package com.lm.bot.di.dagger.modules

import com.lm.bot.ui.recycler_view.AdapterMethods
import com.lm.bot.ui.recycler_view.AdapterMethodsImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface AdapterHandlerModule {

    @Binds
    @Singleton
    fun bindsAdapterHandler(adapterHandler: AdapterMethodsImpl): AdapterMethods
}