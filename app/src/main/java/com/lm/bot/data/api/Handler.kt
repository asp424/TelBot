package com.lm.bot.data.api

import com.google.gson.JsonObject
import com.lm.retrofit.data.api.APIResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

interface Handler {

    fun task(call: Call<JsonObject>): Flow<APIResponse<JsonObject>>

    class Base @Inject constructor(): Handler {

        override fun task(call: Call<JsonObject>) =
            callbackFlow {
                call.request { trySendBlocking(it) }
                awaitClose()
            }.flowOn(Dispatchers.IO)

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
}