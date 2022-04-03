package com.lm.bot.data

import android.content.SharedPreferences
import javax.inject.Inject


interface SharedPrefProvider {

    fun run()

    fun stop()

    fun read(): Boolean

    class Base @Inject constructor(private val sharedPreferences: SharedPreferences) :
        SharedPrefProvider {

        override fun run() = sharedPreferences.edit().putBoolean("id", false).apply()

        override fun stop() = sharedPreferences.edit().putBoolean("id", true).apply()

        override fun read() = sharedPreferences.getBoolean("id", true)
    }
}