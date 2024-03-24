package com.example.weatherapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TodayScreen(todayWeatherData: List<TodayWeatherData>) {
    val currentWeather = todayWeatherData.first()
    Box(modifier = Modifier.fillMaxSize()) {
        Image(painter = painterResource(id = R.drawable.mountain), contentDescription = "Mountain",contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxWidth())
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = CenterHorizontally, verticalArrangement = Arrangement.Center) {
            if (todayWeatherData.isNotEmpty()) {
                Text(
                    text = "Temperature: ${currentWeather.main.temp}°C",
                    style = MaterialTheme.typography.headlineMedium,fontSize = 24.sp, color = Color.White
                )
                Text(
                    text = "Description: ${currentWeather.weather.first().description}",
                    style = MaterialTheme.typography.bodyMedium,fontSize = 24.sp, color = Color.White
                )
                Image(painter = painterResource(id = R.drawable.ic_sun), contentDescription =null, modifier = Modifier
                    .align(CenterHorizontally)
                    .size(128.dp))
                // Add more weather details as needed
            } else {
                Text(
                    text = "Loading weather data...",
                    style = MaterialTheme.typography.bodyMedium,fontSize = 24.sp, color = Color.White
                )
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = CenterVertically) {
                Column(horizontalAlignment = CenterHorizontally) {
                    if (todayWeatherData.isNotEmpty()) {
                        Image(painter = painterResource(id = R.drawable.ic_humidity), contentDescription =null, modifier = Modifier.size(48.dp))
                        Text(text = "${currentWeather.main.humidity}%", fontSize = 24.sp, color = Color.White)
                    }
                    else {
                        Text(
                            text = "Loading humidity data...",
                            style = MaterialTheme.typography.bodyMedium,fontSize = 24.sp, color = Color.White
                        )
                    }
                }
                Column(horizontalAlignment = CenterHorizontally) {
                    if (todayWeatherData.isNotEmpty()) {
                        Image(painter = painterResource(id = R.drawable.ic_wind), contentDescription =null, modifier = Modifier.size(48.dp))
                        Text(text = "${currentWeather.wind.speed}Km/hr", fontSize = 24.sp, color = Color.White)
                    }
                }
                Column(horizontalAlignment = CenterHorizontally) {
                    if (todayWeatherData.isNotEmpty()) {
                        Image(painter = painterResource(id = R.drawable.ic_cloud), contentDescription =null, modifier = Modifier.size(48.dp))
                        Text(text = "${currentWeather.rain._1h}%", fontSize = 24.sp, color = Color.White)
                    }
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun TodayScreenPreview() {
    TodayScreen(todayWeatherData = emptyList())
}
