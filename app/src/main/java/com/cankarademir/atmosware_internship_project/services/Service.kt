package com.cankarademir.atmosware_internship_project.services

import com.cankarademir.atmosware_internship_project.models.Photos
import com.cankarademir.atmosware_internship_project.models.Users
import retrofit2.Call
import retrofit2.http.GET

interface Service {

    @GET("photos")
    fun getPhotos(): Call<List<Photos>>

    @GET("users")
    fun getUsers(): Call<List<Users>>

}