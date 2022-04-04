package com.lm.bot.data.shared_pref

import android.content.SharedPreferences
import com.lm.bot.core.ResourceProvider
import javax.inject.Inject


interface SharedPrefProvider {

    fun run()

    fun stop()

    fun saveId(id: String)

    fun readId(): String

    fun read(): Boolean

    class Base @Inject constructor(
        private val sharedPreferences: SharedPreferences,
        private val rP: ResourceProvider
    ) :
        SharedPrefProvider {

        override fun run() = sharedPreferences.edit().putBoolean(rP.key, false).apply()

        override fun stop() = sharedPreferences.edit().putBoolean(rP.key, true).apply()

        override fun saveId(id: String) = sharedPreferences.edit().putString("id", id).apply()

        override fun readId() = sharedPreferences.getString("id", "").toString()

        override fun read() = sharedPreferences.getBoolean(rP.key, true)
    }
}