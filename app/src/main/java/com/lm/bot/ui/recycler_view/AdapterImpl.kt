package com.lm.bot.ui.recycler_view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lm.bot.ui.cells.RvItem
import javax.inject.Inject

class AdapterImpl @Inject constructor(
    private val adapterHandler: AdapterHandler
) : RecyclerView.Adapter<MessageHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        adapterHandler.holder(parent)


    override fun onBindViewHolder(holder: MessageHolder, position: Int) =
        holder.item.setContent {
            RvItem(adapterHandler.list[position]) {
                adapterHandler.apply { position.deleteItem }
            }
        }

    override fun getItemCount() = adapterHandler.list.size
}