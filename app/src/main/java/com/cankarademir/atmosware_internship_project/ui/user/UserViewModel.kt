package com.cankarademir.atmosware_internship_project.ui.user

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cankarademir.atmosware_internship_project.configs.ApiClient
import com.cankarademir.atmosware_internship_project.models.Users
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel : ViewModel() {
    val data: MutableLiveData<List<Users>> = MutableLiveData()

    fun getUserData() {
        viewModelScope.launch {
            try {
                val apiService = ApiClient.retrofitService
                val response = withContext(Dispatchers.IO) {
                    apiService.getUsers().execute()
                }

                if (response.isSuccessful) {
                    data.value = response.body()
                    Log.d("MainLog", data.value.toString())
                }
            } catch (e: Exception) {
                // Hata durumunda gerekli işlemleri yapabilirsiniz
                Log.d("can", "HataaUserViewModel")
            }
        }
    }
}