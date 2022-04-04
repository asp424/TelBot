package com.lm.bot.core

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import com.lm.bot.data.shared_pref.SharedPrefProvider
import com.lm.bot.domain.BotDataProvider
import com.lm.bot.presentation.BotViewModel
import com.lm.bot.presentation.BotViewModelFactory
import javax.inject.Inject

abstract class BaseActivity: ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: BotViewModelFactory

    @Inject
    lateinit var botIntent: Intent

    @Inject
    lateinit var sP: SharedPrefProvider

    @Inject
    lateinit var dP: BotDataProvider

    protected val vm: BotViewModel by viewModels { viewModelFactory }

     fun startBot() { applicationContext.startForegroundService(botIntent); sP.run() }

     fun stopBot() { applicationContext.stopService(botIntent); sP.stop() }

}