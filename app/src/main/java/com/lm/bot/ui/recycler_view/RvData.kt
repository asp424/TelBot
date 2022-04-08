package com.lm.bot.ui.recycler_view

import com.lm.bot.data.model.Message
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface RvData {

    var message: MutableStateFlow<Message>

}