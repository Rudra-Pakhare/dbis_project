package com.example.ignite.screens.training

import com.example.ignite.IgniteViewModel
import com.example.ignite.models.service.AccountService
import com.example.ignite.models.service.LogService
import com.example.ignite.repository.GeneralRepository
import com.example.ignite.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.getstream.chat.android.client.ChatClient
import javax.inject.Inject

@HiltViewModel
class TrainingViewModel @Inject constructor(
    val accountService: AccountService,
    logService: LogService,
    private val generalRepository: GeneralRepository,
    private val userRepository: UserRepository,
    private val client: ChatClient,
) : IgniteViewModel(logService){

    val subscriptionsAllLiveData get() = generalRepository.subscriptionsAllLiveData
    val subscriptionsTakenLiveData get() = generalRepository.subscriptionsTakenLiveData

    fun subscribe(subsId: String) {
        launchCatching {
            userRepository.subscribe(subsId,accountService.currentUserId)
            onAppStart()
        }
    }

    fun unsubscribe(subsId: String) {
        launchCatching {
            userRepository.unsubscribe(subsId,accountService.currentUserId)
            onAppStart()
        }
    }

    fun createChannel(userId:String,mentorId:String,name:String){
        launchCatching {
            client.createChannel(
                channelType = "messaging",
                channelId = "${userId}_${mentorId}",
                extraData = mutableMapOf(
                    "members" to listOf(userId, mentorId),
                    "mentor" to mentorId,
                    "name" to name,
                ),
                memberIds = listOf(userId, mentorId),
            ).enqueue()
        }
    }

    fun onAppStart() {
        launchCatching {
            generalRepository.getSubscriptionsAll(accountService.currentUserId)
            generalRepository.getSubscriptionsTaken(accountService.currentUserId)
        }
    }

}