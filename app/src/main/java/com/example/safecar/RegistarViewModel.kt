package com.example.safecar


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegistarViewModel : ViewModel() {

    private val _registarResult = MutableLiveData<Boolean>()
    val registarResultLiveData = _registarResult

    fun areCredentialsValid(username: String, password: String) {

        // ir ao servidor

        registarResultLiveData.postValue(username == password)
    }
}