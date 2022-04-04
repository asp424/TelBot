package com.lm.bot.di.dagger

import android.app.Application
import android.content.Intent
import android.content.SharedPreferences
import com.lm.bot.service.BotService
import com.lm.bot.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MapModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun intent(intent: Intent): Builder

        @BindsInstance
        fun sharPr(sharedPreferences: SharedPreferences): Builder

        @BindsInstance
        fun context(application: Application): Builder

        fun create(): AppComponent
    }

    fun inject(botService: BotService.Base)

    fun injectAct(mainActivity: MainActivity)
}