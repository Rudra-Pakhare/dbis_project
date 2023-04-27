package com.example.ignite.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
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
import com.example.ignite.models.general.ExerciseX
import kotlinx.coroutines.CoroutineScope

@Composable
fun HomeScreen(
    appState : IgniteState,
    viewModel: HomeViewModel = hiltViewModel(),
    coroutineScope : CoroutineScope = rememberCoroutineScope(),
    scaffoldState : ScaffoldState = rememberScaffoldState()
){
    val user = viewModel.accountService.currentUser.collectAsState(initial = User())
    val exercises by viewModel.exerciseResponseLiveData.observeAsState()
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
            itemsIndexed(exercises?.data?.categories ?: listOf()) { index, exercise ->
                ColumnItem(text = exercise, exerciseX = exercises?.data?.exercises?.get(index) ?: listOf())
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
    exerciseX: List<ExerciseX>
){
    Text(text = text, fontSize = 30.sp, fontWeight = FontWeight.Bold)
    LazyRow {
        items(exerciseX) { exercise ->
            ExerciseCard(exercise = exercise)
        }
    }
}

@Composable
fun ExerciseCard(
    exercise : ExerciseX
) {
    Card(
        modifier = Modifier
            .width(200.dp)
            .height(200.dp)
            .padding(15.dp)
            .clickable { },
        elevation = 10.dp
    ) {
        Text(text = exercise.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
    }
}
