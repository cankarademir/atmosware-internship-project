package com.cankarademir.atmosware_internship_project.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "favorite_table")
data class Photos (
    val albumId: Long? = null,
    @PrimaryKey
    val id: Long? = null,
    val title: String? = null,
    val url: String,
    val thumbnailUrl: String? = null
): Parcelable