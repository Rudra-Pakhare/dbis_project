package com.example.ignite.api

//import com.example.ignite.models.general.Category
import com.example.ignite.models.general.ExerciseX
import com.example.ignite.models.general.Exercises
import com.example.ignite.models.user.Posts
import com.example.ignite.models.user.Subscriptions
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GeneralApi {

    @GET("exercise")//done
    suspend fun getExercises() : Response<Exercises>

    @GET("exercise/{exercise}")//done
    suspend fun getExercise(@Path("exercise") exercise: String) : Response<ExerciseX>

    @GET("post")
    suspend fun getPosts() : Response<Posts>

    @GET("subscription/taken/{id}")//done
    suspend fun getSubscriptionsTaken(@Path("id",encoded = false) id: String) : Response<Subscriptions>

    @GET("subscription/all/{id}")//done
    suspend fun getSubscriptionsAll(@Path("id",encoded = false) id: String) : Response<Subscriptions>
}