package com.example.ignite.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ignite.api.GeneralApi
import com.example.ignite.models.general.Exercises
import com.example.ignite.utils.NetworkResult
import org.json.JSONObject
import javax.inject.Inject

class GeneralRepository @Inject constructor(private val generalApi: GeneralApi) {

    private val _exerciseResponseLiveData = MutableLiveData<NetworkResult<Exercises>>()
    val exerciseResponseLiveData: LiveData<NetworkResult<Exercises>>
        get() = _exerciseResponseLiveData

    suspend fun getExercises() {
        _exerciseResponseLiveData.postValue(NetworkResult.Loading())
        try {
            val response = generalApi.getExercises()
            if (response.isSuccessful && response.body() != null) {
                _exerciseResponseLiveData.postValue(NetworkResult.Success(response.body()!!))
            }
            else if(response.errorBody()!=null){
                val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
                _exerciseResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
            }
            else{
                _exerciseResponseLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
            }
        } catch (e: Exception) {
            _exerciseResponseLiveData.postValue(NetworkResult.Error(e.message))
        }
    }
}