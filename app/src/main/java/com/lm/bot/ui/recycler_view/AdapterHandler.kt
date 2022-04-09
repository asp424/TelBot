package com.lm.bot.ui.recycler_view

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.ComposeView
import androidx.recyclerview.widget.RecyclerView
import com.lm.bot.core.RA
import com.lm.bot.data.model.Message

interface AdapterHandler {

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(message: Message, adapter: RA)

    fun deleteItem(position: Int, adapter: RA)

    fun item(item: ComposeView, position: Int, adapter: RA)

    var list: MutableList<Message>

    fun holder(parent: ViewGroup): MessageHolder

}