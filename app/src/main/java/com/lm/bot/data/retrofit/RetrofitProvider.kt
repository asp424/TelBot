package com.lm.bot.data.retrofit

import com.lm.bot.core.ResourceProvider
import com.lm.bot.data.api.AnimeApi
import com.lm.bot.data.api.JokeApi
import com.lm.bot.data.api.MemesApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

interface RetrofitProvider {

    val memesApi: MemesApi

    val animeApi: AnimeApi

    val jokeApi: JokeApi

    class Base @Inject constructor(
        private val rP: ResourceProvider
    ) : RetrofitProvider {

        override val memesApi by lazy {
            Retrofit.Builder()
                .baseUrl(rP.memesUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(MemesApi::class.java)
        }

        override val animeApi by lazy {
            Retrofit.Builder()
                .baseUrl(rP.animeUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(AnimeApi::class.java)
        }

        override val jokeApi by lazy {
            Retrofit.Builder()
                .baseUrl(rP.jokeUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(JokeApi::class.java)
        }
    }
}




