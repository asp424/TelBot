package com.lm.bot.data.api

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Callback {
    inline fun <T> Call<T>.request(crossinline onResult: (response: APIResponse<T>) -> Unit) {
        enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) onResult(APIResponse.Success(response))
                else onResult(APIResponse.Failure(response))
            }

            override fun onFailure(call: Call<T>, throwable: Throwable) {
                onResult(APIResponse.Exception(throwable))
            }
        })
    }
}