package com.example.ignite.screens.signup

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.ignite.IgniteRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {

    var uiState = mutableStateOf(SignUpData())
        private set

    private val email
        get() = uiState.value.email
    private val password
        get() = uiState.value.password
    private val name
        get() = uiState.value.name
    private val repeatPassword
        get() = uiState.value.repeatPassword

    fun onNameChange(newValue: String) {
        uiState.value = uiState.value.copy(name = newValue)
    }

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onRepeatPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(repeatPassword = newValue)
    }

    fun onSignInClick(openAndPopUp: (String, String) -> Unit) {
        openAndPopUp(IgniteRoutes.HomeScreen.route, IgniteRoutes.SignUpScreen.route)
    }

    fun onLoginClick(openAndPopUp: (String, String) -> Unit) {
        openAndPopUp(IgniteRoutes.LoginScreen.route,IgniteRoutes.SignUpScreen.route)
    }

    fun onForgotPasswordClick() {
    }
}

data class SignUpData(
    val email: String = "",
    val password: String = "",
    val name: String = "",
    val repeatPassword: String = ""
)