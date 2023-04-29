package com.example.ignite.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ignite.IgniteRoutes
import com.example.ignite.IgniteState
import com.example.ignite.composables.bottombar.MyBottomBar
import com.example.ignite.composables.topbar.MyTopBar
import com.example.ignite.models.User

@Composable
fun HomeScreen(
    appState : IgniteState,
    viewModel: HomeViewModel,
    scaffoldState : ScaffoldState = rememberScaffoldState()
){
    val user = viewModel.accountService.currentUser.collectAsState(initial = User())
    val exercises by viewModel.exercisesResponseLiveData.observeAsState()
    Scaffold (
        scaffoldState = scaffoldState,
        bottomBar = {MyBottomBar(appState = appState,0,user.value.isAnonymous)},
        topBar = { MyTopBar() }
    ) { _ ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, bottom = 65.dp, top = 10.dp),
        ) {
            itemsIndexed(exercises?.data?.categories ?: listOf()) { index, exercise ->
                ColumnItem(text = exercise, exerciseX = exercises?.data?.exercises?.get(index) ?: listOf(), appState = appState)
            }
        }
    }

    LaunchedEffect(true) {
        viewModel.onAppStart()
    }
}

@Composable
fun ColumnItem(
    text: String,
    exerciseX: List<String>,
    appState: IgniteState,
){
    Text(text = text, fontSize = 30.sp, fontWeight = FontWeight.Bold)
    LazyRow(
        modifier = Modifier.padding(bottom = 20.dp, top = 0.dp),
    ) {
        itemsIndexed(exerciseX) { index, exercise ->
            ExerciseCard(exercise = exercise, appState = appState)
        }
    }
}

@Composable
fun ExerciseCard(
    exercise : String,
    appState: IgniteState
) {
    Card(
        modifier = Modifier
            .height(125.dp)
            .padding(5.dp)
            .clickable { appState.navigate(IgniteRoutes.Exercise.route + "/" + exercise) },
        elevation = 10.dp
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = exercise, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}
