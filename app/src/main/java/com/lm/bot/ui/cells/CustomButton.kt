package com.lm.bot.ui.cells

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.lm.bot.domain.BotInteraction
import com.lm.bot.presentation.BotViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CustomButton(vm: BotViewModel, butText: String, onClick: (String, Boolean) -> Unit) {
    LocalSoftwareKeyboardController.current?.apply {
        LocalContext.current.also { cont ->
            Button(
                onClick = {
                    if (butText == "Start bot") {
                        BotInteraction.Base.botToken.apply {
                            if (isNotEmpty() && length == 46) {
                                hide()
                                vm.startBot(cont)
                                onClick("Stop bot", false)
                                toast(cont, "Bot started")
                            } else toast(cont, "Wrong token format")
                        }
                    } else {
                        hide()
                        vm.stopBot(cont); toast(cont, "Bot stopped")
                        onClick("Start bot", true)
                    }
                },
                modifier = Modifier.padding(top = 10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black
                )
            ) { Text(text = butText, color = Color.White) }
        }
    }
}

private fun toast(context: Context, text: String) =
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()