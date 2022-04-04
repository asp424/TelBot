package com.lm.bot.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lm.bot.core.ResourceProvider
import com.lm.bot.data.shared_pref.SharedPrefProvider
import com.lm.bot.domain.BotInteraction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class BotViewModel @Inject constructor(
    private val sP: SharedPrefProvider,
    private val botInteraction: BotInteraction,
    private val rP: ResourceProvider
) : ViewModel() {

    private val _botInfo = MutableStateFlow<Pair<String, String?>>(rP.init)
    val botInfo get() = _botInfo.asStateFlow()

    fun check() = sP.read()

    fun botInfo() = viewModelScope.launch {
        clearInfo()
        botInteraction.botInfo().collect { _botInfo.value = it }
    }

    fun clearInfo() {
        _botInfo.value = rP.init
    }

    val listMessages get() = botInteraction.messagesFlow.asStateFlow()
}




