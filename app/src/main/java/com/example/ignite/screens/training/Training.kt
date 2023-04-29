package com.example.ignite.screens.training

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.ignite.IgniteState
import com.example.ignite.composables.bottombar.MyBottomBar
import com.example.ignite.composables.topbar.MyTopBar
import com.example.ignite.models.User
import com.example.ignite.screens.home.HomeViewModel
import com.example.ignite.utils.Constants
import kotlinx.coroutines.CoroutineScope

@Composable
fun TrainingScreen(
    appState : IgniteState,
    viewModel: TrainingViewModel = hiltViewModel(),
    coroutineScope : CoroutineScope = rememberCoroutineScope(),
    scaffoldState : ScaffoldState = rememberScaffoldState()
){
    val user = viewModel.accountService.currentUser.collectAsState(initial = User())
    val subscriptionsAll by viewModel.subscriptionsAllLiveData.observeAsState()
    val subscriptionsTaken by viewModel.subscriptionsTakenLiveData.observeAsState()
    Scaffold (
        scaffoldState = scaffoldState,
        bottomBar = { MyBottomBar(appState = appState,1,user.value.isAnonymous) },
        topBar = { MyTopBar() }
    ) { _ ->
        Column{
            Column() {
                Text(text = "My Subscriptions", fontSize = 30.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 10.dp,top = 10.dp))
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 0.dp, top = 0.dp),
                ) {
                    items(subscriptionsTaken?.data?: listOf()) {item ->
                        SubscriptionCard(viewModel = viewModel, subsId = item.subsid,userName = item.mentorname, userImage = item.mentorprofilepic, subsDesc = item.subsdesc, subsTitle = item.title, subsCategory = item.category, subsText = "Unsubscribe")
                    }
                }
            }
            Column() {
                Text(text = "Explore", fontSize = 30.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 10.dp,top = 10.dp))
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 50.dp, top = 0.dp),
                ) {
                    items(subscriptionsAll?.data?: listOf()) {item ->
                        SubscriptionCard(viewModel = viewModel, subsId = item.subsid ,show = user.value.isAnonymous, userName = item.mentorname, userImage = item.mentorprofilepic, subsDesc = item.subsdesc, subsTitle = item.title, subsCategory = item.category, subsText = "Subscribe")
                    }
                }
            }
        }
    }

    LaunchedEffect(true) {
        viewModel.onAppStart()
    }
}

@Composable
fun SubscriptionCard(
    userName: String = "User Name",
    userImage: String = "",
    subsDesc: String = "Subs Text",
    subsTitle: String = "Subs Title",
    subsCategory : String = "Subs Category",
    subsText: String = "Subscribe",
    show: Boolean = false,
    viewModel: TrainingViewModel,
    subsId:String
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
            Text(text = subsTitle, fontSize = 20.sp, fontWeight = FontWeight.SemiBold,modifier = Modifier.padding(start = 10.dp))
            Text(text = subsCategory, fontSize = 15.sp, fontWeight = FontWeight.SemiBold,modifier = Modifier.padding(start = 10.dp))
            Text(text = subsDesc, fontSize = 20.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(start = 10.dp, top = 10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom = 10.dp)) {
                    if(userImage=="") Icon(
                        painter = rememberVectorPainter(image = Icons.Default.Person),
                        contentDescription = "User Image",
                        modifier = Modifier
                            .size(25.dp)
                            .clip(CircleShape)
                            .border(
                                width = 1.dp,
                                color = Color.LightGray,
                                shape = CircleShape
                            )
                    )
                    else AsyncImage(
                        model = Constants.BASE_URL + userImage,
                        contentDescription = null,
                        modifier = Modifier
                            .size(25.dp)
                            .clip(CircleShape)
                            .border(
                                width = 1.dp,
                                color = Color.LightGray,
                                shape = CircleShape
                            )
                    )
                    Text(text = userName, fontSize = 10.sp, fontWeight = FontWeight.SemiBold,modifier = Modifier.padding(start = 10.dp))
                }
                if(!show)Button(onClick = {
                    if(subsText=="Subscribe") viewModel.subscribe(subsId = subsId)
                    else viewModel.unsubscribe(subsId)
                    viewModel.onAppStart() },modifier = Modifier.padding(10.dp)) {
                    Text(text = subsText, fontSize = 10.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}