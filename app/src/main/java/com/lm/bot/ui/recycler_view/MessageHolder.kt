package com.lm.bot.ui.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lm.bot.databinding.RvItemBinding


fun holder(parent: ViewGroup) =
    MessageHolder(RvItemBinding.inflate(LayoutInflater.from(parent.context)).root)

class MessageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var item = RvItemBinding.bind(itemView).item
}