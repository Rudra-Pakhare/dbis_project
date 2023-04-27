package com.example.ignite.screens.profile

import com.example.ignite.IgniteViewModel
import com.example.ignite.models.User
import com.example.ignite.models.service.AccountService
import com.example.ignite.models.service.LogService
import com.example.ignite.models.user.PostUpload
import com.example.ignite.models.user.SubscriptionUpload
import com.example.ignite.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    val accountService: AccountService,
    logService: LogService,
    private val userRepository: UserRepository
) : IgniteViewModel(logService){

    val userResponseLiveData get() = userRepository.userResponseLiveData
    val postUploadLiveData get() = userRepository.postUploadLiveData
    val subscriptionUploadLiveData get() = userRepository.subscriptionUploadLiveData
    val postLiveData get() = userRepository.postLiveData
    val subscriptionLiveData get() = userRepository.subscriptionLiveData

    fun postUpload(postUpload: PostUpload) {
        launchCatching {
            userRepository.postUpload(postUpload)
        }
    }

    fun subscriptionUpload(subscriptionUpload: SubscriptionUpload) {
        launchCatching {
            userRepository.subscriptionUpload(subscriptionUpload)
        }
    }

    fun onLogoutClick() {
        launchCatching {
            accountService.signOut()
        }
    }
}