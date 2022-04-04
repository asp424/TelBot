package com.lm.bot.data.repository

import com.google.gson.JsonObject
import com.lm.bot.data.model.Joke
import com.lm.bot.data.retrofit.ApiResponse
import com.lm.bot.data.retrofit.Handler
import com.lm.bot.data.retrofit.RetrofitProvider
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface Repository {

    fun memes(): Flow<ApiResponse<JsonObject>>

    fun anime(): Flow<ApiResponse<JsonObject>>

    fun joke(): Flow<ApiResponse<Joke>>

    class Base @Inject constructor(
        private val handler: Handler,
        private val rP: RetrofitProvider
    ) : Repository {

        override fun memes() = handler.task(rP.memesApi.fetchMemes())

        override fun anime() = handler.task(rP.animeApi.fetchAnime())

        override fun joke() = handler.task(rP.jokeApi.fetchJokes())

    }
}