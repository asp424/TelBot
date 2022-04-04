package com.lm.bot.data.retrofit

import retrofit2.Response

sealed class ApiResponse<out T>{

    class Success<T>(response: Response<T>): ApiResponse<T>() {
        val data = response.body()
    }

    class Failure<T>(response: Response<T>): ApiResponse<T>() {
        val message = response.errorBody().toString()
    }

    class Exception<T>(throwable: Throwable): ApiResponse<T>() {
        val message: String? = throwable.localizedMessage
    }

    object Loading: ApiResponse<Nothing>()
}
