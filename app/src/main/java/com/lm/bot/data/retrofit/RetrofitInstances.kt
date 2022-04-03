package com.lm.bot.data.retrofit

import com.lm.bot.data.api.MemesApi
import com.lm.bot.data.api.AnimeApi
import com.lm.bot.data.api.JokeApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstances {

    val memesApi by lazy {
        Retrofit.Builder()
            .baseUrl(MEMES_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(MemesApi::class.java)
    }

    val animeApi by lazy {
        Retrofit.Builder()
            .baseUrl(ANIME_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(AnimeApi::class.java)
    }

    val jokeApi by lazy {
        Retrofit.Builder()
            .baseUrl(ANIME_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(JokeApi::class.java)
    }

    private const val MEMES_URL = "https://api.imgflip.com"
    private const val ANIME_URL = "https://api.jikan.moe/"
    private const val JOKE_URL = "https://v2.jokeapi.dev/joke/"

}




