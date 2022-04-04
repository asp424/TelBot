package com.lm.bot.data.api

import com.lm.bot.data.model.Joke
import retrofit2.Call
import retrofit2.http.GET

interface JokeApi {
    @GET("Any?safe-mode")
    fun fetchJokes(): Call<Joke>
}