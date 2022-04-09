package com.lm.bot.ui.recycler_view

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import com.lm.bot.core.RA
import com.lm.bot.data.model.Message
import com.lm.bot.databinding.ItemBinding
import com.lm.bot.databinding.RecyclerViewBinding
import com.lm.bot.presentation.MainActivity
import com.lm.bot.ui.cells.RvItem
import javax.inject.Inject

class AdapterHandlerImpl @Inject constructor(private val context: Application) : AdapterHandler {

    override fun updateList(message: Message, adapter: RA){
        list.add(message); adapter.notifyC(list.size) }

    override fun deleteItem(position: Int, adapter: RA){
        list.removeAt(position); adapter.notifyD(position); adapter.notifyC(position) }

    override fun item(item: ComposeView, position: Int, adapter: RA) =
        item.setContent { RvItem(list[position]) { deleteItem(position, adapter) } }

    private fun RA.notifyC(position: Int) = notifyItemRangeChanged(position, list.size)

    private fun RA.notifyD(position: Int) = notifyItemRemoved(position)

    override var list = mutableListOf<Message>()

    override fun holder(parent: ViewGroup) =
        MessageHolder(ItemBinding.inflate(LayoutInflater.from(parent.context)).root)

}