package com.example.ignite.api

import com.example.ignite.models.User
import com.example.ignite.models.user.PostUpload
import com.example.ignite.models.user.Posts
import com.example.ignite.models.user.SubscriptionUpload
import com.example.ignite.models.user.Subscriptions
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {

    @POST("signup")
    suspend fun signup(@Body userRequest: User) : Response<User>

    @POST("post")
    suspend fun postUpload(@Body post: PostUpload) : Response<PostUpload>

    @POST("subscription")
    suspend fun subscriptionUpload(@Body subscription: SubscriptionUpload) : Response<SubscriptionUpload>

    @GET("post/{id}")
    suspend fun getPost(@Path("id") id: String) : Response<Posts>

    @GET("subscription/{id}")
    suspend fun getSubscription(@Path("id") id: String) : Response<Subscriptions>

}