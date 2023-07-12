package com.cankarademir.atmosware_internship_project.models

typealias Comments = ArrayList<Comment>
data class Comment (
    val postID: Long,
    val id: Long,
    val name: String,
    val email: String,
    val body: String
)