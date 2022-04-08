package com.lm.bot.di.dagger.modules

import com.lm.bot.ui.recycler_view.AdapterImpl
import com.lm.bot.ui.recycler_view.RvData
import com.lm.bot.ui.recycler_view.RvDataImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface AdapterModule {

    @Binds
    fun bindsRvData(rvData: RvDataImpl): RvData

}