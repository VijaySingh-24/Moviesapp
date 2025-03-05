package com.example.moviesapp.utils

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View


import com.google.gson.Gson

import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException

object ErrorUtil {
    val TAG = ErrorUtil::class.simpleName
    var message:String=""
    fun handlerGeneralError(context: Context?, view: View?, throwable: Throwable) {
        Log.e(TAG, "Error: ${throwable.message}")
        throwable.printStackTrace()
        if (context == null) return

        when (throwable) {
            is HttpException -> {
                try {
                    when (throwable.code()) {
                        400 -> {
                            val body = (throwable as HttpException).response()!!.errorBody()
                            val gson = Gson()
                            val adapter = gson.getAdapter<ResponseHandler.ErrorBean>(ResponseHandler.ErrorBean::class.java!!)
                            try {
                                val errorParser = adapter.fromJson(body!!.string())
                                message=errorParser.message
                                SnackbarUtils.displayError(view, message)
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }

                        }

                        401 -> {
                            val body = (throwable as HttpException).response()!!.errorBody()
                            val gson = Gson()
                            val adapter = gson.getAdapter<ResponseHandler.ErrorBean>(ResponseHandler.ErrorBean::class.java!!)
                            try {
                                val errorParser = adapter.fromJson(body!!.string())
                                message=errorParser.message
                                SnackbarUtils.displayError(view, message)
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                            //SnackbarUtils.displayError(view, message)
                            //logout(context)
                        }


                        403 -> {
                            val body = (throwable as HttpException).response()!!.errorBody()
                            val gson = Gson()
                            val adapter = gson.getAdapter<ResponseHandler.ErrorBean>(ResponseHandler.ErrorBean::class.java!!)
                            try {
                                val errorParser = adapter.fromJson(body!!.string())
                                message=errorParser.message
                                SnackbarUtils.displayError(view, message)
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                          //  SnackbarUtils.displayError(view, message)
                        }

                        404 -> {
                            val body = (throwable as HttpException).response()!!.errorBody()
                            val gson = Gson()
                            val adapter = gson.getAdapter<ResponseHandler.ErrorBean>(ResponseHandler.ErrorBean::class.java!!)
                            try {
                                val errorParser = adapter.fromJson(body!!.string())
                                message=errorParser.message
                                SnackbarUtils.displayError(view, message)
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                          //  SnackbarUtils.displayError(view, message)
                        }
                        else -> {
                            val body = (throwable as HttpException).response()!!.errorBody()
                            val gson = Gson()
                            val adapter = gson.getAdapter<ResponseHandler.ErrorBean>(ResponseHandler.ErrorBean::class.java!!)
                            try {
                                val errorParser = adapter.fromJson(body!!.string())
                                message=errorParser.message
                                SnackbarUtils.displayError(view, message)
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                           // SnackbarUtils.displayError(view, throwable.message())
                           // SnackbarUtils.displayError(view, message)
                        }
                    }
                } catch (exception: Exception) {
                    SnackbarUtils.somethingWentWrong(view)
                    exception.printStackTrace()
                }
            }
            is ConnectException ->     SnackbarUtils.displayError(view!!, throwable.message!!.toString())
            is SocketTimeoutException ->     SnackbarUtils.displayError(view!!, throwable.message!!.toString())
            else ->     SnackbarUtils.displayError(view!!, throwable.message!!.toString())
        }
    }



}