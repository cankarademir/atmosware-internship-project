package com.cankarademir.atmosware_internship_project.services

import com.cankarademir.atmosware_internship_project.models.Comments
import com.cankarademir.atmosware_internship_project.models.Photos
import retrofit2.Call
import retrofit2.http.GET

interface Service {

    @GET("/photos")
    fun getPhotos(): Call<Photos>

    @GET("/comments")
    fun getComments(): Call<Comments>

}