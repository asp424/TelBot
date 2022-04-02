package com.lm.bot.di

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import com.lm.bot.MainActivity
import com.lm.bot.core.SharedPrefProvider
import com.lm.bot.data.bot.Repository
import com.lm.bot.ui.viewmodels.BotViewModel
import com.lm.bot.ui.viewmodels.factorys.BotViewModelFactory

data class Main(
    val botViewModel: BotViewModel
)

val LocalMainDependencies = staticCompositionLocalOf<Main> { error("No value provided") }

object MainDep {
    val depends: Main @Composable get() = LocalMainDependencies.current
}

@Composable
fun MainDependencies(content: @Composable () -> Unit) {
    (LocalContext.current).also { con ->
        Repository.Base().also { repo ->
            CompositionLocalProvider(
                LocalMainDependencies provides Main(
                    botViewModel = ViewModelProvider(
                        con as MainActivity,
                        BotViewModelFactory(
                            Intent(con, repo::class.java),
                            SharedPrefProvider.Base(
                                con.getSharedPreferences("id", MODE_PRIVATE)
                            ), repo
                        )
                    )[BotViewModel::class.java]
                ), content = content
            )
        }
    }
}







