package com.example.ignite.api

//import com.example.ignite.models.general.Category
import com.example.ignite.models.general.Exercises
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GeneralApi {

    @GET("exercise")
    suspend fun getExercises() : Response<Exercises>

}