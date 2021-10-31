package com.example.newmovie2.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newmovie2.apiservices.MovieApi
import com.example.newmovie2.models.DetailResponse
import com.example.newmovie2.models.Result
import kotlinx.coroutines.launch
import java.lang.Exception

class DetailFragmentVM : ViewModel() {
    private val _status = MutableLiveData<MovieApiStatus>()

    val status: LiveData<MovieApiStatus> = _status

    private val _movieDetail = MutableLiveData<DetailResponse>()

    val movieDetail = _movieDetail


    fun getMovieDetail(query: String) {
        _status.value = MovieApiStatus.LOADING
        viewModelScope.launch {
            try {
                _movieDetail.value = MovieApi.retrofitService.getDetail(query)
                _status.value = MovieApiStatus.DONE
            } catch (e: Exception) {
                _status.value = MovieApiStatus.ERROR
            }
        }
    }
}