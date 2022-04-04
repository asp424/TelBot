package com.lm.bot.data

import android.content.SharedPreferences
import javax.inject.Inject


interface SharedPrefProvider {

    fun run()

    fun stop()

    fun read(): Boolean

    class Base @Inject constructor(private val sharedPreferences: SharedPreferences) :
        SharedPrefProvider {

        override fun run() = sharedPreferences.edit().putBoolean(ID, false).apply()

        override fun stop() = sharedPreferences.edit().putBoolean(ID, true).apply()

        override fun read() = sharedPreferences.getBoolean(ID, true)
    }

    companion object{
        const val ID = "id"
    }
}