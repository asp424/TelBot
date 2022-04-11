package com.lm.bot.ui.recycler_view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject

class Adapter @Inject constructor(
    val adapterMethods: AdapterMethods
) : RecyclerView.Adapter<MessageHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        adapterMethods.holder(parent)

    override fun onBindViewHolder(holder: MessageHolder, position: Int) =
        adapterMethods.item(holder.item, position, this)

    override fun getItemCount() = adapterMethods.list.size
}
