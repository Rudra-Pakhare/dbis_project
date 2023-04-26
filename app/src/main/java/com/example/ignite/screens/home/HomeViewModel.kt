package com.example.ignite.screens.home

import androidx.lifecycle.viewModelScope
import com.example.ignite.IgniteRoutes
import com.example.ignite.IgniteViewModel
import com.example.ignite.models.User
import com.example.ignite.models.service.AccountService
import com.example.ignite.models.service.LogService
import com.example.ignite.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val accountService: AccountService,
    logService: LogService,
    private val userRepository: UserRepository
) : IgniteViewModel(logService){

    fun onAppStart() {
        launchCatching {
            if (accountService.hasUser)
            else {
                accountService.createAnonymousAccount()
                viewModelScope.launch {
                    userRepository.registerUser(User(accountService.currentUserId))
                }
            }
        }
    }
}
