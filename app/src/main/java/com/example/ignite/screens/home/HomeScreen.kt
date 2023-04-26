package com.example.ignite.screens.home

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ignite.IgniteRoutes
import com.example.ignite.IgniteState
import com.example.ignite.R
import com.example.ignite.composables.bottombar.MyBottomBar
import com.example.ignite.composables.topbar.MyTopBar
import com.example.ignite.models.User
import com.example.ignite.screens.login.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun HomeScreen(
    appState : IgniteState,
    viewModel: HomeViewModel = hiltViewModel(),
    coroutineScope : CoroutineScope = rememberCoroutineScope(),
    scaffoldState : ScaffoldState = rememberScaffoldState()
){
    val user = viewModel.accountService.currentUser.collectAsState(initial = User())
    Scaffold (
        scaffoldState = scaffoldState,
        bottomBar = {MyBottomBar(appState = appState,0)},
        topBar = { MyTopBar() }
    ) { _ ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, bottom = 50.dp,top = 5.dp),
        ) {
            items(listOf("My Workouts","FullBody","Chest","Legs","Abs","Back","Arms")){
                item ->  ColumnItem(text = item)
            }
        }
    }

    LaunchedEffect(true) {
        viewModel.onAppStart()
    }
}

@Composable
fun ColumnItem(
    text: String
){
    Text(text = text, fontSize = 30.sp, fontWeight = FontWeight.Bold)
    LazyRow {
        items(count = 10){ ExerciseCard() }
    }
}

@Composable
fun ExerciseCard() {
    Card(
        modifier = Modifier
            .width(200.dp)
            .height(200.dp)
            .padding(15.dp)
            .clickable { },
        elevation = 10.dp
    ) {
        Text(text = "Exercise")
    }
}
