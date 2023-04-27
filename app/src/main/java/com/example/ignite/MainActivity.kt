package com.example.ignite

import android.content.pm.ActivityInfo
import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ignite.screens.apitesting.ApiScreen
import com.example.ignite.screens.home.HomeScreen
import com.example.ignite.screens.login.LoginScreen
import com.example.ignite.screens.profile.ProfileScreen
import com.example.ignite.screens.signup.SignUpScreen
import com.example.ignite.screens.snackbar.SnackbarManager
import com.example.ignite.screens.training.TrainingScreen
import com.example.ignite.ui.theme.IGNITETheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope

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
                    startDestination = IgniteRoutes.HomeScreen.route
                )
            }
        }
    }
}

@Composable
fun IgniteApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String
){
    val appState = rememberAppState(navController)

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = it,
                modifier = Modifier.padding(8.dp),
                snackbar = { snackbarData ->
                    Snackbar(snackbarData, contentColor = MaterialTheme.colors.onPrimary)
                }
            )
        },
        scaffoldState = appState.scaffoldState
    ){
        _ ->
        NavHost(navController = navController, startDestination = startDestination){
            composable(route = IgniteRoutes.LoginScreen.route){
                LoginScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
            }
            composable(route = IgniteRoutes.SignUpScreen.route){
                SignUpScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
            }
            composable(route = IgniteRoutes.ProfileScreen.route){
                ProfileScreen(appState = appState)
            }
            composable(route = IgniteRoutes.HomeScreen.route){
                HomeScreen(appState = appState)
            }
            composable(route = IgniteRoutes.TrainingScreen.route){
                TrainingScreen(appState = appState)
            }
            composable(route = IgniteRoutes.ApiScreen.route){
                ApiScreen(appState = appState)
            }
        }
    }
}

@Composable
@ReadOnlyComposable
fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}

@Composable
fun rememberAppState(
    navController: NavHostController,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    snackbarManager: SnackbarManager = SnackbarManager,
    resources: Resources = resources()
) =
    remember(navController,scaffoldState,coroutineScope,snackbarManager,resources) {
        IgniteState(navController,scaffoldState,coroutineScope,snackbarManager,resources)
    }

