package com.example.ignite

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ignite.screens.home.HomeScreen
import com.example.ignite.screens.login.LoginScreen
import com.example.ignite.screens.profile.ProfileScreen
import com.example.ignite.screens.signup.SignUpScreen
import com.example.ignite.ui.theme.IGNITETheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        setContent {
            IGNITETheme {
                // A surface container using the 'background' color from the theme
                IgniteApp(
                    modifier = Modifier.fillMaxSize(),
                    navController = rememberNavController(),
                    startDestination = IgniteRoutes.LoginScreen.route
                )
            }
        }
    }
}

@Composable
fun IgniteApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = IgniteRoutes.LoginScreen.route
){
    NavHost(navController = navController, startDestination = startDestination){
        composable(route = IgniteRoutes.LoginScreen.route){
            LoginScreen(navController = navController)
        }
        composable(route = IgniteRoutes.SignUpScreen.route){
            SignUpScreen(navController = navController)
        }
        composable(route = IgniteRoutes.ProfileScreen.route){
            ProfileScreen(navController = navController)
        }
        composable(route = IgniteRoutes.HomeScreen.route){
            HomeScreen(navController = navController)
        }
    }
}

