package com.example.ignite.screens.home

import androidx.lifecycle.viewModelScope
import com.example.ignite.IgniteRoutes
import com.example.ignite.IgniteViewModel
import com.example.ignite.models.User
import com.example.ignite.models.service.AccountService
import com.example.ignite.models.service.LogService
import com.example.ignite.repository.GeneralRepository
import com.example.ignite.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.getstream.chat.android.client.ChatClient
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val accountService: AccountService,
    logService: LogService,
    private val generalRepository: GeneralRepository,
    private val userRepository: UserRepository,
    private val client: ChatClient,
) : IgniteViewModel(logService){

    val exerciseResponseLiveData get() = generalRepository.exerciseResponseLiveData
    val exercisesResponseLiveData get() = generalRepository.exercisesResponseLiveData

    private fun getExercises() {
        launchCatching {
            generalRepository.getExercises()
        }
    }

    fun onExerciseClick(exercise: String) {
        launchCatching {
            generalRepository.getExercise(exercise)
        }
    }

    fun onAppStart() {
        launchCatching {
            if (accountService.hasUser)
            else accountService.createAnonymousAccount()
            getExercises()
            accountService.currentUser.collect{
                if(!accountService.firstLaunch || accountService.loggedIn){
                    accountService.firstLaunch = true
                    accountService.loggedIn = false
                    userRepository.signup(it)
                }
                if(!it.isAnonymous){
                    client.connectUser(
                        user = io.getstream.chat.android.client.models.User(
                            id = accountService.currentUserId,
                            name = "Ignite User"
                        ),
                        client.devToken(accountService.currentUserId),
                        timeoutMilliseconds = null,
                    ).enqueue()
                }
            }
        }
    }
}
