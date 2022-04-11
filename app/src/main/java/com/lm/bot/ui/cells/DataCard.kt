package com.lm.bot.ui.cells

import android.annotation.SuppressLint
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
import com.lm.bot.presentation.BotViewModel
import com.lm.bot.ui.recycler_view.Adapter

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun DataCard(
    textFieldSize: Boolean,
    rv: RecyclerView,
    vm: BotViewModel
) {
    //val stateList = rememberScrollState()

    vm.messageWork.collectAsState().value.also { l ->
        LaunchedEffect(l) {
            if (l.mess.isNotEmpty())
                (rv.adapter as Adapter).apply { adapterMethods.updateList(l, this) }
        }
    }
    Column(
        Modifier
            .background(Color.Gray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

            LaunchedEffect(key1 = true, block = {
                Log.d("My", this.coroutineContext.toString())

            })
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
                AndroidView(factory = { rv })

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