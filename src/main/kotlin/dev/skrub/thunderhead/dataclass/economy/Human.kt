package dev.skrub.thunderhead.dataclass.economy

data class Human(
    val id: Long,
    var balance: Long,
    var lastDaily: Long
)
