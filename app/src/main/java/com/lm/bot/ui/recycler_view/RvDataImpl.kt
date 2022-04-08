package com.lm.bot.ui.recycler_view

import com.lm.bot.data.model.Message
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject


class RvDataImpl @Inject constructor() : RvData {
    override var message: MutableStateFlow<Message> = MutableStateFlow(Message())

}