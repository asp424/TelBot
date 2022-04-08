package com.lm.bot.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lm.bot.core.ResourceProvider
import com.lm.bot.data.repository.Repository
import com.lm.bot.data.shared_pref.SharedPrefProvider
import com.lm.bot.domain.BotDataProvider
import com.lm.bot.domain.BotRepository
import com.lm.bot.ui.recycler_view.RvData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class BotViewModel @Inject constructor(
    private val sP: SharedPrefProvider,
    private val botRepository: BotRepository,
    private val rP: ResourceProvider,
    private val botDataProvider: BotDataProvider,
    private val rvData: RvData
) : ViewModel() {

    private val _botInfo = MutableStateFlow<Pair<String, String?>>(rP.init)
    val botInfo get() = _botInfo.asStateFlow()

    fun check() = sP.read()

    fun id() = sP.readId()

    fun botInfo() = viewModelScope.launch {
        clearInfo(); botRepository.botInfo().collect { _botInfo.value = it }
    }

    fun clearInfo() {
        _botInfo.value = rP.init
    }



    val listMessages get() = botDataProvider.messagesFlow.asStateFlow()
}




