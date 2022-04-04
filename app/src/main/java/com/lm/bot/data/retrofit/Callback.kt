package com.lm.bot.data.retrofit

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Callback {
    inline fun <T> Call<T>.request(crossinline onResult: (response: ApiResponse<T>) -> Unit) {
        enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) onResult(ApiResponse.Success(response))
                else onResult(ApiResponse.Failure(response))
            }

            override fun onFailure(call: Call<T>, throwable: Throwable) {
                onResult(ApiResponse.Exception(throwable))
            }
        })
    }
}