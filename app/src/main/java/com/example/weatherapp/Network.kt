package com.example.weatherapp

import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TodayWeatherNetwork(
@SerialName("weather") val weather: List<TodayWeatherData>,
)
@Serializable
data class WeeklyWeatherNetwork(
    @SerialName("weather2") val weather: List<WeeklyWeatherData>,
)
@Serializable
data class TodayWeatherData(
    @PrimaryKey val coord: Coord,
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val visibility: Int,
    val wind: Wind,
    val rain: Rain,
    val clouds: Clouds,
    val dt: Int,
    val sys: Sys,
    val timezone: Int,
    val id: Int,
    val name: String,
    val cod: Int
){
    fun ToTodayWeatherData() = TodayWeatherRoom(coord, weather, base, main, visibility, wind, rain, clouds, dt, sys, timezone, id, name, cod)
}
@Serializable
data class WeeklyWeatherData(
    val cod: String,
    val message: Int,
    val cnt: Int,
    val list: List<ListData>,
    @PrimaryKey val city: City
){
    fun ToWeeklyWeatherData() = WeeklyWeatherRoom(cod, message, cnt, list, city)
}
@Serializable
data class Coord(
    val lon: Double,
    val lat: Double
)
@Serializable
data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)
@Serializable
data class Main(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Int,
    val humidity: Int,
    val sea_level: Int,
    val grnd_level: Int
)
@Serializable
data class Wind(
    val speed: Double,
    val deg: Int,
    val gust: Double
)
@Serializable
data class Rain(
    val _1h: Double
)
@Serializable
data class Clouds(
    val all: Int
)
@Serializable
data class Sys(
    val type: Int,
    val id: Int,
    val country: String,
    val sunrise: Int,
    val sunset: Int
)
@Serializable
data class ListData(
    val dt: Long,
    val main: Main,
    val weather: List<Weather>,
    val clouds: Clouds,
    val wind: Wind,
    val visibility: Int,
    val pop: Double,
    val rain: Rain,
    val sys: Sys,
    val dt_txt: String
)

@Serializable
data class City(
    val id: Int,
    val name: String,
    val coord: Coord,
    val country: String,
    val population: Int,
    val timezone: Int,
    val sunrise: Long,
    val sunset: Long
)
