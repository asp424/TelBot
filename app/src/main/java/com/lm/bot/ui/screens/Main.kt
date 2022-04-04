package com.lm.bot.ui.screens

import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import com.lm.bot.domain.BotDataProvider
import com.lm.bot.presentation.BotViewModel
import com.lm.bot.ui.cells.DataCard
import com.lm.bot.ui.cells.MainColumn


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Main(vm: BotViewModel, rP: BotDataProvider) {

    var token by remember { mutableStateOf("") }

    var botInfoVis by remember { mutableStateOf(false) }

    LaunchedEffect(token) {
        if (token.isNotEmpty() && token.length == 46) {
            rP.botToken = token; vm.botInfo(); botInfoVis = true
        } else botInfoVis = false
    }

    var butText by remember { mutableStateOf(if (vm.check()) "Start bot" else "Stop bot") }

    var textFieldSize by remember { mutableStateOf(vm.check()) }

    MainColumn(vm, butText, token, textFieldSize, vm.botInfo.collectAsState(), botInfoVis,
        { token = it }, { bT, tFS -> butText = bT; textFieldSize = tFS }, rP
    )

    DataCard(vm.listMessages.collectAsState(), textFieldSize)
}


