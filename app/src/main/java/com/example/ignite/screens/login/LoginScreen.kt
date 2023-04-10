package com.example.ignite.screens.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ignite.composables.formfields.EmailField
import com.example.ignite.composables.formfields.MyButton
import com.example.ignite.composables.formfields.PasswordField
import com.example.ignite.composables.formfields.TopRow

@Composable
fun LoginScreen(
    openAndPopUp: (String, String) -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
){
    val uiState by viewModel.uiState

    var passwordVisibility by remember { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        TopRow(text1 = "Log In", text2 = "Signup") {
            viewModel.onSignupClick(openAndPopUp)
        }
        Spacer(modifier = Modifier.height(30.dp))
        EmailField(value = uiState.email, onValueChange = {viewModel.onEmailChange(it)}, placeholder = "Email")
        Spacer(modifier = Modifier.height(20.dp))
        PasswordField(value = uiState.password ,onValueChange = {viewModel.onPasswordChange(it)} , placeholder = "Password",passwordVisibility = passwordVisibility) {
            passwordVisibility = !passwordVisibility
        }
        Spacer(modifier = Modifier.height(20.dp))
        MyButton(text = "Log In") {
            viewModel.onLoginClick(openAndPopUp)
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Forgot your password?",
            Modifier.clickable { viewModel.onForgotPasswordClick() },
            fontSize = 20.sp,
            color = Color(0xFF5DB075),
            fontWeight = FontWeight.SemiBold
        )
    }
}