package com.ducgao.elinexttest.feats.home.presentation.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest
import com.ducgao.elinexttest.feats.home.presentation.HomeConfigs
import org.koin.compose.koinInject

@Composable
fun GridItemView(url: String, configs: HomeConfigs = koinInject()) {
    var painterState: AsyncImagePainter.State by remember { mutableStateOf(AsyncImagePainter.State.Empty) }

    var retryCount by remember { mutableIntStateOf(0) }

    val model = ImageRequest.Builder(LocalContext.current)
        .data("$url?retry=$retryCount")
        .crossfade(true)
        .listener(onError = { _, _ ->
            retryCount++
        })
        .build()

    AsyncImage(
        model = model,
        contentDescription = url,
        modifier = Modifier
            .aspectRatio(1f)
            .fillMaxWidth()
            .clip(RoundedCornerShape(configs.imageCornerRadius.dp)),
        onState = { state ->
            painterState = state
        }
    )

    if (painterState is AsyncImagePainter.State.Loading) {
        LoadingIndicator()
    }
}

@Composable
private fun LoadingIndicator() {
    Box(
        contentAlignment = Alignment.Center, modifier = Modifier
            .aspectRatio(1f)
            .fillMaxWidth()
    ) {
        CircularProgressIndicator(
            color = Color.Black,
            strokeWidth = 2.dp,
            modifier = Modifier.size(16.dp)
        )
    }
}