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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ignite.screens.apitesting.ApiScreen
import com.example.ignite.screens.chat.ChatScreen
import com.example.ignite.screens.chat.ChatViewModel
import com.example.ignite.screens.chat.MessageScreen
import com.example.ignite.screens.feeds.FeedScreen
import com.example.ignite.screens.feeds.FeedViewModel
import com.example.ignite.screens.home.Exercise
import com.example.ignite.screens.home.HomeScreen
import com.example.ignite.screens.home.HomeViewModel
import com.example.ignite.screens.login.LoginScreen
import com.example.ignite.screens.profile.DeletePost
import com.example.ignite.screens.profile.DeleteSubs
import com.example.ignite.screens.profile.PostForm
import com.example.ignite.screens.profile.ProfileScreen
import com.example.ignite.screens.profile.ProfileViewModel
import com.example.ignite.screens.profile.SubscriptionForm
import com.example.ignite.screens.profile.UpdateProfilePic
import com.example.ignite.screens.signup.SignUpScreen
import com.example.ignite.screens.snackbar.SnackbarManager
import com.example.ignite.screens.training.TrainingScreen
import com.example.ignite.screens.training.TrainingViewModel
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
    val profileViewModel: ProfileViewModel = hiltViewModel()
    val homeViewModel: HomeViewModel = hiltViewModel()
    val trainingViewModel: TrainingViewModel = hiltViewModel()
    val feedViewModel: FeedViewModel = hiltViewModel()
    val chatViewModel: ChatViewModel = hiltViewModel()
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
                ProfileScreen(appState = appState, viewModel = profileViewModel)
            }
            composable(route = IgniteRoutes.HomeScreen.route){
                HomeScreen(appState = appState, viewModel = homeViewModel)
            }
            composable(route = IgniteRoutes.TrainingScreen.route){
                TrainingScreen(appState = appState, viewModel = trainingViewModel)
            }
            composable(route = IgniteRoutes.ApiScreen.route){
                ApiScreen(appState = appState)
            }
            composable(route = IgniteRoutes.Exercise.route + "/{exercise}"){
                Exercise(appState = appState, exercise = it.arguments?.getString("exercise") ?: "", viewModel = homeViewModel)
            }
            composable(route = IgniteRoutes.PostForm.route){
                PostForm(appState = appState, viewModel = profileViewModel)
            }
            composable(route = IgniteRoutes.SubscriptionForm.route){
                SubscriptionForm(appState = appState, viewModel = profileViewModel)
            }
            composable(route = IgniteRoutes.FeedScreen.route){
                FeedScreen(appState = appState, viewModel = feedViewModel)
            }
            composable(route = IgniteRoutes.UpdateProfilePic.route){
                UpdateProfilePic(appState = appState, viewModel = profileViewModel)
            }
            composable(route = IgniteRoutes.DeletePost.route){
                DeletePost(appState = appState, viewModel = profileViewModel)
            }
            composable(route = IgniteRoutes.DeleteSubs.route){
                DeleteSubs(appState = appState, viewModel = profileViewModel)
            }
            composable(route = IgniteRoutes.ChatScreen.route){
                ChatScreen(appState = appState, viewModel = chatViewModel)
            }
            composable(route = IgniteRoutes.MessageScreen.route + "/{id}"){
                MessageScreen(appState = appState, channelId = it.arguments?.getString("id") ?: "", viewModel = chatViewModel)
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

