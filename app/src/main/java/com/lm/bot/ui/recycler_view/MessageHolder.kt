package com.lm.bot.ui.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lm.bot.databinding.RvItemBinding
import javax.inject.Inject


class MessageHolder @Inject constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var item = RvItemBinding.bind(itemView).item
}