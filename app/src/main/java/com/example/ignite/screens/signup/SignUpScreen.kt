package com.example.ignite.screens.signup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ignite.IgniteRoutes
import com.example.ignite.R
import com.example.ignite.screens.login.LoginViewModel

@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: SignUpViewModel = hiltViewModel()
){
    val uiState by viewModel.uiState

    var passwordVisibility by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Scaffold(Modifier.fillMaxSize()) {
                paddingValues ->
            Column(
                Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = "Sign Up",
                        fontSize = 40.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(start = 125.dp)
                    )
                    Text(
                        text = "Login",
                        Modifier.clickable { navController.navigate(IgniteRoutes.LoginScreen.route) }.padding(end = 10.dp),
                        fontSize = 20.sp,
                        color = Color(0xFF5DB075),
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                OutlinedTextField(
                    value = uiState.name,
                    onValueChange = { viewModel.onNameChange(it) },
                    placeholder = { Text("Name") },
                    shape = RoundedCornerShape(20),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .height(60.dp),
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color(0x66E8E8E8),focusedIndicatorColor = Color.Gray, cursorColor = Color.Gray )
                )
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    value = uiState.email,
                    onValueChange = { viewModel.onEmailChange(it) },
                    placeholder = { Text("Email") },
                    shape = RoundedCornerShape(20),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .height(60.dp),
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color(0x66E8E8E8),focusedIndicatorColor = Color.Gray, cursorColor = Color.Gray )
                )
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    value = uiState.password,
                    onValueChange = { viewModel.onPasswordChange(it) },
                    placeholder = { Text("Password") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = {
                            passwordVisibility = !passwordVisibility
                        }) {
                            Icon(painter = painterResource(id = R.drawable.baseline_remove_red_eye_24),"")
                        }
                    },
                    shape = RoundedCornerShape(20),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .height(60.dp),
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color(0x66E8E8E8),focusedIndicatorColor = Color.Gray, cursorColor = Color.Gray )
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = { navController.navigate(IgniteRoutes.HomeScreen.route) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF5DB075)),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(
                        text = "Sign In",
                        fontSize = 25.sp,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Forgot your password?",
                    Modifier.clickable {  },
                    fontSize = 20.sp,
                    color = Color(0xFF5DB075),
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreView(){
    SignUpScreen(navController = rememberNavController())
}