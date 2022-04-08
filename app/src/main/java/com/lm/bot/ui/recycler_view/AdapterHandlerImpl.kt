package com.lm.bot.ui.recycler_view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.lm.bot.data.model.Message
import com.lm.bot.databinding.RvItemBinding
import javax.inject.Inject

class AdapterHandlerImpl @Inject constructor() : AdapterHandler {

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList(newList: MutableList<Message>) = list.apply {
        clear()
        list = newList
       // adapter.notifyDataSetChanged()
    }

    override val Int.deleteItem
        get() = run {

            //    list.removeAt(this); a.notifyItemRemoved(this)
            //      a.notifyItemRangeChanged(this, list.size)
        }



    override var list = mutableListOf<Message>()

    override fun holder(parent: ViewGroup) =
        MessageHolder(RvItemBinding.inflate(LayoutInflater.from(parent.context)).root)

}