package com.lm.bot.di.dagger.modules

import com.lm.bot.data.SharedPrefProvider
import dagger.Binds
import dagger.Module
import javax.inject.Singleton


@Module
interface SharedPrefProviderModule {

    @Binds
    @Singleton
    fun bindSharedPrefProvider(sharedPrefProvider: SharedPrefProvider.Base): SharedPrefProvider
}