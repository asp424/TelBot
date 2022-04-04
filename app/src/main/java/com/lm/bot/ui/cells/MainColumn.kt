package com.lm.bot.ui.cells

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.unit.dp
import com.lm.bot.core.ResourceProvider
import com.lm.bot.presentation.BotViewModel
import com.lm.bot.ui.theme.Teal200

@Composable
fun MainColumn(
    vm: BotViewModel, butText: String,
    token: String, textFieldSize: Boolean,
    bI: State<Pair<String, String?>>,
    botInfoVis: Boolean,
    tokenV: (String) -> Unit, onClick: (String, Boolean) -> Unit, rP: ResourceProvider
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Gray).padding(bottom = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
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
        InfoCard(bI, botInfoVis)
        CustomButton(vm, butText, onClick = { bT, tFS ->
            onClick(bT, tFS); tokenV("")
        }, rP)
    }
}