package com.example.ignite.screens.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ignite.IgniteRoutes
import com.example.ignite.IgniteState
import com.example.ignite.models.User
import com.example.ignite.models.user.PostUpload

@Composable
fun PostForm(
    appState: IgniteState,
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    val user = viewModel.accountService.currentUser.collectAsState(initial = User())
    var desc by remember { mutableStateOf("") }
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
                                IgniteRoutes.PostForm.route
                            )
                        }
                        .padding(10.dp)
                        .size(30.dp))
                Text(text = "Upload Post", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
        }
    ){
        _ ->
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            TextField(
                value = desc,
                onValueChange = { desc = it },
                label = { Text("Description") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )
            Button(onClick = {
                viewModel.postUpload(PostUpload(postDesc = desc, userId = user.value.id))
                appState.navigateAndPopUp(
                    IgniteRoutes.ProfileScreen.route,
                    IgniteRoutes.PostForm.route
                )
            }) {
                Text(text = "Upload")
            }
        }
    }
}