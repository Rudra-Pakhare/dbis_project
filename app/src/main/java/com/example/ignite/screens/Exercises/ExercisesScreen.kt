package com.example.ignite.screens.Exercises


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import android.os.Build.VERSION.SDK_INT
import androidx.compose.material.Text
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder


@Composable
private fun Greeting(name: String) {
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

    Image(
        painter = rememberAsyncImagePainter(model = "https://compote.slate.com/images/697b023b-64a5-49a0-8059-27b963453fb1.gif?crop=780%2C520%2Cx0%2Cy0&width=1280", imageLoader),
        contentDescription = null,
        modifier = Modifier.fillMaxSize()
    )
//    AsyncImage(
//        model = "https://cdn.mos.cms.futurecdn.net/4AZaC73zUNFBsAGiuaK9E9-970-80.jpeg.webp",
//        contentDescription = "Translated description of what the image contains"
//    )
        Text(text = "Hello $name!", modifier = Modifier.padding(24.dp))
}

@Composable
private fun Exercises(
    modifier: Modifier = Modifier,
    names: List<String> = List(1000) { "$it" }
) {
    LazyColumn(modifier = modifier.padding(vertical = 4.dp).fillMaxWidth()) {

        items(items = names) { name ->
            Greeting(name = name)
        }
    }

}

@Composable
fun ExercisesScreen(
    openAndPopUp: (String, String) -> Unit,
){
    Exercises()
}
