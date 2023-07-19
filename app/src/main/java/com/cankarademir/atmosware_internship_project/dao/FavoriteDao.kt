package com.cankarademir.atmosware_internship_project.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cankarademir.atmosware_internship_project.models.Photos

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorite_table ")
    suspend fun getFavorites(): List<Photos>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavorite(favoriteData: Photos)

    @Query("DELETE FROM favorite_table WHERE id = :favoriteId")
    suspend fun deleteFavorite(favoriteId: Long?)
}