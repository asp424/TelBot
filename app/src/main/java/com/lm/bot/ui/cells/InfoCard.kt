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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lm.bot.presentation.BotViewModel


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun InfoCard(
    bI: State<Pair<String, String?>>,
    botInfoVis: Boolean,
    vm: BotViewModel
) {
    var id by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    LocalSoftwareKeyboardController.current?.apply {
        Card(
            modifier = Modifier
                .size(240.dp, animateDpAsState(if (botInfoVis) 200.dp else 0.dp).value)
                .padding(top = 3.dp)
        ) {
            SelectionContainer {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    bI.value.first.also { res ->
                        CustomTextField(
                            placeholderText = "ChatId",
                            token = id,
                            res = { id = it },
                            textFieldSize = res != "Wrong token" && res.isNotEmpty(),
                            color = Black,
                            tint = LocalContentColor.current.copy(alpha = 0.3f)
                        )
                        CustomTextField(
                            placeholderText = "Message",
                            token = message,
                            res = { message = it },
                            textFieldSize = res != "Wrong token" && res.isNotEmpty(),
                            color = Black,
                            tint = LocalContentColor.current.copy(alpha = 0.3f)
                        )
                        Button(
                            onClick = {
                                if (message.isNotEmpty() && id.isNotEmpty())
                                    vm.sendMessage(id.toLong(), message)
                                message = ""
                                hide()
                            },
                            modifier = Modifier
                                .padding(top = 3.dp)
                                .height(
                                    animateDpAsState(
                                        if (res != "Wrong token" && res.isNotEmpty())
                                            LocalConfiguration.current.screenHeightDp.dp / 18
                                        else 0.dp
                                    ).value
                                ),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Black
                            )
                        ) { Text(text = "Send message", color = Color.White) }
                        Box {
                            Text(
                                text = res, textAlign = TextAlign.Center,
                                modifier = Modifier.padding(
                                    top =
                                    if (res == "Wrong token") 76.dp else 6.dp
                                ),
                                color = if (res == "Wrong token") Color.Red else Black
                            )
                            if (res == "") CircularProgressIndicator(modifier = Modifier.padding(top = 70.dp))
                        }
                        Text(
                            text = bI.value.second!!, textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 3.dp),
                            color = if (res == "Wrong token") Color.Red else Black
                        )
                    }
                }
            }
        }
    }
}