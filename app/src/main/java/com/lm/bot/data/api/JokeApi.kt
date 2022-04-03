package com.lm.bot.data.api

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET

interface JokeApi {
    @GET("Any?safe-mode")
    fun fetchJokes(): Call<JsonObject>
}