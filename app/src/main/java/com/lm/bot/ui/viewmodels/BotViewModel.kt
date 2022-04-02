package com.lm.bot.ui.viewmodels

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.lm.bot.core.SharedPrefProvider
import com.lm.bot.data.bot.Repository

class BotViewModel(
    private val intent: Intent,
    private val sP: SharedPrefProvider,
    private val repository: Repository

) : ViewModel() {

    fun startBot(context: Context) = with(context) { context.startService(intent); sP.run() }

    fun stopBot(context: Context) = with(context) { context.stopService(intent); sP.stop() }

    fun check() = sP.read()

}