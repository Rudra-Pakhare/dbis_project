package com.example.ignite.screens.home

import com.example.ignite.IgniteRoutes
import com.example.ignite.IgniteViewModel
import com.example.ignite.models.service.AccountService
import com.example.ignite.models.service.LogService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val accountService: AccountService,
    logService: LogService
) : IgniteViewModel(logService){

    fun onLogoutClick(openAndPopUp: (String, String) -> Unit) {
        launchCatching {
            accountService.signOut()
            openAndPopUp(IgniteRoutes.LoginScreen.route, IgniteRoutes.HomeScreen.route)
        }
    }
}