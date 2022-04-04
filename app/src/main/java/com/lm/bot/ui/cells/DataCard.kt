package com.lm.bot.ui.cells

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.lm.bot.data.model.Message

@Composable
fun DataCard(list: State<MutableList<Message>>, textFieldSize: Boolean) {
    val stateList = rememberScrollState()
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
            Column(modifier = Modifier.verticalScroll(stateList)) {
                list.value.forEach {
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