package com.example.newmovie2.viewmodels

import androidx.lifecycle.*
import com.example.newmovie2.apiservices.MovieApi
import kotlinx.coroutines.launch
import java.lang.Exception

enum class MovieApiStatus {LOADING, DONE, ERROR}

class SearchFragmentVM : ViewModel() {
    private val _status = MutableLiveData<MovieApiStatus>()

    val status : LiveData<MovieApiStatus> = _status

    private val _movieTitle = MutableLiveData<MovieApiStatus>()

    val movieTitle : LiveData<MovieApiStatus> = _movieTitle

    fun getMovieTitle(query : String) {
        _status.value = MovieApiStatus.LOADING
        viewModelScope.launch {
            try {
                MovieApi.retrofitService.getTitle(query).results
                _status.value = MovieApiStatus.DONE
            }catch(e: Exception){
                _status.value = MovieApiStatus.ERROR
            }
        }
    }
}