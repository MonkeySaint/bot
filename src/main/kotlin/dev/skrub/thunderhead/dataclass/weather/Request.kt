package dev.skrub.thunderhead.dataclass.weather

data class Request(
    val query: String,
    val type: String
)