package com.example.ignite.screens.profile

import com.example.ignite.IgniteViewModel
import com.example.ignite.models.User
import com.example.ignite.models.service.AccountService
import com.example.ignite.models.service.LogService
import com.example.ignite.models.user.PostUpload
import com.example.ignite.models.user.SubscriptionUpload
import com.example.ignite.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    val accountService: AccountService,
    logService: LogService,
    private val userRepository: UserRepository
) : IgniteViewModel(logService){

    val postLiveData get() = userRepository.postLiveData
    val subscriptionLiveData get() = userRepository.subscriptionLiveData
    val profileLiveData get() = userRepository.profileLiveData

    fun postUpload(postUpload: PostUpload) {
        launchCatching {
            userRepository.postUpload(postUpload)
        }
    }

    fun postUploadImage(postDesc: String, userId : String, image: File) {
        launchCatching {
            userRepository.postUploadImage(postDesc, userId, image = image)
        }
    }

    fun deletePost(postId: String) {
        launchCatching {
            userRepository.deletePost(postId,accountService.currentUserId)
        }
    }

    fun deleteSubs(subsId: String) {
        launchCatching {
            userRepository.deleteSubs(subsId,accountService.currentUserId)
        }
    }

    fun subscriptionUpload(subscriptionUpload: SubscriptionUpload) {
        launchCatching {
            userRepository.subscriptionUpload(subscriptionUpload)
        }
    }

    fun postProfileImage(userId: String, image: File) {
        launchCatching {
            userRepository.postProfilePic(userId, image = image)
        }
    }

    fun getPost() {
        launchCatching {
            userRepository.getPost(accountService.currentUserId)
        }
    }

    fun getSubscription() {
        launchCatching {
            userRepository.getSubscription(accountService.currentUserId)
        }
    }

    fun getProfilePic() {
        launchCatching {
            userRepository.getProfilePic(accountService.currentUserId)
        }
    }

    fun onLogoutClick() {
        launchCatching {
            accountService.signOut()
        }
    }

    fun onAppStart() {
        launchCatching {
            getPost()
            getSubscription()
            getProfilePic()
        }
    }
}