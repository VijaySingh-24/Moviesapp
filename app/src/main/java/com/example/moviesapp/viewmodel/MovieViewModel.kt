package com.example.moviesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.moviesapp.model.MoviesList
import com.example.moviesapp.repository.MovieRepository

import com.example.moviesapp.utils.Resource
import com.example.moviesapp.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {
    private val _moviesResponse = MutableLiveData<Resource<MoviesList>>()
    val moviesResponse: LiveData<Resource<MoviesList>> get() = _moviesResponse

    val errorMovies = MutableLiveData<Throwable>()
    val progressMovies = MutableLiveData<Boolean>()

    fun hitMoviesList() {
        viewModelScope.launch {
            try {
                progressMovies.postValue(true)
                val result = repository.getMovies()
                _moviesResponse.postValue(result)
                progressMovies.postValue(false)
            } catch (e: Exception) {
                errorMovies.postValue(e)
                progressMovies.postValue(false)
            }
        }
    }
}
