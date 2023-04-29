package com.example.ignite.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ignite.api.UserApi
import com.example.ignite.models.User
import com.example.ignite.models.user.PostUpload
import com.example.ignite.models.user.Posts
import com.example.ignite.models.user.ProfilePic
import com.example.ignite.models.user.SubscriptionUpload
import com.example.ignite.models.user.Subscriptions
import com.example.ignite.utils.NetworkResult
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Response
import java.io.File
import javax.inject.Inject

class UserRepository @Inject constructor(private val userApi: UserApi) {

    private val _userResponseLiveData = MutableLiveData<NetworkResult<User>>()
    val userResponseLiveData: LiveData<NetworkResult<User>>
        get() = _userResponseLiveData

    suspend fun signup(user: User) {
        _userResponseLiveData.postValue(NetworkResult.Loading())
        try {
            val response = userApi.signup(user)
            if (response.isSuccessful && response.body() != null) {
                _userResponseLiveData.postValue(NetworkResult.Success(response.body()!!))
            }
            else if(response.errorBody()!=null){
                val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
                _userResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
            }
            else{
                _userResponseLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
            }
        } catch (e: Exception) {
            _userResponseLiveData.postValue(NetworkResult.Error(e.message))
        }
    }

    private val _postUploadLiveData = MutableLiveData<NetworkResult<PostUpload>>()
    val postUploadLiveData: LiveData<NetworkResult<PostUpload>>
        get() = _postUploadLiveData

    suspend fun postUpload(post: PostUpload) {
        _postUploadLiveData.postValue(NetworkResult.Loading())
        try {
            val response = userApi.postUpload(post)
            if (response.isSuccessful && response.body() != null) {
                _postUploadLiveData.postValue(NetworkResult.Success(response.body()!!))
            }
            else if(response.errorBody()!=null){
                val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
                _postUploadLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
            }
            else{
                _postUploadLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
            }
        } catch (e: Exception) {
            _postUploadLiveData.postValue(NetworkResult.Error(e.message))
        }
    }

    suspend fun postUploadImage(postDesc : String, userId : String, image: File) {
        _postUploadLiveData.postValue(NetworkResult.Loading())
        try {
            var file = MultipartBody.Part.createFormData("image", image.name, image.asRequestBody("image/*".toMediaTypeOrNull()))
            val response = userApi.postUploadImage(file,postDesc.toRequestBody("text/plain".toMediaTypeOrNull()),userId.toRequestBody("text/plain".toMediaTypeOrNull()))
            if (response.isSuccessful && response.body() != null) {
                _postUploadLiveData.postValue(NetworkResult.Success(response.body()!!))
            }
            else if(response.errorBody()!=null){
                val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
                _postUploadLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
            }
            else{
                _postUploadLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
            }
        } catch (e: Exception) {
            _postUploadLiveData.postValue(NetworkResult.Error(e.message))
        }
    }

    private val _subscriptionUploadLiveData = MutableLiveData<NetworkResult<SubscriptionUpload>>()
    val subscriptionUploadLiveData: LiveData<NetworkResult<SubscriptionUpload>>
        get() = _subscriptionUploadLiveData

    suspend fun subscriptionUpload(subscription: SubscriptionUpload) {
        _subscriptionUploadLiveData.postValue(NetworkResult.Loading())
        try {
            val response = userApi.subscriptionUpload(subscription)
            if (response.isSuccessful && response.body() != null) {
                _subscriptionUploadLiveData.postValue(NetworkResult.Success(response.body()!!))
            }
            else if(response.errorBody()!=null){
                val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
                _subscriptionUploadLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
            }
            else{
                _subscriptionUploadLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
            }
        } catch (e: Exception) {
            _subscriptionUploadLiveData.postValue(NetworkResult.Error(e.message))
        }
    }

    private val _postLiveData = MutableLiveData<NetworkResult<Posts>>()
    val postLiveData: LiveData<NetworkResult<Posts>>
        get() = _postLiveData

    suspend fun getPost(id: String) {
        _postLiveData.postValue(NetworkResult.Loading())
        try {
            val response = userApi.getPost(id)
            if (response.isSuccessful && response.body() != null) {
                _postLiveData.postValue(NetworkResult.Success(response.body()!!))
            }
            else if(response.errorBody()!=null){
                val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
                _postLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
            }
            else{
                _postLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
            }
        } catch (e: Exception) {
            _postLiveData.postValue(NetworkResult.Error(e.message))
        }
    }

    private val _subscriptionLiveData = MutableLiveData<NetworkResult<Subscriptions>>()
    val subscriptionLiveData: LiveData<NetworkResult<Subscriptions>>
        get() = _subscriptionLiveData

    suspend fun getSubscription(id: String) {
        _subscriptionLiveData.postValue(NetworkResult.Loading())
        try {
            val response = userApi.getSubscription(id)
            if (response.isSuccessful && response.body() != null) {
                _subscriptionLiveData.postValue(NetworkResult.Success(response.body()!!))
            }
            else if(response.errorBody()!=null){
                val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
                _subscriptionLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
            }
            else{
                _subscriptionLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
            }
        } catch (e: Exception) {
            _subscriptionLiveData.postValue(NetworkResult.Error(e.message))
        }
    }

    private val _profileLiveData = MutableLiveData<NetworkResult<ProfilePic>>()
    val profileLiveData: LiveData<NetworkResult<ProfilePic>>
        get() = _profileLiveData

    suspend fun getProfilePic(id: String) {
        _profileLiveData.postValue(NetworkResult.Loading())
        try {
            val response = userApi.getProfilePic(id)
            if (response.isSuccessful && response.body() != null) {
                _profileLiveData.postValue(NetworkResult.Success(response.body()!!))
            }
            else if(response.errorBody()!=null){
                val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
                _profileLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
            }
            else{
                _profileLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
            }
        } catch (e: Exception) {
            _profileLiveData.postValue(NetworkResult.Error(e.message))
        }
    }

    suspend fun postProfilePic(id: String, image: File) {
        _profileLiveData.postValue(NetworkResult.Loading())
        try {
            var file = MultipartBody.Part.createFormData("image", image.name, image.asRequestBody("image/*".toMediaTypeOrNull()))
            val response = userApi.postProfilePic(file,id.toRequestBody("text/plain".toMediaTypeOrNull()))
            if (response.isSuccessful && response.body() != null) {
                _profileLiveData.postValue(NetworkResult.Success(response.body()!!))
            }
            else if(response.errorBody()!=null){
                val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
                _profileLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
            }
            else{
                _profileLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
            }
        } catch (e: Exception) {
            _profileLiveData.postValue(NetworkResult.Error(e.message))
        }
    }

    suspend fun deletePost(id: String,userId:String) {
        _postUploadLiveData.postValue(NetworkResult.Loading())
        try {
            val response = userApi.deletePost(id,userId)
            if (response.isSuccessful && response.body() != null) {
                _postUploadLiveData.postValue(NetworkResult.Success(response.body()!!))
            }
            else if(response.errorBody()!=null){
                val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
                _postUploadLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
            }
            else{
                _postUploadLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
            }
        } catch (e: Exception) {
            _postUploadLiveData.postValue(NetworkResult.Error(e.message))
        }
    }

    suspend fun deleteSubs(id: String,userId:String){
        _subscriptionUploadLiveData.postValue(NetworkResult.Loading())
        try {
            val response = userApi.deleteSubs(id,userId)
            if (response.isSuccessful && response.body() != null) {
                _subscriptionUploadLiveData.postValue(NetworkResult.Success(response.body()!!))
            }
            else if(response.errorBody()!=null){
                val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
                _subscriptionUploadLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
            }
            else{
                _subscriptionUploadLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
            }
        } catch (e: Exception) {
            _subscriptionUploadLiveData.postValue(NetworkResult.Error(e.message))
        }
    }

    suspend fun subscribe(id: String,userId:String){
        _subscriptionUploadLiveData.postValue(NetworkResult.Loading())
        try {
            val response = userApi.subscribe(id,userId)
            if (response.isSuccessful && response.body() != null) {
                _subscriptionUploadLiveData.postValue(NetworkResult.Success(response.body()!!))
            }
            else if(response.errorBody()!=null){
                val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
                _subscriptionUploadLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
            }
            else{
                _subscriptionUploadLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
            }
        } catch (e: Exception) {
            _subscriptionUploadLiveData.postValue(NetworkResult.Error(e.message))
        }
    }

    suspend fun unsubscribe(id: String,userId:String){
        _subscriptionUploadLiveData.postValue(NetworkResult.Loading())
        try {
            val response = userApi.unsubscribe(id,userId)
            if (response.isSuccessful && response.body() != null) {
                _subscriptionUploadLiveData.postValue(NetworkResult.Success(response.body()!!))
            }
            else if(response.errorBody()!=null){
                val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
                _subscriptionUploadLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
            }
            else{
                _subscriptionUploadLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
            }
        } catch (e: Exception) {
            _subscriptionUploadLiveData.postValue(NetworkResult.Error(e.message))
        }
    }
}