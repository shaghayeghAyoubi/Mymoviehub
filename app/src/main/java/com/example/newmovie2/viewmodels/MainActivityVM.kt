package com.example.newmovie2.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.launch

class MainActivityVM  : ViewModel(){
    private val _isLinearLayoutManager = MutableLiveData<Boolean>()

    val isLinearLayoutManager : LiveData<Boolean> = _isLinearLayoutManager

    fun setLayout(flag: Boolean) {
        viewModelScope.launch {
            _isLinearLayoutManager.value = flag
        }
    }
}