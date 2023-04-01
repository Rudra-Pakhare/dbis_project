package com.example.ignite.screens.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ignite.IgniteRoutes

@Composable
fun LoginScreen(navController: NavController){
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
                Text(
                    text = "Log In",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(30.dp))
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("Email") },
                    shape = RoundedCornerShape(20),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .height(60.dp),
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color(0x66E8E8E8))
                )
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("Password") },
                    shape = RoundedCornerShape(20),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .height(60.dp),
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color(0x66E8E8E8))
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
                        text = "Log In",
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
    LoginScreen(navController = rememberNavController())
}