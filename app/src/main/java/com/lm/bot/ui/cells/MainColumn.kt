package com.lm.bot.ui.cells

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.lm.bot.domain.BotInteraction
import com.lm.bot.presentation.BotViewModel

@Composable
fun MainColumn(
    vm: BotViewModel, butText: String,
    token: String, textFieldSize: Boolean,
    bI: State<Pair<String, String?>>,
    botInfoVis: Boolean,
    tokenV:(String) -> Unit, onClick: (String, Boolean) -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Gray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CustomTextField(
            placeholderText = "Token",
            token = token,
            res = { tokenV(it); BotInteraction.Base.botToken = it },
            textFieldSize = textFieldSize
        )
        InfoCard(bI, botInfoVis)
        CustomButton(vm, butText, onClick = { bT, tFS ->
            onClick(bT, tFS); tokenV("")
        })
    }
}