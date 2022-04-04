package com.lm.bot.data.api


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call
import javax.inject.Inject

interface Handler {

    fun <T> task(call: Call<T>): Flow<APIResponse<T>>

    class Base @Inject constructor() : Handler {

        override fun <T> task(call: Call<T>): Flow<APIResponse<T>> =
            callbackFlow {
                Callback.apply { call.request { trySendBlocking(it) } }
                awaitClose()
            }.flowOn(Dispatchers.IO)
    }
}