package com.example.weatherapp

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson

@ProvidedTypeConverter
class TodayWeatherRoomTypeConverter {

    @TypeConverter
    fun fromTodayWeatherRoom(todayWeatherRoom: TodayWeatherRoom): String {
        return Gson().toJson(todayWeatherRoom)
    }

    @TypeConverter
    fun toTodayWeatherRoom(value: String): TodayWeatherRoom {
        return Gson().fromJson(value, TodayWeatherRoom::class.java)
    }
    @TypeConverter
    fun fromCoord(coord: Coord): String {
        return Gson().toJson(coord)
    }

    @TypeConverter
    fun toCoord(value: String): Coord {
        return Gson().fromJson(value, Coord::class.java)
    }
    @TypeConverter
    fun fromWeatherList(weather: List<Weather>): String {
        return Gson().toJson(weather)
    }

    @TypeConverter
    fun toWeatherList(value: String): List<Weather> {
        return Gson().fromJson(value, Array<Weather>::class.java).toList()
    }
    @TypeConverter
    fun fromMain(main: Main): String {
        return Gson().toJson(main)
    }

    @TypeConverter
    fun toMain(value: String): Main {
        return Gson().fromJson(value, Main::class.java)
    }
    @TypeConverter
    fun fromWind(wind: Wind): String {
        return Gson().toJson(wind)
    }
    @TypeConverter
    fun toWind(value: String): Wind {
        return Gson().fromJson(value, Wind::class.java)
    }
    @TypeConverter
    fun fromClouds(clouds: Clouds): String {
        return Gson().toJson(clouds)
    }
    @TypeConverter
    fun toClouds(value: String): Clouds {
        return Gson().fromJson(value, Clouds::class.java)
    }
    @TypeConverter
    fun fromSys(sys: Sys): String {
        return Gson().toJson(sys)
    }
    @TypeConverter
    fun toSys(value: String): Sys {
        return Gson().fromJson(value, Sys::class.java)
    }
    @TypeConverter
    fun fromRain(rain: Rain): String {
        return Gson().toJson(rain)
    }
    @TypeConverter
    fun toRain(value: String): Rain {
        return Gson().fromJson(value, Rain::class.java)
    }

}
@ProvidedTypeConverter
class WeeklyWeatherRoomTypeConverter {

    @TypeConverter
    fun fromWeeklyWeatherRoom(weeklyWeatherRoom: WeeklyWeatherRoom): String {
        return Gson().toJson(weeklyWeatherRoom)
    }
    @TypeConverter
    fun fromCity(city: City): String {
        return Gson().toJson(city)
    }
    @TypeConverter
    fun toCity(value: String): City {
        return Gson().fromJson(value, City::class.java)
    }
    @TypeConverter
    fun fromListData(weatherData: ListData): String {
        return Gson().toJson(weatherData)
    }
    @TypeConverter
    fun toListData(value: String): ListData {
        return Gson().fromJson(value, ListData::class.java)
    }
    @TypeConverter
    fun fromList(list: List<ListData>): String {
        return Gson().toJson(list)
    }
    @TypeConverter
    fun toList(value: String): List<ListData> {
        return Gson().fromJson(value, Array<ListData>::class.java).toList()
    }
}