package com.example.ignite.screens.apitesting

import com.example.ignite.IgniteViewModel
import com.example.ignite.models.service.AccountService
import com.example.ignite.models.service.LogService
import com.example.ignite.repository.GeneralRepository
import com.example.ignite.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ApiViewModel @Inject constructor(
    val accountService: AccountService,
    logService: LogService,
    private val generalRepository: GeneralRepository
) : IgniteViewModel(logService){



    fun onAppStart() {
        launchCatching {
            if (accountService.hasUser)
            else accountService.createAnonymousAccount()
        }
    }
}