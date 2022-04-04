package com.lm.bot.ui.cells

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Token
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextField(
    placeholderText: String = "",
    res: (String) -> Unit,
    textFieldSize: Boolean,
    token: String,
    color: Color,
    tint: Color
) {
    BasicTextField(
        value = token,
        onValueChange = { res(it) },
        singleLine = true,
        cursorBrush = SolidColor(MaterialTheme.colors.primary),
        textStyle = LocalTextStyle.current.copy(
            color = color,
            fontSize = 16.sp
        ),
        decorationBox = { innerTextField ->
            Row(
                Modifier
                    .background(
                        MaterialTheme.colors.surface,
                        RoundedCornerShape(percent = 20)
                    )
                    .padding(animateDpAsState(if (textFieldSize) 4.dp
                    else 0.dp).value)
                    .height(animateDpAsState(if (textFieldSize)
                        LocalConfiguration.current.screenHeightDp.dp / 18
                    else 0.dp
                    ).value)
                    .background(
                        MaterialTheme.colors.surface,
                        MaterialTheme.shapes.small,
                    )
                    .width(LocalConfiguration.current.screenWidthDp.dp - 110.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Filled.Token,
                    null,
                    tint = tint
                )
                Box(Modifier.weight(1f)) {
                    if (token.isEmpty()) Text(
                        placeholderText,
                        style = LocalTextStyle.current.copy(
                            color = MaterialTheme.colors.onSurface.copy(alpha = 0.3f),
                            fontSize = 16.sp
                        )
                    )
                    innerTextField()
                }
            }
        }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text,)

    )
}



