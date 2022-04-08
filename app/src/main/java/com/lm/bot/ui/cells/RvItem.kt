package com.lm.bot.ui.cells

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lm.bot.data.model.Message

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RvItem(item: Message, onClick: () -> Unit) {
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
        ) {
        Card(
            onClick = { onClick() },
            border = BorderStroke(1.dp, color = Color.Black),
            modifier = Modifier.padding(start = 10.dp, top = 6.dp)
        ) {
            Column(Modifier.padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = item.firstName, textAlign = TextAlign.Center)
                Text(text = item.mess, textAlign = TextAlign.Center)
            }
        }
    }
}