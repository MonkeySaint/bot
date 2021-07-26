package dev.skrub.thunderhead.dataclass.weather

data class Weather(
    val current_condition: List<CurrentCondition>,
    val nearest_area: List<NearestArea>,
    val request: List<Request>,
    val weather: List<WeatherX>
)