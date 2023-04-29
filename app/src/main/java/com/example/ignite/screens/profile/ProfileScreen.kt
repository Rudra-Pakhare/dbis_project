package com.example.ignite.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.ignite.IgniteRoutes
import com.example.ignite.IgniteState
import com.example.ignite.composables.bottombar.MyBottomBar
import com.example.ignite.composables.topbar.MyTopBar
import com.example.ignite.models.User
import com.example.ignite.utils.Constants
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
    val profilePic = viewModel.profileLiveData.observeAsState()
    Scaffold (
        scaffoldState = scaffoldState,
        bottomBar = { MyBottomBar(appState = appState,4,user.value.isAnonymous) },
        drawerContent = {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = { appState.navigateAndPopUp(IgniteRoutes.UpdateProfilePic.route,IgniteRoutes.ProfileScreen.route) }) {
                    Text(text = "Edit Profile Pic")
                }
                Button(onClick = { appState.navigateAndPopUp(IgniteRoutes.DeletePost.route,IgniteRoutes.ProfileScreen.route) }) {
                    Text(text = "Delete Post")
                }
                Button(onClick = { appState.navigateAndPopUp(IgniteRoutes.DeleteSubs.route,IgniteRoutes.ProfileScreen.route) }) {
                    Text(text = "Delete Subscription")
                }
                Button(onClick = { viewModel.onLogoutClick(); appState.clearAndNavigate(IgniteRoutes.HomeScreen.route) }) {
                    Text(text = "Log Out")
                }
            }
        },
        drawerGesturesEnabled = !user.value.isAnonymous,
        topBar = { MyTopBar() }
    ) { _ ->
        Column(
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(5.dp))
            ProfileSection(user = ""+if(user.value.isAnonymous)"Guest" else user.value.name, profilePic = profilePic.value?.data?.profilepicpath?:"")
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
                ShowPost(count = count, viewModel = viewModel){ appState.navigateAndPopUp(IgniteRoutes.PostForm.route,IgniteRoutes.ProfileScreen.route) }
            }
            else if(tab==1){
                ShowSubscription(count = count, viewModel = viewModel){ appState.navigateAndPopUp(IgniteRoutes.SubscriptionForm.route,IgniteRoutes.ProfileScreen.route) }
            }
        }
    }

    LaunchedEffect(true) {
        viewModel.onAppStart()
    }
}

@Composable fun ShowPost(
    count : Int,
    viewModel: ProfileViewModel,
    onclk : ()->Unit,
){
    val posts by viewModel.postLiveData.observeAsState()
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
            items(posts?.data?: listOf()){ post ->
                PostCard(postImage = post.postimgpath, postDesc = post.postdescr, postDate = post.postdate, userName = post.username, userImage = post.profilepicpath)
            }
        }
    }

}

@Composable
fun PostCard(
    userName: String = "User Name",
    userImage: String = "",
    postImage: String = "",
    postDesc: String = "Post Text",
    postDate: String = "Post Date",
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable { },
        elevation = 10.dp
    ) {
        Column{
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 10.dp, bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if(userImage=="") Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "User Image",
                    modifier = Modifier
                        .size(50.dp)
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
                        .size(50.dp)
                        .clip(CircleShape)
                        .border(
                            width = 1.dp,
                            color = Color.LightGray,
                            shape = CircleShape
                        )
                )
                Text(text = userName, fontSize = 20.sp, fontWeight = FontWeight.SemiBold,modifier = Modifier.padding(start = 10.dp))
            }
            if(postImage != "") AsyncImage(model = Constants.BASE_URL + postImage, contentDescription = null)
            Text(text = postDesc, fontSize = 20.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(start = 10.dp, top = 10.dp))
            Text(text = postDate, fontSize = 15.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(start = 10.dp, top = 10.dp))
        }
    }
}

@Composable fun ShowSubscription(
    count : Int,
    viewModel: ProfileViewModel,
    onclk : ()->Unit,
){
    val subscriptions by viewModel.subscriptionLiveData.observeAsState()
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
            items(subscriptions?.data?: listOf()){ subs ->
                SubscriptionCard(subsDesc = subs.subsdesc, subsTitle = subs.title, subsCategory = subs.category)
            }
        }
    }
}

@Composable
fun SubscriptionCard(
    subsDesc: String = "Subs Text",
    subsTitle: String = "Subs Title",
    subsCategory : String = "Subs Category",
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
        }
    }
}

@Composable
fun ProfileSection(
    user: String,
    profilePic:String = "",
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth()
    ) {
        if(profilePic=="") Icon(
            painter = rememberVectorPainter(image = Icons.Default.Person),
            contentDescription = "User Image",
            modifier = Modifier
                .size(100.dp)
                .aspectRatio(1f, matchHeightConstraintsFirst = true)
                .border(
                    width = 1.dp,
                    color = Color.LightGray,
                    shape = CircleShape
                )
                .padding(3.dp)
                .clip(CircleShape)
        )
        else AsyncImage(
            model = Constants.BASE_URL + profilePic,
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .aspectRatio(1f, matchHeightConstraintsFirst = true)
                .border(
                    width = 1.dp,
                    color = Color.LightGray,
                    shape = CircleShape
                )
                .padding(3.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(text = user)
    }
}
