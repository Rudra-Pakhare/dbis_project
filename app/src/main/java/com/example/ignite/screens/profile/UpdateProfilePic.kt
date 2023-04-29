package com.example.ignite.screens.profile

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.ignite.IgniteRoutes
import com.example.ignite.IgniteState
import com.example.ignite.models.User
import com.example.ignite.models.user.PostUpload
import java.io.File
import java.io.FileOutputStream

@Composable
fun UpdateProfilePic(
    appState: IgniteState,
    viewModel: ProfileViewModel = hiltViewModel(),
    context: Context = LocalContext.current
) {
    val user = viewModel.accountService.currentUser.collectAsState(initial = User())
    var image by remember { mutableStateOf<Uri?>(null) }
    var galleryLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        image = uri
    }
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
                                IgniteRoutes.UpdateProfilePic.route
                            )
                        }
                        .padding(10.dp)
                        .size(30.dp))
                Text(text = "Update ProfilePic", fontSize = 20.sp, fontWeight = FontWeight.Bold)
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
            Button(onClick = {
                galleryLauncher.launch("image/*")
            }) {
                Text(text = "Upload Image")
            }
            if(image != null) Image(painter = rememberAsyncImagePainter(model = image), contentDescription = null)
            Button(onClick = {
                if(image != null) {
                    val cacheFile = File(context.cacheDir, "image.png")
                    val inputStream = context.contentResolver.openInputStream(image!!)
                    val outputStream = FileOutputStream(cacheFile)

                    inputStream?.use { input ->
                        outputStream.use { output ->
                            input.copyTo(output)
                        }
                    }
                    viewModel.postProfileImage(userId = user.value.id, image = cacheFile)
                    appState.navigateAndPopUp(
                        IgniteRoutes.ProfileScreen.route,
                        IgniteRoutes.UpdateProfilePic.route
                    )
                }
            }) {
                Text(text = "Update")
            }
        }
    }
}