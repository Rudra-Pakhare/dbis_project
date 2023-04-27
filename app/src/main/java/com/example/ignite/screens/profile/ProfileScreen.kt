package com.example.ignite.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.NavigationRailItem
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ignite.IgniteRoutes
import com.example.ignite.IgniteState
import com.example.ignite.composables.bottombar.MyBottomBar
import com.example.ignite.composables.topbar.MyTopBar
import com.example.ignite.models.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.example.ignite.R.drawable as AppImages

@Composable
fun ProfileScreen(
    appState : IgniteState,
    viewModel: ProfileViewModel = hiltViewModel(),
    scaffoldState : ScaffoldState = rememberScaffoldState(),
    coroutineScope : CoroutineScope = rememberCoroutineScope(),
){
    val user = viewModel.accountService.currentUser.collectAsState(initial = User())
    var tab by remember { mutableStateOf(0) }
    var count by remember { mutableStateOf(0) }
    Scaffold (
        scaffoldState = scaffoldState,
        bottomBar = { MyBottomBar(appState = appState,4) },
        drawerContent = { Button(onClick = { viewModel.onLogoutClick(); appState.clearAndNavigate(IgniteRoutes.HomeScreen.route) }) {
            Text(text = "Log Out")
        }},
        topBar = { MyTopBar() }
    ) { _ ->
        Column(
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(5.dp))
            ProfileSection(user = ""+if(user.value.isAnonymous)"Guest" else user.value.name)
            Spacer(modifier = Modifier.height(10.dp))
            Divider()
            TabRow(selectedTabIndex = tab, modifier = Modifier.height(60.dp)) {
                if(!user.value.isAnonymous){
                    Tab(selected = tab==0, onClick = { tab=0 }, text={Text(text="post")})
                    Tab(selected = tab==1, onClick = { tab=1 }, text={Text(text="subscription")})
                    Tab(selected = tab==2, onClick = { coroutineScope.launch {
                        scaffoldState.drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    } }, text={Text(text="settings")})
                }
                else {
                    Tab(
                        selected = tab==0,
                        onClick = { appState.navigate(IgniteRoutes.SignUpScreen.route) },
                        text={
                            Text(text = "sign up")
                        })
                }
            }
            if(tab==0 && !user.value.isAnonymous){
                ShowPost(count = count, viewModel = viewModel){ appState.navigate(IgniteRoutes.PostForm.route) }
            }
            else if(tab==1){
                ShowSubscription(count = count, viewModel = viewModel){ appState.navigate(IgniteRoutes.SubscriptionForm.route) }
            }
        }
    }
}

@Composable fun ShowPost(
    count : Int,
    viewModel: ProfileViewModel,
    onclk : ()->Unit,
){
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onclk){
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        },
        modifier = Modifier.padding(bottom = 50.dp)
    ) {
        _ ->
        LazyColumn(modifier = Modifier.fillMaxWidth()){
            items(count = count){ Posts() }
        }
    }

}

@Composable fun Posts(){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(15.dp)
            .clickable { },
        elevation = 10.dp
    ) {
        Text(text = "post", fontSize = 50.sp)
    }
}

@Composable fun ShowSubscription(
    count : Int,
    viewModel: ProfileViewModel,
    onclk : ()->Unit,
    ){
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(onClick = onclk){
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                }
            },
            modifier = Modifier.padding(bottom = 50.dp)
        ) {
                _ ->
            LazyColumn(modifier = Modifier.fillMaxWidth()){
                items(count = count){ Subscription() }
            }
        }
}

@Composable fun Subscription(){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(15.dp)
            .clickable { },
        elevation = 10.dp
    ) {
        Text(text = "subscription", fontSize = 50.sp)
    }
}

@Composable
fun ProfileSection(
    user: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth()
    ) {
        RoundImage(
            image = painterResource(id = AppImages.baseline_person_24),
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(text = user)
    }
}

@Composable
fun RoundImage(
    image: Painter,
    modifier: Modifier = Modifier
) {
    Image(
        painter = image,
        contentDescription = null,
        modifier = modifier
            .aspectRatio(1f, matchHeightConstraintsFirst = true)
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = CircleShape
            )
            .padding(3.dp)
            .clip(CircleShape)
    )
}
