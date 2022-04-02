package com.lm.bot.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Token
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lm.bot.data.bot.Repository.Companion.botToken
import com.lm.bot.data.bot.message
import com.lm.bot.di.MainDep.depends
import com.lm.bot.ui.cells.CustomTextField


@Composable
fun Main() {
    depends.apply {
        botViewModel.also { vm ->
            message.collectAsState().value.also {list ->
                LocalContext.current.also { cont ->
                    var token by remember { mutableStateOf("") }
                    var butText by remember {
                        mutableStateOf(if (vm.check()) "Start bot" else "Stop bot")
                    }
                    var textFieldSize by remember { mutableStateOf(vm.check()) }
                    var logFieldSize by remember { mutableStateOf(!vm.check()) }
                    Column(
                        Modifier
                            .fillMaxSize()
                            .background(Gray),
                        horizontalAlignment = CenterHorizontally,
                        verticalArrangement = Center
                    ) {
                        CustomTextField(
                            leadingIcon = {
                                Icon(
                                    Icons.Filled.Token,
                                    null,
                                    tint = LocalContentColor.current.copy(alpha = 0.3f)
                                )
                            },
                            trailingIcon = null,
                            fontSize = 16.sp,
                            placeholderText = "Token",
                            modifier = Modifier,
                            keyboardOptions = KeyboardType.Text,
                            token = token,
                            res = { token = it; botToken = it },
                            textFieldSize = textFieldSize
                        )

                        Button(
                            onClick = {
                                if (butText == "Start bot") {
                                    botToken.apply {
                                        if (isNotEmpty() && length == 46) {
                                            vm.startBot(cont); token = ""; butText = "Stop bot"
                                            toast(cont, "Bot started"); textFieldSize = false
                                            logFieldSize = true
                                        } else toast(cont, "Wrong token format")
                                    }
                                } else {
                                    vm.stopBot(cont); toast(cont, "Bot stopped")
                                    textFieldSize = true; butText = "Start bot"; logFieldSize =
                                        false

                                }
                            },
                            modifier = Modifier.padding(top = 10.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Black
                            )
                        ) { Text(text = butText, color = White) }
                    }
                    Column(
                        Modifier
                            .background(Gray),
                        horizontalAlignment = CenterHorizontally,
                        verticalArrangement = Center
                    ) {
                        Card(
                            modifier = Modifier
                                .size(
                                    LocalConfiguration.current.screenWidthDp.dp,
                                    animateDpAsState(
                                        if (logFieldSize) LocalConfiguration.current.screenHeightDp.dp / 3
                                        else 0.dp
                                    ).value
                                )
                                .padding(10.dp)
                        ) {
                            Column {
                                list.forEach {
                                    it.apply {
                                        Text(
                                            text = "$firstName($id): $mess",
                                            modifier = Modifier.padding(start = 6.dp, top = 4.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun toast(context: Context, text: String) = 
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
