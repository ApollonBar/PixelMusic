package com.kyant.pixelmusic.api.playlist

import kotlinx.serialization.Serializable

@Serializable
data class L(
    val br: Long? = 0,
    val fid: Long? = 0,
    val size: Long? = 0,
    val vd: Long? = 0
)