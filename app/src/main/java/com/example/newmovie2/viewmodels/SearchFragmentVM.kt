package com.example.newmovie2.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData

enum class MovieApiStatus {LOADING, DONE, ERROR}

class SearchFragmentVM : ViewModel() {
    private val _status = MutableLiveData<MovieApiStatus>()

    val status : LiveData<MovieApiStatus> = _status

    private val _movieTitle = MutableLiveData<MovieApiStatus>()

    val movieTitle : LiveData<MovieApiStatus> = _movieTitle
}