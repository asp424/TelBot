package com.lm.bot.presentation

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lm.bot.data.BotService
import com.lm.bot.data.BotService.Companion.messagesFlow
import com.lm.bot.data.SharedPrefProvider
import com.lm.bot.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class BotViewModel @Inject constructor(
    private val intent: Intent,
    private val sP: SharedPrefProvider,
    private val botRepository: BotService,
    private val repository: Repository

) : ViewModel() {

    private val _botInfo = MutableStateFlow<Pair<String, String?>>(Pair("", ""))
    val botInfo get() = _botInfo.asStateFlow()

    fun startBot(context: Context) = with(context.applicationContext) { startForegroundService(intent); sP.run() }

    fun stopBot(context: Context) = with(context.applicationContext) { stopService(intent); sP.stop() }

    fun check() = sP.read()

    fun botInfo() = viewModelScope.launch {
        _botInfo.value = Pair("", "")
    //    botRepository.botInfo()?.collect {
    //        _botInfo.value = it }
    //
        }
    val listMessages get() = messagesFlow.asStateFlow()
}



