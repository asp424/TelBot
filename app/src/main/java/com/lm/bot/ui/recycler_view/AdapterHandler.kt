package com.lm.bot.ui.recycler_view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lm.bot.data.model.Message
import com.lm.bot.databinding.RvItemBinding

interface AdapterHandler {

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: MutableList<Message>): MutableList<Message>

    val Int.deleteItem: Unit

    var list: MutableList<Message>

    fun holder(parent: ViewGroup): MessageHolder

}