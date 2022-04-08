package com.lm.bot.ui.recycler_view

import android.annotation.SuppressLint
import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lm.bot.data.model.Message
import com.lm.bot.ui.cells.RvItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AdapterImpl @Inject constructor(private val rvData: RvData) :
    RecyclerView.Adapter<MessageHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageHolder =
        holder(parent)

    @SuppressLint("StateFlowValueCalledInComposition")
    override fun onBindViewHolder(holder: MessageHolder, position: Int) {
        holder.item.setContent {
            RvItem(list[position]) {
                // rvData.list.value.removeAt(position)
                // notifyItemRemoved(position)
                notifyItemRangeChanged(position, list.size)
            }
        }
    }

    override fun getItemCount(): Int = list.size

    private val list = mutableListOf<Message>()

}