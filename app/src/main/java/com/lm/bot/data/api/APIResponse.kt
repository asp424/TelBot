package com.lm.retrofit.data.api

import retrofit2.Response

sealed class APIResponse<out T>{

    class Success<T>(response: Response<T>): APIResponse<T>() {
        val data = response.body()
    }

    class Failure<T>(response: Response<T>): APIResponse<T>() {
        val message = response.errorBody().toString()
    }

    class Exception<T>(throwable: Throwable): APIResponse<T>() {
        val message: String? = throwable.localizedMessage
    }

    object Loading: APIResponse<Nothing>()
}
