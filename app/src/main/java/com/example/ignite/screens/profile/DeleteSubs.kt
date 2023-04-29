package com.example.ignite.screens.profile

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.ignite.IgniteRoutes
import com.example.ignite.IgniteState
import com.example.ignite.models.User
import com.example.ignite.utils.Constants

@Composable
fun DeleteSubs(
    appState : IgniteState,
    viewModel: ProfileViewModel = hiltViewModel(),
    scaffoldState : ScaffoldState = rememberScaffoldState()
){
    val user = viewModel.accountService.currentUser.collectAsState(initial = User())
    val subscriptions by viewModel.subscriptionLiveData.observeAsState()
    Scaffold (
        topBar = {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    Icons.Filled.ArrowBack, contentDescription = "Back", modifier = Modifier
                        .clickable {
                            appState.navigateAndPopUp(
                                IgniteRoutes.ProfileScreen.route,
                                IgniteRoutes.DeleteSubs.route
                            )
                        }
                        .padding(10.dp)
                        .size(30.dp))
                Text(text = "Delete Subscription", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
        }
    ) { _ ->
        LazyColumn(modifier = Modifier.fillMaxWidth()){
            items(subscriptions?.data?: listOf()){ subs ->
                SubsDelete(viewModel=viewModel,subsDesc = subs.subsdesc, subsTitle = subs.title, subsCategory = subs.category,subsId = subs.subsid)
            }
        }
    }

    LaunchedEffect(true) {
        viewModel.getSubscription()
    }
}

@Composable
fun SubsDelete(
    subsDesc: String = "Subs Text",
    subsTitle: String = "Subs Title",
    subsCategory : String = "Subs Category",
    subsId: String = "Subs Id",
    viewModel: ProfileViewModel
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable { },
        elevation = 10.dp
    ) {
        Column(

        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 10.dp, bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = { viewModel.deleteSubs(subsId)
                    viewModel.getSubscription() }) {
                    Text(text = "Delete", fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
                }
            }
            Text(text = subsTitle, fontSize = 20.sp, fontWeight = FontWeight.SemiBold,modifier = Modifier.padding(start = 10.dp))
            Text(text = subsCategory, fontSize = 15.sp, fontWeight = FontWeight.SemiBold,modifier = Modifier.padding(start = 10.dp))
            Text(text = subsDesc, fontSize = 20.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(start = 10.dp, top = 10.dp))
        }
    }
}