package com.cankarademir.atmosware_internship_project.configs

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cankarademir.atmosware_internship_project.dao.FavoriteDao
import com.cankarademir.atmosware_internship_project.models.Photos

@Database(entities = [Photos::class], version = 2)
abstract class FavoriteDatabase: RoomDatabase() {
    abstract fun FavoriteDao(): FavoriteDao
    companion object {

        @Volatile
        private var INSTANCE: FavoriteDatabase? = null

        fun getDatabase(context: Context): FavoriteDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteDatabase::class.java,
                    "favorite_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}