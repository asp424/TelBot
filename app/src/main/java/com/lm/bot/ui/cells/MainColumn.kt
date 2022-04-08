package com.lm.bot.ui.cells

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.unit.dp
import com.lm.bot.domain.BotDataProvider
import com.lm.bot.presentation.BotViewModel
import com.lm.bot.ui.recycler_view.AdapterHandler
import com.lm.bot.ui.theme.Teal200

@Composable
inline fun MainColumn(
    vm: BotViewModel,
    butText: String,
    token: String,
    textFieldSize: Boolean,
    bI: State<Pair<String, String?>>,
    botInfoVis: Boolean,
    crossinline tokenV: (String) -> Unit,
    crossinline onClick: (String, Boolean) -> Unit,
    rP: BotDataProvider,
    adapterHandler: AdapterHandler
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Gray)
            .padding(bottom = 140.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        DataCard(textFieldSize, adapterHandler, vm)
        Box(modifier = Modifier.padding(top = animateDpAsState(if (!textFieldSize)
            0.dp else 60.dp).value)) {
            CustomTextField(
                placeholderText = "Token",
                token = token,
                res = { tokenV(it); rP.botToken = it },
                textFieldSize = textFieldSize,
                color = if (bI.value.first != "Wrong token" && bI.value.first.isNotEmpty()
                    && token.isNotEmpty() && token.length == 46
                ) Teal200
                else Black,
                tint = if (bI.value.first != "Wrong token" && bI.value.first.isNotEmpty()
                    && token.isNotEmpty() && token.length == 46
                ) Teal200
                else LocalContentColor.current.copy(alpha = 0.3f)
            )
        }
        InfoCard(bI, botInfoVis, vm, textFieldSize)
        CustomButton(butText, onClick = { bT, tFS ->
            onClick(bT, tFS);
        }, rP, bI, vm)
    }
}