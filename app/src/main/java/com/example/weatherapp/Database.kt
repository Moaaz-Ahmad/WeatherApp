package com.example.weatherapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [TodayWeatherRoom::class], version = 1, exportSchema = false)
@TypeConverters(TodayWeatherRoomTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherItemDao(): WeatherItemDao
}
@Database(entities = [WeeklyWeatherRoom::class], version = 1, exportSchema = false)
@TypeConverters(WeeklyWeatherRoomTypeConverter::class)
abstract class AppDatabase2 : RoomDatabase() {
    abstract fun weatherItemDao2(): WeatherItemDao2
}
@Entity
data class TodayWeatherRoom(
    val coord: Coord,
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
    @PrimaryKey val id: Int,
    val name: String,
    val cod: Int
)
@Entity
data class WeeklyWeatherRoom(
    val cod: String,
    val message: Int,
    val cnt: Int,
    val list: List<ListData>,
    @PrimaryKey val city: City
)
@Dao
interface WeatherItemDao {
    @Query("SELECT * FROM TodayWeatherRoom")
    fun getAll(): LiveData<List<TodayWeatherRoom>>
    @Insert
    fun insertAll(vararg weatherItems: TodayWeatherRoom)
    @Query("SELECT (SELECT COUNT(*) FROM TodayWeatherRoom) == 0")
    fun isEmpty(): Boolean
}
@Dao
interface WeatherItemDao2 {
    @Query("SELECT * FROM WeeklyWeatherRoom")
    fun getAll(): LiveData<List<WeeklyWeatherRoom>>
    @Insert
    fun insertAll(vararg weatherItems: WeeklyWeatherRoom)
    @Query("SELECT (SELECT COUNT(*) FROM WeeklyWeatherRoom) == 0")
    fun isEmpty(): Boolean
}

