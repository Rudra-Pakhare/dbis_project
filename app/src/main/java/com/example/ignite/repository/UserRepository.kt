package com.example.ignite.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ignite.api.UserApi
import com.example.ignite.models.User
import com.example.ignite.utils.NetworkResult
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(private val userApi: UserApi) {

    private val _userResponseLiveData = MutableLiveData<NetworkResult<String>>()
    val userResponseLiveData: LiveData<NetworkResult<String>>
        get() = _userResponseLiveData
}