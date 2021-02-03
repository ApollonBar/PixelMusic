package com.kyant.pixelmusic.api.playlist

data class Track(
    val name: String? = "",
    val id: Long? = 0,
    val pst: Int? = 0,
    val t: Int? = 0,
    val ar: List<Ar>? = listOf(),
    val alia: List<Any>? = listOf(),
    val pop: Int? = 0,
    val st: Int? = 0,
    val rt: Any? = Any(),
    val fee: Int? = 0,
    val v: Int? = 0,
    val crbt: Any? = Any(),
    val cf: String? = "",
    val al: Al? = Al(),
    val dt: Int? = 0,
    val h: H? = H(),
    val m: M? = M(),
    val l: L? = L(),
    val a: Any? = Any(),
    val cd: String? = "",
    val no: Int? = 0,
    val rtUrl: Any? = Any(),
    val ftype: Int? = 0,
    val rtUrls: List<Any>? = listOf(),
    val djId: Int? = 0,
    val copyright: Int? = 0,
    val s_id: Int? = 0,
    val mark: Int? = 0,
    val originCoverType: Int? = 0,
    val originSongSimpleData: Any? = Any(),
    val single: Int? = 0,
    val noCopyrightRcmd: Any? = Any(),
    val rtype: Int? = 0,
    val rurl: Any? = Any(),
    val mst: Int? = 0,
    val cp: Int? = 0,
    val mv: Int? = 0,
    val publishTime: Long? = 0
)