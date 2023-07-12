package com.cankarademir.atmosware_internship_project.models

typealias Photos = ArrayList<Photo>
data class Photo (
    val albumID: Long? = null,
    val id: Long? = null,
    val title: String? = null,
    val url: String? = null,
    val thumbnailURL: String? = null
)