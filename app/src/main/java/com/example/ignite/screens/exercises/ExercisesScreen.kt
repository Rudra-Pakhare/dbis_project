package com.example.ignite.screens.exercises


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
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.example.ignite.R.drawable as AppImages


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
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(context).data(data = AppImages.abs).apply(block = {
                size(Size.ORIGINAL)
            }).build() , imageLoader),
        contentDescription = null,
        modifier = Modifier.fillMaxSize()
    )
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
