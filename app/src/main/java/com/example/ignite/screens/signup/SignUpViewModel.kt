package com.example.ignite.screens.signup

import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.ignite.IgniteRoutes
import com.example.ignite.IgniteViewModel
import com.example.ignite.models.service.AccountService
import com.example.ignite.models.service.LogService
import com.example.ignite.screens.snackbar.SnackbarManager
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.regex.Pattern
import javax.inject.Inject
import com.example.ignite.R.string as AppText

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val accountService: AccountService,
    logService: LogService
) : IgniteViewModel(logService) {

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
        if(!name.isNotBlank()){
            SnackbarManager.showMessage(AppText.name_error)
            return
        }
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(AppText.email_error)
            return
        }

        if (!password.isValidPassword()) {
            SnackbarManager.showMessage(AppText.password_error)
            return
        }

        if (!password.passwordMatches(uiState.value.repeatPassword)) {
            SnackbarManager.showMessage(AppText.password_match_error)
            return
        }

        launchCatching {
            accountService.signIn(uiState.value.name,uiState.value.email,uiState.value.password)
            openAndPopUp(IgniteRoutes.HomeScreen.route, IgniteRoutes.SignUpScreen.route)
        }
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

private const val MIN_PASS_LENGTH = 6
private const val PASS_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$"

fun String.isValidEmail(): Boolean {
    return this.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isValidPassword(): Boolean {
    return this.isNotBlank() &&
            this.length >= MIN_PASS_LENGTH &&
            Pattern.compile(PASS_PATTERN).matcher(this).matches()
}

fun String.passwordMatches(repeated: String): Boolean {
    return this == repeated
}
