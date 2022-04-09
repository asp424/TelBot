package com.lm.bot.core

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import com.lm.bot.data.shared_pref.SharedPrefProvider
import com.lm.bot.databinding.RecyclerViewBinding
import com.lm.bot.domain.BotDataProvider
import com.lm.bot.presentation.BotViewModel
import com.lm.bot.presentation.BotViewModelFactory
import com.lm.bot.ui.recycler_view.*
import javax.inject.Inject
import javax.inject.Singleton

abstract class BaseActivity: ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: BotViewModelFactory

    @Inject
    lateinit var botIntent: Intent

    @Inject
    lateinit var sP: SharedPrefProvider

    @Inject
    lateinit var dP: BotDataProvider

    @Inject
    lateinit var adapter: Adapter

    protected val vm: BotViewModel by viewModels { viewModelFactory }

     fun startBot() { applicationContext.startForegroundService(botIntent); sP.run(); sP.saveId(dP.botToken) }

     fun stopBot() { applicationContext.stopService(botIntent); sP.stop(); sP.saveId("") }

}