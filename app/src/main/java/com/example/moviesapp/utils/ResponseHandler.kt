package com.example.moviesapp.utils

import com.google.gson.Gson
import retrofit2.HttpException
import java.net.SocketTimeoutException

open class ResponseHandler {

    fun <T : Any> handleSuccess(data: T): Resource<T> {
        return Resource.success(data)
    }

    fun <T : Any> handleException(e: Exception): Resource<T> {
        return when (e) {
            is HttpException -> Resource.error(handleHttpException(e), null)
            is SocketTimeoutException -> Resource.error(getErrorMessage(ErrorCodes.SocketTimeOut.code), null)
            else -> Resource.error(getErrorMessage(Int.MAX_VALUE), null)
        }
    }

    private fun handleHttpException(exception: HttpException): String {
        return try {
            val reader = exception.response()?.errorBody()?.charStream()
            val errorBody = Gson().fromJson(reader, ErrorBean::class.java)
            errorBody?.message ?: "Unknown HTTP error"
        } catch (e: Exception) {
            "Something went wrong: ${e.localizedMessage}"
        }
    }

    private fun getErrorMessage(code: Int): String {
        return when (code) {
            ErrorCodes.SocketTimeOut.code -> "Timeout"
            401 -> "Unauthorized Access"
            404 -> "Not Found"
            else -> "Something went wrong"
        }
    }

    data class ErrorBean(
        val message: String
    )
}