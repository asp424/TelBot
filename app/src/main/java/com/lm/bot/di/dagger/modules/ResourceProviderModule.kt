package com.lm.bot.di.dagger.modules

import com.lm.bot.core.ResourceProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ResourceProviderModule {

    @Provides
    @Singleton
    fun provideResourceProvider():ResourceProvider = ResourceProvider()
}