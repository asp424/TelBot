package com.lm.bot.di.dagger.modules

import com.lm.bot.data.repository.Repository
import com.lm.bot.data.retrofit.RetrofitProvider
import dagger.Binds
import dagger.Module
import javax.inject.Singleton


@Module
interface RetrofitProviderModule {

    @Binds
    @Singleton
    fun bindRetrofitProvider(rP: RetrofitProvider.Base): RetrofitProvider
}