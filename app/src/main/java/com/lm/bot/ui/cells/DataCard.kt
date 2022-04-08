package com.lm.bot.ui.cells

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.recyclerview.widget.RecyclerView
import com.lm.bot.data.model.Message
import com.lm.bot.databinding.ActivityMainBinding
import com.lm.bot.presentation.BotViewModel
import com.lm.bot.presentation.MainActivity
import com.lm.bot.ui.recycler_view.AdapterHandler
import com.lm.bot.ui.recycler_view.AdapterImpl
import com.lm.bot.ui.recycler_view.MessageHolder

@Composable
fun DataCard(
    textFieldSize: Boolean,
    adapterHandler: AdapterHandler,
    vm: BotViewModel
) {
    val stateList = rememberScrollState()

    //LaunchedEffect(list){
       // adapterHandler.updateList(list)
   // }
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
            SelectionContainer {
              //  AndroidView(factory = { ActivityMainBinding.inflate((it as MainActivity).layoutInflater)
                    //.apply { rv.adapter = adapterHandler.adapterImpl }.root })
                 Column(modifier = Modifier.verticalScroll(stateList)) {
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
                }
            }
        }
    }
}