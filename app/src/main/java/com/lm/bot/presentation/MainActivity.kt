package com.lm.bot.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import com.lm.bot.core.BaseActivity
import com.lm.bot.core.BotApp
import com.lm.bot.ui.screens.Main

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as BotApp).appComponent.injectAct(this)
        setContent { Main(vm, dP, adapter) }
    }
}
