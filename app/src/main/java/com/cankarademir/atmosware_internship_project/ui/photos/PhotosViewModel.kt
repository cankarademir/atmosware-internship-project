package com.cankarademir.atmosware_internship_project.ui.photos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cankarademir.atmosware_internship_project.configs.ApiClient
import com.cankarademir.atmosware_internship_project.models.Photos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PhotosViewModel : ViewModel() {

    val data: MutableLiveData<List<Photos>> = MutableLiveData()

    fun getPhotosData() {
        viewModelScope.launch {
            try {
                val apiService = ApiClient.retrofitService
                val response = withContext(Dispatchers.IO) {
                    apiService.getPhotos().execute()
                }
                if (response.isSuccessful) {
                    data.value = response.body()
                    Log.d("MainLog", data.value.toString())
                }
            } catch (e: Exception) {
                // Hata durumunda gerekli işlemleri yapabilirsiniz
                Log.d("can", "HataaPhotoViewModel")
            }
        }
    }
}