package com.lm.bot.di.dagger.modules

import com.lm.bot.core.ResourceProvider
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface ResourceProviderModule {

    @Binds
    @Singleton
    fun bindsResourceProvider(resourceProvider: ResourceProvider.Base): ResourceProvider

}