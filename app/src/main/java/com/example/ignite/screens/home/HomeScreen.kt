package com.example.ignite.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ignite.IgniteRoutes
import com.example.ignite.models.User
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
    val user = viewModel.accountService.currentUser.collectAsState(initial = User())
    Scaffold (
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomAppBar(
                backgroundColor = Color.White
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    Button(onClick = { coroutineScope.launch { scaffoldState.drawerState.open() } }) {
                        Text(text = "training")
                    }
                    Button(onClick = { coroutineScope.launch { scaffoldState.drawerState.open() } }) {
                        Text(text = "exercises")
                    }
                    Button(onClick = { openAndPopUp(IgniteRoutes.ProfileScreen.route,IgniteRoutes.HomeScreen.route) }) {
                        Text(text = "me")
                    }
                }
            }
        }
    ) { _ ->
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Home Screen,hi " + if(user.value.isAnonymous)"Guest" else user.value.name, fontSize = 40.sp, fontWeight = FontWeight.Bold)
        }
    }

    LaunchedEffect(true) {
        viewModel.onAppStart()
    }
}