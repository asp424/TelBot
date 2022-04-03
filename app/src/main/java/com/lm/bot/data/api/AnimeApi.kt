package com.lm.bot.data.api

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET


interface AnimeApi {
    @GET("v3/search/anime?q=naruto")
    fun fetchAnime(): Call<JsonObject>
}
