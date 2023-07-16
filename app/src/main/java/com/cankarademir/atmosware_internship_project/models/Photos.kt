package com.cankarademir.atmosware_internship_project.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Photos (
    val albumID: Long? = null,
    val id: Long? = null,
    val title: String? = null,
    val url: String,
    val thumbnailURL: String? = null
): Parcelable