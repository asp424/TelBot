package com.lm.bot.data.retrofit


import com.lm.bot.data.retrofit.Callback.request
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call
import javax.inject.Inject

interface Handler {

    fun <T> task(call: Call<T>): Flow<ApiResponse<T>>

    class Base @Inject constructor() : Handler {
        override fun <T> task(call: Call<T>) = callbackFlow { call.request { trySendBlocking(it) }
            awaitClose() }.flowOn(IO)
    }
}