package com.kyant.pixelmusic.api.toplist

data class TopList(
    val subscribers: List<Any>? = listOf(),
    val subscribed: Any? = Any(),
    val creator: Any? = Any(),
    val artists: Any? = Any(),
    val tracks: Any? = Any(),
    val updateFrequency: String? = "",
    val backgroundCoverId: Long? = 0,
    val backgroundCoverUrl: Any? = Any(),
    val titleImage: Int? = 0,
    val titleImageUrl: Any? = Any(),
    val englishTitle: Any? = Any(),
    val opRecommend: Boolean? = false,
    val recommendInfo: Any? = Any(),
    val description: String? = "",
    val specialType: Int? = 0,
    val ordered: Boolean? = false,
    val coverImgUrl: String? = "",
    val totalDuration: Int? = 0,
    val updateTime: Long? = 0,
    val trackCount: Int? = 0,
    val commentThreadId: String? = "",
    val createTime: Long? = 0,
    val highQuality: Boolean? = false,
    val privacy: Int? = 0,
    val trackUpdateTime: Long? = 0,
    val trackNumberUpdateTime: Long? = 0,
    val playCount: Long? = 0,
    val adType: Int? = 0,
    val status: Int? = 0,
    val tags: List<String>? = listOf(),
    val subscribedCount: Int? = 0,
    val cloudTrackCount: Int? = 0,
    val userId: Long? = 0,
    val coverImgId: Long? = 0,
    val newImported: Boolean? = false,
    val anonimous: Boolean? = false,
    val name: String? = "",
    val id: Long? = 0,
    val coverImgId_str: String? = "",
    val ToplistType: String? = ""
)