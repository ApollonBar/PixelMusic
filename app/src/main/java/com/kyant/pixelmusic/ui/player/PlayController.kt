package com.kyant.pixelmusic.ui.player

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kyant.pixelmusic.locals.LocalPixelPlayer
import com.kyant.pixelmusic.media.Media

@SuppressLint("ModifierParameter")
@Composable
fun PlayController(
    onFavoriteButtonClick: () -> Unit,
    onInfoButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val player = LocalPixelPlayer.current
    Row(modifier) {
        IconButton(onFavoriteButtonClick) {
            Icon(Icons.Outlined.FavoriteBorder, "Favorite")
        }
        Spacer(Modifier.width(16.dp))
        IconButton({
            player.previous()
            player.snapTo(0)
        }) {
            Icon(Icons.Outlined.SkipPrevious, "Skip to prevoius")
        }
        Spacer(Modifier.width(16.dp))
        PlayPauseButton()
        Spacer(Modifier.width(16.dp))
        IconButton({
            player.next()
            player.snapTo(0)
        }) {
            Icon(Icons.Outlined.SkipNext, "Skip to next")
        }
        Spacer(Modifier.width(16.dp))
        IconButton(onInfoButtonClick) {
            Icon(Icons.Outlined.Info, "Info")
        }
    }
}

@SuppressLint("ModifierParameter")
@Composable
fun PlayPauseButton(modifier: Modifier = Modifier) {
    val player = LocalPixelPlayer.current
    IconButton(
        {
            if (Media.browser.isConnected) {
                Media.session?.isActive = true
                player.playOrPause()
            }
        },
        modifier.background(
            animateColorAsState(MaterialTheme.colors.primary).value,
            RoundedCornerShape(50)
        )
    ) {
        Icon(
            if (player.isPlayingState) Icons.Outlined.Pause else Icons.Outlined.PlayArrow,
            if (player.isPlayingState) "Pause" else "Play",
            tint = MaterialTheme.colors.onPrimary
        )
    }
}

@Composable
fun PlayPauseTransparentButton(modifier: Modifier = Modifier) {
    val player = LocalPixelPlayer.current
    Box(modifier) {
        IconButton(
            {
                if (Media.browser.isConnected) {
                    player.playOrPause()
                }
            },
            Modifier.background(Color.Transparent, RoundedCornerShape(50))
        ) {
            Icon(
                if (player.isPlayingState) Icons.Outlined.Pause else Icons.Outlined.PlayArrow,
                if (player.isPlayingState) "Pause" else "Play"
            )
        }
        CircularProgressIndicator(
            player.bufferedProgress,
            Modifier.size(48.dp),
            color = MaterialTheme.colors.primary.copy(0.4f),
            strokeWidth = 3.dp
        )
        CircularProgressIndicator(
            player.progress,
            Modifier.size(48.dp),
            strokeWidth = 3.dp
        )
    }
}