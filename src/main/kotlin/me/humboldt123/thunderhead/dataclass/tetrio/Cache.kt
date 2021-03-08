package me.humboldt123.thunderhead.dataclass.tetrio

data class Cache(
    val cached_at: Long,
    val cached_until: Long,
    val status: String
)