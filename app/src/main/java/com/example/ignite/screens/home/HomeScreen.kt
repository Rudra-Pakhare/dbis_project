package com.example.ignite.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ignite.IgniteRoutes
import com.example.ignite.screens.login.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    openAndPopUp: (String, String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
    coroutineScope : CoroutineScope = rememberCoroutineScope(),
    scaffoldState : ScaffoldState = rememberScaffoldState()
){

    Scaffold (
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar {
                Button(onClick = { coroutineScope.launch { scaffoldState.drawerState.open() } }) {
                    Text(text = "button")
                }
            }
        },
        drawerContent = {
            DrawerContent()
        },
        bottomBar = {
            BottomAppBar {
                Button(onClick = { coroutineScope.launch { scaffoldState.drawerState.open() } }) {
                    Text(text = "button")
                }
            }
        }
    ) { _ ->
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Home Screen", fontSize = 40.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = "to logout",
                Modifier.clickable { viewModel.onLogoutClick(openAndPopUp) },
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun DrawerContent(){
    Text(text = "hi")
}