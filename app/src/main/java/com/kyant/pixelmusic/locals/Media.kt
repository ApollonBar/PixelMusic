package com.kyant.pixelmusic.locals

import android.content.ComponentName
import android.content.Context
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.session.MediaSessionCompat
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.kyant.pixelmusic.media.*
import com.kyant.pixelmusic.util.DataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

object Media {
    const val NOTIFICATION_CHANNEL_ID = "Pixel Music"
    const val MEDIA_ROOT_ID = "media_root_id"
    const val EMPTY_MEDIA_ROOT_ID = "empty_root_id"

    var player: PixelPlayer? by mutableStateOf(null)
    lateinit var browser: MediaBrowserCompat
    var session: MediaSessionCompat? by mutableStateOf(null)

    private lateinit var dataSourceFactory: DataSource.Factory
    val songs: SnapshotStateList<Song> = mutableStateListOf<Song>().onEach {
        session?.controller?.addQueueItem(it.toMediaDescription())
    }
    var nowPlaying: Song? by mutableStateOf(null)

    fun init(
        context: Context,
        connectionCallbacks: MediaBrowserCompat.ConnectionCallback
    ) {
        browser = MediaBrowserCompat(
            context,
            ComponentName(context, MediaPlaybackService::class.java),
            connectionCallbacks,
            null
        )
        dataSourceFactory = DefaultDataSourceFactory(
            context,
            Util.getUserAgent(context, "Pixel Music")
        )
    }

    fun syncPlaylistsToLocal(context: Context) {
        CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {
            DataStore(context, "playlists")
                .write("playlist_0", songs.map { it.serialize() })
            DataStore(context, "playlists")
                .write("playlist_0_state", player?.currentWindowIndex to player?.currentPosition)
        }
    }

    fun syncWithPlaylists(context: Context) {
        CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {
            DataStore(context, "playlists")
                .getOrNull<List<SerializedSong>>("playlist_0")
                ?.map { it.toSong(context) }
                ?.let { syncSongsWithPlaylists(it) }
            DataStore(context, "playlists")
                .getOrNull<Pair<Int?, Long?>>("playlist_0_state")?.let {
                    player?.seekTo(it.first ?: 0, it.second ?: 0)
                    player?.position?.snapTo(it.second?.toFloat() ?: 0f)
                }
        }
    }

    fun restore() {
        player = null
        session = null
        songs.clear()
        nowPlaying = null
    }

    fun addSongToPlaylist(index: Int, song: Song) {
        songs.add(index, song)
        val source = ProgressiveMediaSource
            .Factory(dataSourceFactory)
            .createMediaSource(MediaItem.fromUri(song.mediaUrl!!))
        player?.addMediaSource(index, source)
    }

    private fun syncSongsWithPlaylists(songList: List<Song>) {
        songs.addAll(songList)
        val sources = songList.map {
            ProgressiveMediaSource
                .Factory(dataSourceFactory)
                .createMediaSource(MediaItem.fromUri(it.mediaUrl!!))
        }
        player?.setMediaSources(sources)
    }
}