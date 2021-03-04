package com.slavetny.firebasetesttask.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Exception

abstract class BaseViewModel : ViewModel() {
    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> get() = _errorLiveData

    fun onHandleError(exception: Exception) {
        _errorLiveData.value = exception.message
    }
}