package com.lm.bot.ui.cells

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.recyclerview.widget.RecyclerView
import com.lm.bot.databinding.RecyclerViewBinding
import com.lm.bot.presentation.BotViewModel
import com.lm.bot.presentation.MainActivity
import com.lm.bot.ui.recycler_view.AdapterImpl

@Composable
fun DataCard(
    textFieldSize: Boolean,
    adapter: AdapterImpl,
    vm: BotViewModel
) {
    var rvv: RecyclerView? by remember {
        mutableStateOf(null)
    }
    //val stateList = rememberScrollState()

    vm.message.collectAsState().value.also { l ->
        LaunchedEffect(l) {
            if (l.mess.isNotEmpty())
                adapter.adapterHandler.updateList(l, adapter)
        }
    }
    Column(
        Modifier
            .background(Color.Gray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier
                .size(
                    LocalConfiguration.current.screenWidthDp.dp,
                    animateDpAsState(
                        if (!textFieldSize) LocalConfiguration.current.screenHeightDp.dp / 3
                        else 0.dp
                    ).value
                )
                .padding(10.dp)
        ) {
            Log.d("My", (LocalConfiguration.current.screenHeightDp.dp / 3).toString())

            SelectionContainer {
                AndroidView(factory = {
                    RecyclerViewBinding.inflate((it as MainActivity).layoutInflater)
                        .apply { rv.adapter = adapter }.root
                })

                /* Column(modifier = Modifier.verticalScroll(stateList)) {
                    vm.listMessages.collectAsState().value.forEach {
                       it.apply {
                           Text(
                               text = "$firstName($id): $mess",
                               modifier = Modifier.padding(
                                   start = 6.dp,
                                   top = 4.dp
                               )
                           )
                       }
                   }
               } */
            }
        }
    }
}