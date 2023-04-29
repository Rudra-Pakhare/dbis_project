package com.example.ignite.screens.home

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.example.ignite.IgniteRoutes
import com.example.ignite.IgniteState
import com.example.ignite.utils.Constants

@Composable
fun Exercise(
    appState : IgniteState,
    exercise: String,
    viewModel: HomeViewModel,
) {
    val exercises by viewModel.exerciseResponseLiveData.observeAsState()
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
    Scaffold (
        topBar = {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back", modifier = Modifier
                    .clickable {
                        appState.navigateAndPopUp(
                            IgniteRoutes.HomeScreen.route,
                            IgniteRoutes.Exercise.route + "/" + exercise
                        )
                    }
                    .padding(7.dp)
                    .size(40.dp))
                Text(text = exercise, fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(end = 0.dp))
            }
        }
    ){
        _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp),
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize().verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(context).data(data = Constants.BASE_URL + exercises?.data?.gifpath).apply(block = {
                            size(Size.ORIGINAL)
                        }).build() , imageLoader),
                    contentDescription = null,
                    modifier = Modifier
                        .size(350.dp)
                )
                Text(text = "Steps", fontSize = 30.sp, fontWeight = FontWeight.Bold)
                Text(text = exercises?.data?.steps ?: "", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(30.dp,top=10.dp))
            }
        }
    }
    LaunchedEffect(true) {
        viewModel.onExerciseClick(exercise)
    }
}