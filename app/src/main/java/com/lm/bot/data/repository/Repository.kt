package com.lm.bot.data.repository

import com.lm.bot.core.FJ
import com.lm.bot.core.Fj
import com.lm.bot.data.retrofit.Handler
import com.lm.bot.data.retrofit.RetrofitProvider
import javax.inject.Inject

interface Repository {

    fun memes(): FJ

    fun anime(): FJ

    fun joke(): Fj

    class Base @Inject constructor(
        private val handler: Handler,
        private val rP: RetrofitProvider
    ) : Repository {

        override fun memes() = handler.task(rP.memesApi.fetchMemes())

        override fun anime() = handler.task(rP.animeApi.fetchAnime())

        override fun joke() = handler.task(rP.jokeApi.fetchJokes())

    }
}