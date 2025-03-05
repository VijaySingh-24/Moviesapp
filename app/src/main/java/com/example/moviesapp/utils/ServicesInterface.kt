package com.example.moviesapp.utils
import com.example.moviesapp.constant.IntentConstant
import com.example.moviesapp.model.MoviesList
import com.example.moviesapp.model.SearchModel
import okhttp3.Response
import retrofit2.http.*
//'https://dummyjson.com/products/search?q=phone'

interface ServicesInterface {
    @GET(IntentConstant.GET_DATA)
    suspend fun setData(): MoviesList

    @GET("products/search")
    suspend fun searchProducts(@Query("q") query: String): SearchModel

}