package com.lm.bot.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lm.bot.data.SharedPrefProvider
import com.lm.bot.domain.BotInteraction
import com.lm.bot.domain.BotInteraction.Base.Companion.init
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class BotViewModel @Inject constructor(
    private val sP: SharedPrefProvider,
    private val botInteraction: BotInteraction
) : ViewModel() {

    private val _botInfo = MutableStateFlow<Pair<String, String?>>(init)
    val botInfo get() = _botInfo.asStateFlow()

    fun check() = sP.read()

    fun botInfo() = viewModelScope.launch {
        _botInfo.value = init
        botInteraction.botInfo().collect { _botInfo.value = it }
    }

    val listMessages get() = botInteraction.messagesFlow.asStateFlow()
}




