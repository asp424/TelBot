package com.lm.bot.di.dagger.modules

import com.lm.bot.data.repository.Repository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindRepository(repository: Repository.Base): Repository
}