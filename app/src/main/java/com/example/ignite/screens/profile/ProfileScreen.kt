package com.example.ignite.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ignite.IgniteRoutes
import com.example.ignite.models.User
import com.example.ignite.R.drawable as AppImages

@Composable
fun ProfileScreen(
    openAndPopUp: (String, String) -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
){
    val user = viewModel.accountService.currentUser.collectAsState(initial = User())
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = AppImages.baseline_person_24),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(16.dp)))
        Text(text = "Profile Screen", fontSize = 40.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { openAndPopUp(IgniteRoutes.HomeScreen.route,IgniteRoutes.ProfileScreen.route) }) {
                Text(text = "to home", fontSize = 20.sp)
            }
            if(user.value.isAnonymous)
                Button(onClick = { openAndPopUp(IgniteRoutes.SignUpScreen.route,IgniteRoutes.ProfileScreen.route) }) {
                    Text(text = "sign up", fontSize = 20.sp)
                }
            else
                Button(onClick = { viewModel.onLogoutClick() }) {
                    Text(text = "log out", fontSize = 20.sp)
                }
        }
    }
}