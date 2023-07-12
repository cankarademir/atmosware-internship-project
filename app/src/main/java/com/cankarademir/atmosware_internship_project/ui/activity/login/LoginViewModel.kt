package com.cankarademir.atmosware_internship_project.ui.activity.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val loginResult = MutableLiveData<Boolean>()
    val passwordVisible = MutableLiveData<Boolean>()

    fun login() {
        val enteredUsername = username.value
        val enteredPassword = password.value

        val loginSuccessful = (enteredUsername == "kullanici_adi" && enteredPassword == "sifre")
        loginResult.value = loginSuccessful
    }
}