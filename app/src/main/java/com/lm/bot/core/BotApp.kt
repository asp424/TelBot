package com.lm.bot.core

import android.app.Application
import android.content.Intent
import com.lm.bot.di.dagger.DaggerAppComponent
import com.lm.bot.service.BotService

class BotApp : Application() {
    val appComponent by lazy {
        DaggerAppComponent.builder().intent(
            Intent(this, BotService.Base::class.java)
        ).sharPr(getSharedPreferences("bot", MODE_PRIVATE)).context(this).create()
    }
}

