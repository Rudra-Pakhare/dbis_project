package com.example.ignite

import android.content.res.Resources
import androidx.compose.material.ScaffoldState
import androidx.navigation.NavHostController
import com.example.ignite.screens.snackbar.SnackbarManager
import com.example.ignite.screens.snackbar.SnackbarMessage.Companion.toMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class IgniteState(
    private val navController: NavHostController,
    val scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope,
    private val snackbarManager: SnackbarManager,
    private val resources: Resources,
) {

    init {
        coroutineScope.launch {
            snackbarManager.snackbarMessages.filterNotNull().collect { snackbarMessage ->
                val text = snackbarMessage.toMessage(resources)
                scaffoldState.snackbarHostState.showSnackbar(text)
            }
        }
    }

    fun popUp() {
        navController.popBackStack()
    }

    fun navigate(route: String) {
        navController.navigate(route) { launchSingleTop = true }
    }

    fun navigateAndPopUp(route: String, popUp: String) {
        navController.navigate(route = route) {
            launchSingleTop = true
            popUpTo(popUp) { inclusive = true }
        }
    }

    fun clearAndNavigate(route: String) {
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(0) { inclusive = true }
        }
    }
}