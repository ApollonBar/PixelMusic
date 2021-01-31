package com.kyant.pixelmusic.media

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.support.v4.media.session.PlaybackStateCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.media.session.MediaButtonReceiver
import com.kyant.pixelmusic.R
import com.kyant.pixelmusic.locals.Media

fun Context.createNotificationChannel(
    channelId: String,
    name: String
) {
    ContextCompat.getSystemService(this, NotificationManager::class.java)?.apply {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, name, NotificationManager.IMPORTANCE_LOW)
            createNotificationChannel(channel)
        }
    }
}

fun Context.buildMediaStyleNotification(channelId: String): NotificationCompat.Builder {
    val mediaSession = Media.session!!
    val mediaMetadata = mediaSession.controller.metadata
    val description = mediaMetadata.description
    return NotificationCompat.Builder(this, channelId).apply {
        setContentTitle(description.title)
        setContentText(description.subtitle)
        setSubText(description.description)
        setLargeIcon(description.iconBitmap)
        setContentIntent(mediaSession.controller.sessionActivity)
        setDeleteIntent(
            MediaButtonReceiver.buildMediaButtonPendingIntent(
                this@buildMediaStyleNotification,
                PlaybackStateCompat.ACTION_STOP
            )
        )
        setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
        setSmallIcon(R.drawable.ic_launcher_foreground)
        color = ContextCompat.getColor(this@buildMediaStyleNotification, R.color.black)
        addAction(
            R.drawable.ic_baseline_skip_previous_24,
            "Forward",
            MediaButtonReceiver.buildMediaButtonPendingIntent(
                this@buildMediaStyleNotification,
                PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS
            )
        )
        addAction(
            if (Media.player?.isPlayingState == true) R.drawable.ic_baseline_pause_24
            else R.drawable.ic_baseline_play_arrow_24,
            if (Media.player?.isPlayingState == true) "Pause" else "Play",
            MediaButtonReceiver.buildMediaButtonPendingIntent(
                this@buildMediaStyleNotification,
                PlaybackStateCompat.ACTION_PLAY_PAUSE
            )
        )
        addAction(
            R.drawable.ic_baseline_skip_next_24,
            "Next",
            MediaButtonReceiver.buildMediaButtonPendingIntent(
                this@buildMediaStyleNotification,
                PlaybackStateCompat.ACTION_SKIP_TO_NEXT
            )
        )
        setStyle(
            androidx.media.app.NotificationCompat.MediaStyle()
                .setMediaSession(mediaSession.sessionToken)
                .setShowActionsInCompactView(1)
                .setShowCancelButton(true)
                .setCancelButtonIntent(
                    MediaButtonReceiver.buildMediaButtonPendingIntent(
                        this@buildMediaStyleNotification,
                        PlaybackStateCompat.ACTION_STOP
                    )
                )
        )
    }
}