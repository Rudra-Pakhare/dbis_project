package com.example.ignite.composables.bottombar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.ignite.IgniteRoutes
import com.example.ignite.IgniteState
import com.example.ignite.R


@Composable fun MyBottomBar(
    appState : IgniteState,
    screen : Int,
    show: Boolean = false
){
    BottomAppBar(
        backgroundColor = Color.White
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            BottomNavigationItem(
                icon = { Icon(painterResource(R.drawable.baseline_person_24), contentDescription = null) },
                label = { Text(text = "Exercise") },
                selected = screen == 0,
                onClick = {appState.navigate(IgniteRoutes.HomeScreen.route)},
                selectedContentColor = MaterialTheme.colors.secondary,
                unselectedContentColor = LocalContentColor.current,
                modifier = Modifier.navigationBarsPadding()
            )
            BottomNavigationItem(
                icon = { Icon(painterResource(R.drawable.baseline_person_24), contentDescription = null) },
                label = { Text(text = "Training") },
                selected = screen == 1,
                onClick = {appState.navigate(IgniteRoutes.TrainingScreen.route)},
                selectedContentColor = MaterialTheme.colors.secondary,
                unselectedContentColor = LocalContentColor.current,
                modifier = Modifier.navigationBarsPadding()
            )
            BottomNavigationItem(
                icon = { Icon(painterResource(R.drawable.baseline_person_24), contentDescription = null) },
                label = { Text(text = "Feed") },
                selected = screen == 2,
                onClick = {appState.navigate(IgniteRoutes.FeedScreen.route)},
                selectedContentColor = MaterialTheme.colors.secondary,
                unselectedContentColor = LocalContentColor.current,
                modifier = Modifier.navigationBarsPadding()
            )
            if(!show)BottomNavigationItem(
                icon = { Icon(painterResource(R.drawable.baseline_person_24), contentDescription = null) },
                label = { Text(text = "Chat") },
                selected = screen == 3,
                onClick = {appState.navigate(IgniteRoutes.ChatScreen.route)},
                selectedContentColor = MaterialTheme.colors.secondary,
                unselectedContentColor = LocalContentColor.current,
                modifier = Modifier.navigationBarsPadding()
            )
            BottomNavigationItem(
                icon = { Icon(painterResource(R.drawable.baseline_person_24), contentDescription = null) },
                label = { Text(text = "Profile") },
                selected = screen == 4,
                onClick = {appState.navigate(IgniteRoutes.ProfileScreen.route)},
                selectedContentColor = MaterialTheme.colors.secondary,
                unselectedContentColor = LocalContentColor.current,
                modifier = Modifier.navigationBarsPadding()
            )
        }
    }
}

