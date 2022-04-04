package com.lm.bot.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.kotlintelegrambot.network.ResponseError
import com.lm.bot.core.ResourceProvider
import com.lm.bot.data.shared_pref.SharedPrefProvider
import com.lm.bot.domain.BotDataProvider
import com.lm.bot.domain.BotRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class BotViewModel @Inject constructor(
    private val sP: SharedPrefProvider,
    private val botRepository: BotRepository,
    private val rP: ResourceProvider,
    private val botDataProvider: BotDataProvider
) : ViewModel() {

    private val _botInfo = MutableStateFlow<Pair<String, String?>>(rP.init)
    val botInfo get() = _botInfo.asStateFlow()

    fun check() = sP.read()

    fun botInfo() = viewModelScope.launch {
        clearInfo()
        botRepository.botInfo().collect { _botInfo.value = it }
    }

    fun clearInfo() {
        _botInfo.value = rP.init
    }

    fun sendMessage(id: Long, text: String) = viewModelScope.launch {
        botRepository.sendMessage(id, text).collect {
            when (it) {
                is Response<*> -> Log.d("My", it.toString())
                is ResponseError -> Log.d("My", it.toString())
            }
        }
    }

    val listMessages get() = botDataProvider.messagesFlow.asStateFlow()
}




