package com.lm.bot.di.dagger.modules

import com.lm.bot.ui.recycler_view.RvData
import com.lm.bot.ui.recycler_view.RvDataImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RvDataModule {

    @Binds
    @Singleton
    fun bindsRvData(rvData: RvDataImpl): RvData
}