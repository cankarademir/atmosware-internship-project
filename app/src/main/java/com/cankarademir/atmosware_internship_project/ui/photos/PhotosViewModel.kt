package com.cankarademir.atmosware_internship_project.ui.photos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cankarademir.atmosware_internship_project.configs.ApiClient
import com.cankarademir.atmosware_internship_project.models.Photos
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotosViewModel : ViewModel() {

    val data: MutableLiveData<List<Photos>> = MutableLiveData()

    fun getPhotosData() {
        val apiService = ApiClient.retrofitService
        val call = apiService.getPhotos()

        call.enqueue(object : Callback<List<Photos>> {
            override fun onResponse(
                call: Call<List<Photos>>,
                response: Response<List<Photos>>
            ) {
                if (response.isSuccessful) {
                    data.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<Photos>>, t: Throwable) {
                // Hata durumunda gerekli işlemleri yapabilirsiniz
            }
        })
    }
}