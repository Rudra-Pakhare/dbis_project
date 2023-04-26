package com.example.ignite.api

import com.example.ignite.models.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("/users/signup")
    suspend fun signup(@Body userRequest: User) : Response<String>

}