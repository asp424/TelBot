package com.lm.bot.ui.recycler_view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lm.bot.ui.cells.RvItem
import javax.inject.Inject

class Adapter @Inject constructor(
    val adapterHandler: AdapterHandler
) : RecyclerView.Adapter<MessageHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        adapterHandler.holder(parent)

    override fun onBindViewHolder(holder: MessageHolder, position: Int) =
        adapterHandler.item(holder.item, position, this)

    override fun getItemCount() = adapterHandler.list.size
}
