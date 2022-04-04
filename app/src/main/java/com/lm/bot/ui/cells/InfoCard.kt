package com.lm.bot.ui.cells

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Card
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lm.bot.ui.theme.Teal200


@Composable
fun InfoCard(bI: State<Pair<String, String?>>, botInfoVis: Boolean) {
    var message by remember { mutableStateOf("") }
    Card(
        modifier = Modifier
            .size(240.dp, animateDpAsState(if (botInfoVis) 120.dp else 0.dp).value)
            .padding(top = 3.dp)
    ) {
        SelectionContainer {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                bI.value.first.also { res ->
                    CustomTextField(
                        placeholderText = "Message",
                        token = message,
                        res = { message = it },
                        textFieldSize = res != "Wrong token" && res.isNotEmpty(),
                        color = Black,
                        tint = LocalContentColor.current.copy(alpha = 0.3f)
                    )
                    Text(
                        text = res, textAlign = TextAlign.Center,
                        modifier = Modifier.padding(
                            top =
                            if (res == "Wrong token") 46.dp else 6.dp
                        ),
                        color = if (res == "Wrong token") Color.Red else Color.Black
                    )
                    Text(
                        text = bI.value.second!!, textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 3.dp),
                        color = if (res == "Wrong token") Color.Red else Color.Black
                    )
                }
            }
        }
    }
}