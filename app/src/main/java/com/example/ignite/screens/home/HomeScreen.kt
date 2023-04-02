package com.example.ignite.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ignite.IgniteRoutes

@Composable
fun HomeScreen(navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Home Screen", fontSize = 40.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "to profile",
            Modifier.clickable { navController.navigate(IgniteRoutes.ProfileScreen.route) },
            fontSize = 20.sp
        )
        Text(text = "to logout",
            Modifier.clickable { navController.navigate(IgniteRoutes.LoginScreen.route) },
            fontSize = 20.sp
        )
    }
}