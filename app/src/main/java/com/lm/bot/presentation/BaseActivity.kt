package com.lm.bot.presentation

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import com.lm.bot.data.SharedPrefProvider
import javax.inject.Inject

abstract class BaseActivity: ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: BotViewModelFactory

    @Inject
    lateinit var botIntent: Intent

    @Inject
    lateinit var sP: SharedPrefProvider

    protected val vm: BotViewModel by viewModels { viewModelFactory }

    protected fun startBot() { applicationContext.startForegroundService(botIntent); sP.run() }

    protected fun stopBot() { applicationContext.stopService(botIntent); sP.stop() }

}