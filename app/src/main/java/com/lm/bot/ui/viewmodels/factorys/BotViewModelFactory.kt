package com.lm.bot.ui.viewmodels.factorys

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lm.bot.core.SharedPrefProvider
import com.lm.bot.data.bot.Repository
import com.lm.bot.ui.viewmodels.BotViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

class BotViewModelFactory(
    private val intent: Intent,
    private val sP: SharedPrefProvider,
    private val repository: Repository
) : ViewModelProvider.Factory {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        BotViewModel(intent, sP, repository) as T
}
