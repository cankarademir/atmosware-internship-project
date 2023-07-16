package com.cankarademir.atmosware_internship_project.ui.user

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cankarademir.atmosware_internship_project.configs.ApiClient
import com.cankarademir.atmosware_internship_project.models.Users
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {
    val data: MutableLiveData<List<Users>> = MutableLiveData()
    fun getUserData() {
        val apiService = ApiClient.retrofitService
        val call = apiService.getUsers()

        call.enqueue(object : Callback<List<Users>> {
            override fun onResponse(
                call: Call<List<Users>>,
                response: Response<List<Users>>
            ) {
                if (response.isSuccessful) {
                    data.value = response.body()
                    Log.d("MainLog",data.value.toString())
                }
            }

            override fun onFailure(call: Call<List<Users>>, t: Throwable) {
                // Hata durumunda gerekli işlemleri yapabilirsiniz
                Log.d("can","HataaUserViewModel")
            }
        })
    }
}