package com.example.moviesapp.viewmodel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.model.SearchModel
import com.example.moviesapp.repository.MovieRepository
import com.example.moviesapp.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: MovieRepository
):ViewModel()  {
    val SearchResponse = MutableLiveData<SearchModel?>()
    val errorSearch = MutableLiveData<Throwable>()
    val progressSearch = MutableLiveData<Boolean>()

    fun hitSearchApi(searchKey:String) {
        progressSearch.postValue(true)  // Using postValue() in background thread
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.searchApi(searchKey)
            launch(Dispatchers.Main) {
                when (result.status) {
                    Status.SUCCESS -> {
                        result.data?.let {
                            SearchResponse.value = result.data
                        }
                        progressSearch.value = false
                    }
                    Status.ERROR -> {
                        progressSearch.value = false
                        errorSearch.value = (result.message ?: "Unknown Error") as Throwable?
                    }
                    Status.LOADING -> progressSearch.value = true
                }
            }
        }
    }
}
