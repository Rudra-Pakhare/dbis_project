package com.example.ignite.screens.feeds

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ignite.IgniteState
import com.example.ignite.composables.bottombar.MyBottomBar
import com.example.ignite.composables.topbar.MyTopBar
import com.example.ignite.models.User
import com.example.ignite.utils.Constants

@Composable fun FeedScreen(
    appState : IgniteState,
    viewModel: FeedViewModel,
    scaffoldState : ScaffoldState = rememberScaffoldState()
){
    val user = viewModel.accountService.currentUser.collectAsState(initial = User())
    val posts by viewModel.postsLiveData.observeAsState()
    Scaffold (
        scaffoldState = scaffoldState,
        bottomBar = { MyBottomBar(appState = appState,2,user.value.isAnonymous) },
        topBar = { MyTopBar() }
    ) { _ ->
        Text(text = "Posts", fontSize = 30.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 10.dp,top = 10.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 60.dp, top = 50.dp),
        ) {
            items(posts?.data?: listOf()) {post ->
                PostCard(postImage = post.postimgpath, postDesc = post.postdescr, postDate = post.postdate, userName = post.username, userImage = post.profilepicpath)
            }
        }
    }

    LaunchedEffect(true) {
        viewModel.onAppStart()
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