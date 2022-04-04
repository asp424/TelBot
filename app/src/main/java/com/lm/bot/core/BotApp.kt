package com.lm.bot.core

import android.app.Application
import android.content.Intent
import com.lm.bot.service.BotService
import com.lm.bot.di.dagger.DaggerAppComponent

class BotApp: Application() {
    val appComponent by lazy { DaggerAppComponent.builder().intent(Intent(this,
        BotService.Base::class.java)).sharPr(getSharedPreferences("id", MODE_PRIVATE))
        .context(this).create() }
}

val BotService.Base.appComponent get() = (this.applicationContext as BotApp).appComponent