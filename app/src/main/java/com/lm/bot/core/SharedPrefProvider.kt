package com.lm.bot.core

import android.content.SharedPreferences


interface SharedPrefProvider {

    fun run()

    fun stop()

    fun read(): Boolean

    class Base (private val sharedPreferences: SharedPreferences) :
        SharedPrefProvider {

        override fun run() = sharedPreferences.edit().putBoolean("id", false).apply()

        override fun stop() = sharedPreferences.edit().putBoolean("id", true).apply()

        override fun read() = sharedPreferences.getBoolean("id", true)
    }
}