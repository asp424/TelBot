package com.lm.bot.ui.cells

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun InfoCard(bI: State<Pair<String, String?>>, botInfoVis: Boolean) {
    Card(
        modifier = Modifier.size(240.dp, animateDpAsState(if (botInfoVis) 60.dp else 0.dp).value)
            .padding(top = 3.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = bI.value.first, textAlign = TextAlign.Center,
                modifier = Modifier.padding(
                    top =
                    if (bI.value.first == "Wrong token") 16.dp else 6.dp
                ),
                color = if (bI.value.first == "Wrong token") Color.Red else Color.Black
            )
            Text(
                text = bI.value.second!!, textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 3.dp),
                color = if (bI.value.first == "Wrong token") Color.Red else Color.Black
            )
        }
    }
}