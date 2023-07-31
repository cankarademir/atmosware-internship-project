package com.cankarademir.atmosware_internship_project.ui.activity.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val loginResult = MutableLiveData<Boolean>()

    var USERNAME= "kullanici"
    var PASSWORD= "123456"

    fun login() {
        val enteredUsername = username.value
        val enteredPassword = password.value

        val loginSuccessful = (enteredUsername == USERNAME && enteredPassword == PASSWORD )
        loginResult.value = loginSuccessful
    }
}