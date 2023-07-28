package com.cankarademir.atmosware_internship_project.ui.favorite

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cankarademir.atmosware_internship_project.configs.FavoriteDatabase
import com.cankarademir.atmosware_internship_project.models.Photos
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {
    private val _data: MutableStateFlow<List<Photos>?> = MutableStateFlow(null)
    val data: Flow<List<Photos>?> get() = _data.asStateFlow()

    val favorites = FavoriteDatabase.getDatabase(application).FavoriteDao()

    fun getPhotosData() {
        viewModelScope.launch {
            try {
                val allfavorites = favorites.getFavorites()
                _data.value = allfavorites
            } catch (e: Exception) {
                // Hata durumunda gerekli işlemleri yapabilirsiniz
                Log.d("can", "HataaPhotoViewModel")
            }
        }
    }
}
