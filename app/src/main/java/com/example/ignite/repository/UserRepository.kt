package com.example.ignite.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ignite.api.UserApi
import com.example.ignite.models.User
import com.example.ignite.models.user.PostUpload
import com.example.ignite.models.user.Posts
import com.example.ignite.models.user.SubscriptionUpload
import com.example.ignite.models.user.Subscriptions
import com.example.ignite.utils.NetworkResult
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(private val userApi: UserApi) {

    private val _userResponseLiveData = MutableLiveData<NetworkResult<User>>()
    val userResponseLiveData: LiveData<NetworkResult<User>>
        get() = _userResponseLiveData

    suspend fun signup(user: User) {
        _userResponseLiveData.postValue(NetworkResult.Loading())
        try {
            val response = userApi.signup(user)
            handleResponse(response , _userResponseLiveData)
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
            handleResponse(response , _postUploadLiveData)
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
            handleResponse(response , _subscriptionUploadLiveData)
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
            handleResponse(response , _postLiveData)
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
            handleResponse(response , _subscriptionLiveData)
        } catch (e: Exception) {
            _subscriptionLiveData.postValue(NetworkResult.Error(e.message))
        }
    }

    private fun <T> handleResponse(response : Response<T>, _liveData : MutableLiveData<NetworkResult<T>>){
        if (response.isSuccessful && response.body() != null) {
            _liveData.postValue(NetworkResult.Success(response.body()!!))
        }
        else if(response.errorBody()!=null){
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _liveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        }
        else{
            _liveData.postValue(NetworkResult.Error("Something Went Wrong"))
        }
    }
}