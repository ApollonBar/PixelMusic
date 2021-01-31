package com.kyant.pixelmusic.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kyant.pixelmusic.locals.LocalAmplitudes
import com.kyant.pixelmusic.locals.LocalPixelPlayer
import com.kyant.pixelmusic.util.normalize

@Composable
fun AmplitudeVisualizer(modifier: Modifier = Modifier) {
    val state = rememberLazyListState()
    val amplitudes = LocalAmplitudes.current
    val player = LocalPixelPlayer.current
    LaunchedEffect(player.progress) {
        state.snapToItemIndex((state.layoutInfo.totalItemsCount * player.progress).toInt())
    }
    BoxWithConstraints(modifier) {
        Box(
            Modifier
                .preferredWidth(2.dp)
                .preferredHeight(72.dp)
                .background(
                    MaterialTheme.colors.secondary.copy(ContentAlpha.medium),
                    RoundedCornerShape(50)
                )
                .align(Alignment.Center)
        )
        LazyRow(
            Modifier.align(Alignment.Center),
            state,
            PaddingValues(start = maxWidth / 2, end = maxWidth / 2),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(amplitudes) {
                Box(
                    Modifier
                        .preferredWidth(2.dp)
                        .preferredHeight(it.normalize().dp * 32)
                        .background(MaterialTheme.colors.primary, RoundedCornerShape(50))
                )
                Spacer(Modifier.preferredWidth(5.dp))
            }
        }
    }
}