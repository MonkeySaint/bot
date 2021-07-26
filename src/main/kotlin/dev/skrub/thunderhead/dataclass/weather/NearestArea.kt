package dev.skrub.thunderhead.dataclass.weather

data class NearestArea(
    val areaName: List<AreaName>,
    val country: List<Country>,
    val latitude: String,
    val longitude: String,
    val region: List<Region>
)