package com.cankarademir.atmosware_internship_project.ui.details.detailPhoto

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cankarademir.atmosware_internship_project.configs.FavoriteDatabase
import com.cankarademir.atmosware_internship_project.dao.FavoriteDao
import com.cankarademir.atmosware_internship_project.models.Photos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailPhotoViewModel(application: Application) : AndroidViewModel(application) {

    private val favoriteDao: FavoriteDao
    private val allFavorites = MutableLiveData<List<Photos>>()

    init {
        val database = FavoriteDatabase.getDatabase(application)
        favoriteDao = database.FavoriteDao()
        }

    fun insertFavorite(favoriteData: Photos) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteDao.insertFavorite(favoriteData)
        }
    }

    fun deleteFavorite(favoriteData: Photos) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteDao.deleteFavorite(favoriteData.id)
        }
    }

    fun isFavorite(photoData: Photos?): Boolean {
        val favoritesList = allFavorites.value
        return favoritesList?.any { it.id == photoData?.id } ?: false
    }

    suspend fun fillFavoriteList(){
        allFavorites.value = withContext(Dispatchers.IO) {
            favoriteDao.getFavorites()
        }!!
    }
}