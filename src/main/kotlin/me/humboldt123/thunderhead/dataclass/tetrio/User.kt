package me.humboldt123.thunderhead.dataclass.tetrio

data class User(
    val cache: Cache?,
    val `data`: Data?,
    val success: Boolean,
    val error: Boolean?
)