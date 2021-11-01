package com.example.newmovie2.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.example.newmovie2.apiservices.MovieApi
import kotlinx.coroutines.launch
import java.lang.Exception
import com.example.newmovie2.models.Result

enum class MovieApiStatus {LOADING, DONE, ERROR}

class SearchFragmentVM : ViewModel() {
    private val _status = MutableLiveData<MovieApiStatus>()

    val status : LiveData<MovieApiStatus> = _status

    private val _movieTitle = MutableLiveData<List<Result>>()

    val movieTitle : LiveData<List<Result>> = _movieTitle


    fun getMovieTitle(query : String) {
        _status.value = MovieApiStatus.LOADING
        viewModelScope.launch {
            try {
                _movieTitle.value = MovieApi.retrofitService.getTitle(query).results
                _status.value = MovieApiStatus.DONE
            }catch(e: Exception){
                Log.d("sadaasdasda", "getMovieTitle: $e")
                _status.value = MovieApiStatus.ERROR
            }
        }
    }
}
