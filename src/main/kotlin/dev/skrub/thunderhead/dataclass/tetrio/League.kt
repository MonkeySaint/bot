package dev.skrub.thunderhead.dataclass.tetrio

data class League(
    val apm: Double?,
    val decaying: Boolean,
    val gamesplayed: Int,
    val gameswon: Int,
    val glicko: Double?,
    val next_at: Int,
    val next_rank: String?,
    val percentile: Double,
    val percentile_rank: String,
    val pps: Double?,
    val prev_at: Int,
    val prev_rank: String?,
    val rank: String,
    val rating: Double,
    val rd: Double?,
    val standing: Int,
    val standing_local: Int,
    val vs: Double?
)