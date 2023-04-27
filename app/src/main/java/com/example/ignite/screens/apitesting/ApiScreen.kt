package com.example.ignite.screens.apitesting

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ignite.IgniteState
import com.example.ignite.composables.bottombar.MyBottomBar
import com.example.ignite.composables.topbar.MyTopBar
import com.example.ignite.models.User
import com.example.ignite.screens.home.HomeViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun ApiScreen(
    appState : IgniteState,
    viewModel: ApiViewModel = hiltViewModel(),
    coroutineScope : CoroutineScope = rememberCoroutineScope(),
    scaffoldState : ScaffoldState = rememberScaffoldState()
){

    Scaffold (
        scaffoldState = scaffoldState,
    ) { _ ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, bottom = 50.dp,top = 5.dp),
        ) {

        }
    }

    LaunchedEffect(true) {
        viewModel.onAppStart()
    }
}

