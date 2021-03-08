package me.humboldt123.thunderhead.dataclass.tetrio

data class UserX(
    val _id: String,
    val avatar_revision: Long?,
    val badges: List<Badge>,
    val banner_revision: Long?,
    val bio: String?,
    val country: String?,
    val friend_count: Int,
    val gamesplayed: Int,
    val gameswon: Int,
    val gametime: Double,
    val league: League,
    val role: String,
    val supporter: Boolean,
    val supporter_tier: Int,
    val ts: String?,
    val username: String,
    val verified: Boolean,
    val xp: Double,
    val botmaster: String?
)