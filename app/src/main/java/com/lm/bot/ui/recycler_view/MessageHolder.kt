package com.lm.bot.ui.recycler_view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lm.bot.databinding.ItemBinding
import javax.inject.Inject


class MessageHolder @Inject constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var item = ItemBinding.bind(itemView).root
}