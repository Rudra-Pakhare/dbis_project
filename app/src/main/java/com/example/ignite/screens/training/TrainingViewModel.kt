package com.example.ignite.screens.training

import com.example.ignite.IgniteViewModel
import com.example.ignite.models.service.AccountService
import com.example.ignite.models.service.LogService
import com.example.ignite.repository.GeneralRepository
import com.example.ignite.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrainingViewModel @Inject constructor(
    val accountService: AccountService,
    logService: LogService,
    private val generalRepository: GeneralRepository,
    private val userRepository: UserRepository
) : IgniteViewModel(logService){

    val subscriptionsAllLiveData get() = generalRepository.subscriptionsAllLiveData
    val subscriptionsTakenLiveData get() = generalRepository.subscriptionsTakenLiveData

    fun subscribe(subsId: String) {
        launchCatching {
            userRepository.subscribe(subsId,accountService.currentUserId)
        }
    }

    fun unsubscribe(subsId: String) {
        launchCatching {
            userRepository.unsubscribe(subsId,accountService.currentUserId)
        }
    }

    fun onAppStart() {
        launchCatching {
            generalRepository.getSubscriptionsAll(accountService.currentUserId)
            generalRepository.getSubscriptionsTaken(accountService.currentUserId)
        }
    }

}