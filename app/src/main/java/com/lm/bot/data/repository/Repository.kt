package com.lm.bot.data.repository

import com.google.gson.JsonObject
import com.lm.bot.data.api.Handler
import com.lm.bot.data.api.MemesApi
import com.lm.retrofit.data.api.APIResponse
import com.lm.bot.data.api.AnimeApi
import com.lm.bot.data.api.JokeApi
import com.lm.bot.data.retrofit.RetrofitInstances.animeApi
import com.lm.bot.data.retrofit.RetrofitInstances.jokeApi
import com.lm.bot.data.retrofit.RetrofitInstances.memesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface Repository {

    fun memes(): Flow<APIResponse<JsonObject>>

    fun anime(): Flow<APIResponse<JsonObject>>

    fun joke(): Flow<APIResponse<JsonObject>>

    class Base @Inject constructor(
        private val handler: Handler
    ) : Repository {

        override fun memes() = handler.task(memesApi.fetchMemes())

        override fun anime() = handler.task(animeApi.fetchAnime())

        override fun joke() = handler.task(jokeApi.fetchJokes())

    }
}