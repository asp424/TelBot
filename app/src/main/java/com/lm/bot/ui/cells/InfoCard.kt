package com.lm.bot.ui.cells

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.recyclerview.widget.RecyclerView
import com.lm.bot.presentation.BotViewModel


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun InfoCard(
    bI: State<Pair<String, String?>>,
    botInfoVis: Boolean,
    vm: BotViewModel,
    textFieldSize: Boolean
) {
    LocalSoftwareKeyboardController.current?.apply {
        Card(
            modifier = Modifier
                .size(240.dp, animateDpAsState(if (botInfoVis) if (!textFieldSize)
                    76.dp else 54.dp else 0.dp).value)
                .padding(top = 3.dp)
        ) {
            SelectionContainer {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    bI.value.first.also { res ->
                        Box {
                            Text(
                                text = res, textAlign = TextAlign.Center,
                                modifier = Modifier.padding(
                                    top =
                                    if (res == "Wrong token") 16.dp else 6.dp
                                ),
                                color = if (res == "Wrong token") Color.Red else Black
                            )
                            if (res == "") CircularProgressIndicator(modifier = Modifier.padding(top = 12.dp)
                                .size(20.dp)
                            )
                        }
                        Text(
                            text = bI.value.second!!, textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 3.dp),
                            color = if (res == "Wrong token") Color.Red else Black
                        )
                        Text(text = if (!textFieldSize) "Bot is running" else "", fontSize = 20.sp,
                        color = Green
                            )
                    }
                }
            }
        }
    }
}