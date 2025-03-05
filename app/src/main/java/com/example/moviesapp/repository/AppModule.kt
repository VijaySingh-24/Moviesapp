package com.example.moviesapp.repository


import com.example.moviesapp.constant.NetworkConstants
import com.example.moviesapp.utils.ResponseHandler
import com.example.moviesapp.utils.ServicesInterface

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ServicesInterface {
        return Retrofit.Builder()
            .baseUrl(NetworkConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ServicesInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(apiService: ServicesInterface, responseHandler: ResponseHandler): MovieRepository {
        return MovieRepository(apiService, responseHandler)
    }

    @Provides
    @Singleton
    fun provideResponseHandler(): ResponseHandler {
        return ResponseHandler()
    }
}
