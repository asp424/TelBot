package com.lm.bot.di.dagger.modules

import androidx.lifecycle.viewmodel.compose.R
import com.lm.bot.data.repository.Repository
import com.lm.bot.domain.BotInteractor
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindRepository(repository: Repository.Base): Repository
}