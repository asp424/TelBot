package com.lm.bot.ui.cells

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.lm.bot.core.ResourceProvider
import com.lm.bot.domain.BotDataProvider
import com.lm.bot.presentation.BotViewModel
import com.lm.bot.presentation.MainActivity

@OptIn(ExperimentalComposeUiApi::class)
@Composable
inline fun CustomButton(
    butText: String,
    crossinline onClick: (String, Boolean) -> Unit,
    rP: BotDataProvider,
    bI: State<Pair<String, String?>>,
    vm: BotViewModel
) {
    LocalSoftwareKeyboardController.current?.apply {
        (LocalContext.current as MainActivity).also { cont ->
            Button(
                onClick = {
                    if (butText == "Start bot") {
                        rP.botToken.apply {
                            if (isNotEmpty() && length == 46) {
                                hide()
                                cont.startBot()
                                onClick("Stop bot", false)
                                toast(cont, "Bot started")
                            } else toast(cont, "Wrong token format")
                        }
                    } else {
                        hide()
                        cont.stopBot(); toast(cont, "Bot stopped")
                        onClick("Start bot", true)
                        vm.clearInfo()
                        vm.botInfo()
                    }
                },
                modifier = Modifier.padding(top = 10.dp).height(
                    animateDpAsState(
                        if (bI.value.first != "Wrong token" && bI.value.first.isNotEmpty()
                            && rP.botToken.isNotEmpty() && rP.botToken.length == 46
                        )
                            LocalConfiguration.current.screenHeightDp.dp / 18
                        else 0.dp
                    ).value
                ),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black
                )
            ) { Text(text = butText, color = Color.White) }
        }
    }
}

fun toast(context: Context, text: String) =
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()