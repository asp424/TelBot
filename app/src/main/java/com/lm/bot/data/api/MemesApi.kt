package com.lm.bot.data.api

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MemesApi {
    @GET("get_memes")
    fun fetchMemes(): Call<JsonObject>
}
