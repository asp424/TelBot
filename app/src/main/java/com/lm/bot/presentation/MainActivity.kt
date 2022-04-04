package com.lm.bot.presentation

import android.app.ActivityManager
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.lm.bot.core.BotApp
import com.lm.bot.ui.screens.Main
import javax.inject.Inject


class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: BotViewModelFactory

    private val vm: BotViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as BotApp).appComponent.injectAct(this)
        setContent { Main(vm) }
    }
}
