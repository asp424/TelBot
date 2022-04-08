package com.lm.bot.di.dagger

import com.lm.bot.di.dagger.modules.*
import dagger.Module
import kotlinx.coroutines.ExperimentalCoroutinesApi



@OptIn(ExperimentalCoroutinesApi::class)
@Module(
    includes = [
        BotInteractorModule::class,
        RepositoryModule::class, SharedPrefProviderModule::class, ViewModelModules::class,
        ViewModelFactoryModule::class, BotServiseModule::class, HandlerModule::class,
        NotificationProviderModule::class, BotHandlerModule::class, ResourceProviderModule::class,
        RetrofitProviderModule::class, BotProviderModule::class, BotCallbacksModule::class,
        BotDataProviderModule::class, RvDataModule::class
    ]
)
interface MapModule





