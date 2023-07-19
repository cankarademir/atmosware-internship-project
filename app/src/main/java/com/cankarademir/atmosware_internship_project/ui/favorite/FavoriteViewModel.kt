package com.cankarademir.atmosware_internship_project.ui.favorite

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cankarademir.atmosware_internship_project.configs.FavoriteDatabase
import com.cankarademir.atmosware_internship_project.models.Photos
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    val data: MutableLiveData<List<Photos>> = MutableLiveData()
    val favorites = FavoriteDatabase.getDatabase(application).FavoriteDao()
    fun getPhotosData() {

        viewModelScope.launch {
            try {
                val allfavorites = favorites.getFavorites()
                data.value = allfavorites
            } catch (e: Exception) {
                // Hata durumunda gerekli işlemleri yapabilirsiniz
                Log.d("can", "HataaPhotoViewModel")
            }
        }
    }
}