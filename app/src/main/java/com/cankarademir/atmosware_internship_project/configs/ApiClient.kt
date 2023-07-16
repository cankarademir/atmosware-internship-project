package com.cankarademir.atmosware_internship_project.configs

import com.cankarademir.atmosware_internship_project.services.Service
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiClient {

    private val Base_URL = "https://jsonplaceholder.typicode.com/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Base_URL)
        .build()

    val retrofitService: Service by lazy {
        retrofit.create(Service::class.java)
    }
}