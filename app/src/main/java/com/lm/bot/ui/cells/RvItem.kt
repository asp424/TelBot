package com.lm.bot.ui.cells

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lm.bot.data.model.Message

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RvItem(item: Message, onClick: () -> Unit) {
    item.apply {
        Text(
            text = "$firstName($id): $mess", textAlign = TextAlign.Center,
            modifier = Modifier.padding(4.dp).clickable { onClick() }
        )
    }
}
