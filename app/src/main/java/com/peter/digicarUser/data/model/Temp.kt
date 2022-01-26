package com.peter.digicarUser.data.model

data class Temp(
    val main: Main,
    val name: String
)

data class Main(
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double
)