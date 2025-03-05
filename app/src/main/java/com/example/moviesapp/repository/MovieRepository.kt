package com.example.moviesapp.repository

import com.example.moviesapp.model.MoviesList
import com.example.moviesapp.model.SearchModel
import com.example.moviesapp.utils.Resource
import com.example.moviesapp.utils.ResponseHandler
import com.example.moviesapp.utils.ServicesInterface
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val apiService: ServicesInterface,
    private val responseHandler: ResponseHandler
) {
    suspend fun getMovies():Resource<MoviesList>{
        return  try {

            val moviesData = apiService.setData()
            responseHandler.handleSuccess(moviesData)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }


    suspend fun searchApi(searchKey: String): Resource<SearchModel> {
        return try {
            val searchData = apiService.searchProducts(
                searchKey
            ) // Removed ?. since servicesInterface is non-null
            responseHandler.handleSuccess(searchData)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }


    }

}