package com.example.moviesapp.utils

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */


data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String?
) {
    companion object {
        @JvmStatic
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        @JvmStatic
        fun <T> error(msg: String, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        @JvmStatic
        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}
