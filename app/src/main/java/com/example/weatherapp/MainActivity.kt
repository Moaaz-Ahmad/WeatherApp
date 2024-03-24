package com.example.weatherapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.google.android.gms.location.LocationServices
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val _todayWeatherData = mutableStateOf<List<TodayWeatherData>>(emptyList())
    private val todayWeatherData: State<List<TodayWeatherData>> = _todayWeatherData
    private val _weeklyWeatherData = mutableStateOf<List<WeeklyWeatherData>>(emptyList())
    val weeklyWeatherData: State<List<WeeklyWeatherData>> = _weeklyWeatherData
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(contentType = ContentType.Application.Json)
        }
    }
    private val apiKey = "ee6bd1eb240e4a130c6e9aec0e11e099"
    private val database by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database").build()
    }
    private val database2 by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase2::class.java, "database2").build()
    }
    private val fusedLocationClient by lazy {
        LocationServices.getFusedLocationProviderClient(this)
    }

    private var currentLocation: Location? = null
    companion object {
        private const val REQUEST_LOCATION_PERMISSION = 1
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                MyApp(todayWeatherData = todayWeatherData.value,weeklyWeatherData = weeklyWeatherData.value)
            }
        }
        lifecycleScope.launch(Dispatchers.IO) {
            if (database.weatherItemDao().isEmpty()) {
                currentLocation?.let { location ->
                    val weatherItemsNetwork = fetchWeatherData()
                    _todayWeatherData.value = weatherItemsNetwork
                    saveMenuToDatabase(weatherItemsNetwork)
                }
            }
        }
        lifecycleScope.launch(Dispatchers.IO) {
            if (database2.weatherItemDao2().isEmpty()) {
                val weatherItemsNetwork = fetchWeeklyWeatherData()
                saveMenuToWeeklyDatabase(weatherItemsNetwork)
            }
        }
    }
    private fun requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        } else {
            getCurrentLocation()
        }
    }
    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_LOCATION_PERMISSION && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getCurrentLocation() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    currentLocation = location
                }
            }
    }
    private suspend fun fetchWeatherData(): List<TodayWeatherData> {
        val url = "http://https://api.openweathermap.org/data/3.0/onecall?lat=${currentLocation?.latitude}&lon=${currentLocation?.longitude}&exclude={part}&appid={API key}&units=metric"
        return client.get( url)
            .body<TodayWeatherNetwork>()
            .weather
    }
    private fun saveMenuToDatabase(todayWeatherData: List<TodayWeatherData>) {
        val weatherItemsRoom = todayWeatherData.map { it.ToTodayWeatherData() }
        database.weatherItemDao().insertAll(*weatherItemsRoom.toTypedArray())
    }
    private suspend fun fetchWeeklyWeatherData(): List<WeeklyWeatherData> {
        val url = "http://api.openweathermap.org/data/2.5/forecast?lat=${currentLocation?.latitude}&lon=${currentLocation?.longitude}&appid=$apiKey&units=metric"
        return client.get( url)
            .body<WeeklyWeatherNetwork>()
            .weather
    }
    private fun saveMenuToWeeklyDatabase(weeklyWeatherData: List<WeeklyWeatherData>) {
        val weatherItemsRoom = weeklyWeatherData.map { it.ToWeeklyWeatherData() }
        database2.weatherItemDao2().insertAll(*weatherItemsRoom.toTypedArray())
    }
}

@Composable
fun MyApp(todayWeatherData: List<TodayWeatherData>,weeklyWeatherData: List<WeeklyWeatherData>) {
    val navController = rememberNavController()
    Scaffold(bottomBar = { MyBottomNavigation(navController = navController) }) {
        Box(Modifier.padding(it)) {
            NavHost(navController = navController, startDestination = TodayScreen.route) {
                composable(TodayScreen.route) {
                    TodayScreen(todayWeatherData = todayWeatherData)
                }
                composable(WeeklyScreen.route) {
                    WeeklyScreen(weeklyWeatherData = weeklyWeatherData)
                }
            }
        }
    }
}
@Composable
fun MyBottomNavigation(navController: NavController) {
    val destinationList = listOf(
        TodayScreen,
        WeeklyScreen
    )
    val selectedIndex = rememberSaveable {
        mutableStateOf(0)
    }
    BottomNavigation {
        destinationList.forEachIndexed { index, destination ->
            BottomNavigationItem(
                label = { Text(text = destination.title) },
                icon = {
                    Icon(
                        painter = painterResource(id = destination.icon),
                        contentDescription = destination.title
                    )
                },
                selected = index == selectedIndex.value,
                onClick = {
                    selectedIndex.value = index
                    navController.navigate(destinationList[index].route) {
                        popUpTo(TodayScreen.route)
                        launchSingleTop = true
                    }
                })
        }
    }
}
@Preview
@Composable
fun MyAppPreview() {
    MyApp(emptyList(),emptyList())
}