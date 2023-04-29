package com.example.ignite.screens.feeds

import com.example.ignite.IgniteViewModel
import com.example.ignite.models.service.AccountService
import com.example.ignite.models.service.LogService
import com.example.ignite.repository.GeneralRepository
import com.example.ignite.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    val accountService: AccountService,
    logService: LogService,
    private val generalRepository: GeneralRepository,
    private val userRepository: UserRepository
) : IgniteViewModel(logService){

    val postsLiveData get() = generalRepository.postsLiveData

    fun onAppStart() {
        launchCatching {
            generalRepository.getPosts()
        }
    }
}