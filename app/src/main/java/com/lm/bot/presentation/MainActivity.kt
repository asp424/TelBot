package com.lm.bot.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import com.lm.bot.core.BaseActivity
import com.lm.bot.core.BotApp
import com.lm.bot.data.model.Message
import com.lm.bot.databinding.ActivityMainBinding
import com.lm.bot.ui.screens.Main
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as BotApp).appComponent.injectAct(this)
       // setContentView(ActivityMainBinding.inflate(layoutInflater).apply { rv.adapter = adapter }
        //    .root)
        setContent { Main(vm, dP) }
    }
}
