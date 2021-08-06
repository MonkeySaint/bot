package dev.skrub.thunderhead.dataclass.economy

data class Human(
    val id: Long,
    var balance: Int,
    var lastDaily: Long
)
