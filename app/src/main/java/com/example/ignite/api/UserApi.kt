package com.example.ignite.api

import com.example.ignite.models.User
import com.example.ignite.models.user.PostUpload
import com.example.ignite.models.user.Posts
import com.example.ignite.models.user.PostsItem
import com.example.ignite.models.user.ProfilePic
import com.example.ignite.models.user.SubscriptionUpload
import com.example.ignite.models.user.Subscriptions
import com.example.ignite.models.user.SubscriptionsItem
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface UserApi {

    @POST("signup")//done
    suspend fun signup(@Body userRequest: User) : Response<User>

    @Multipart
    @POST("user/post/image")//done
    suspend fun postUploadImage(@Part image: MultipartBody.Part, @Part("postDesc") postDesc: RequestBody,@Part("userId") userId: RequestBody) : Response<PostUpload>

    @POST("user/post")//done
    suspend fun postUpload(@Body post: PostUpload) : Response<PostUpload>

    @POST("user/subscription")//done
    suspend fun subscriptionUpload(@Body subscription: SubscriptionUpload) : Response<SubscriptionUpload>

    @GET("post/{id}")//done
    suspend fun getPost(@Path("id",encoded = false) id: String) : Response<Posts>

    @GET("subscription/{id}")//done
    suspend fun getSubscription(@Path("id",encoded = false) id: String) : Response<Subscriptions>

    @GET("user/profilepic/{id}")//done
    suspend fun getProfilePic(@Path("id",encoded = false) id: String) : Response<ProfilePic>

    @Multipart
    @POST("update/profilepic")//done
    suspend fun postProfilePic(@Part image: MultipartBody.Part, @Part("userId") userId: RequestBody) : Response<ProfilePic>

    @POST("subscribe")
    suspend fun subscribe(@Body userId: String,@Body subsId:String) : Response<SubscriptionUpload>

    @POST("unsubscribe")
    suspend fun unsubscribe(@Body userId: String,@Body subsId:String) : Response<SubscriptionUpload>

    @DELETE("post/delete/{postId}/{userId}")//done
    suspend fun deletePost(@Path("postId",encoded = false) postId: String,@Path("userId",encoded = false) userId: String) : Response<PostUpload>

    @DELETE("subscription/delete/{subsId}/{userId}")//done
    suspend fun deleteSubs(@Path("subsId",encoded = false) subsId: String,@Path("userId",encoded = false) userId: String) : Response<SubscriptionUpload>
}