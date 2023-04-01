package com.example.ignite.screens.login

import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.ignite.IgniteRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    var uiState = mutableStateOf(LoginData())
        private set

    private val email
        get() = uiState.value.email
    private val password
        get() = uiState.value.password

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onLoginClick(openAndPopUp: (String, String) -> Unit) {
        openAndPopUp(IgniteRoutes.HomeScreen.route,IgniteRoutes.LoginScreen.route)
    }

    fun onForgotPasswordClick() {
    }
}

data class LoginData(
    val email: String = "",
    val password: String = ""
)