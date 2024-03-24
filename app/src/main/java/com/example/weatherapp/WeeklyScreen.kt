package com.example.weatherapp

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale

@Composable
fun WeeklyScreen(weeklyWeatherData: List<WeeklyWeatherData>) {
    val currentDay = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Calendar.getInstance().time)
    val calendar = Calendar.getInstance()
    val dayOfWeek = SimpleDateFormat("EEEE", Locale.getDefault()).format(Calendar.getInstance().time)
    Column(modifier = Modifier.fillMaxHeight(1.0f), verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {
        Card(modifier = Modifier.fillMaxWidth(1.0f)) {
            Row {
                Image(painter = painterResource(id = R.drawable.ic_sun), contentDescription =null, modifier = Modifier.size(96.dp))
                Column {
                    Text(text = dayOfWeek, fontSize = 24.sp)
                    Text(text = "${weeklyWeatherData.first().list.first().main.temp}°C", fontSize = 24.sp)
                }
            }
        }
        Card(modifier = Modifier.fillMaxWidth(1.0f)) {
            Row {
                Image(painter = painterResource(id = R.drawable.ic_sun), contentDescription =null, modifier = Modifier.size(96.dp))
                Column {
                    calendar.add(Calendar.DAY_OF_WEEK, 1)
                    val nextDay = calendar.get(Calendar.DAY_OF_WEEK)
                    val nexTdayOfWeek = SimpleDateFormat("EEEE", Locale.getDefault()).format(nextDay)
                    Text(text =nexTdayOfWeek, fontSize = 24.sp)
                    Text(text = "${weeklyWeatherData.first().list.first().main.temp}°C", fontSize = 24.sp)
                }
            }
        }
        Card(modifier = Modifier.fillMaxWidth(1.0f)) {
            Row {
                Image(painter = painterResource(id = R.drawable.ic_sun), contentDescription =null, modifier = Modifier.size(96.dp))
                Column {
                    calendar.add(Calendar.DAY_OF_WEEK, 2)
                    val nextDay = calendar.get(Calendar.DAY_OF_WEEK)
                    val nexTdayOfWeek = SimpleDateFormat("EEEE", Locale.getDefault()).format(nextDay)
                    Text(text =nexTdayOfWeek, fontSize = 24.sp)
                    Text(text = "${weeklyWeatherData.first().list.first().main.temp}°C", fontSize = 24.sp)
                }
            }
        }
        Card(modifier = Modifier.fillMaxWidth(1.0f)) {
            Row {
                Image(painter = painterResource(id = R.drawable.ic_sun), contentDescription =null, modifier = Modifier.size(96.dp))
                Column {
                    calendar.add(Calendar.DAY_OF_WEEK, 3)
                    val nextDay = calendar.get(Calendar.DAY_OF_WEEK)
                    val nexTdayOfWeek = SimpleDateFormat("EEEE", Locale.getDefault()).format(nextDay)
                    Text(text =nexTdayOfWeek, fontSize = 24.sp)
                    Text(text = "${weeklyWeatherData.first().list.first().main.temp}°C", fontSize = 24.sp)
                }
            }
        }
        Card(modifier = Modifier.fillMaxWidth(1.0f)) {
            Row {
                Image(painter = painterResource(id = R.drawable.ic_sun), contentDescription =null, modifier = Modifier.size(96.dp))
                Column {
                    calendar.add(Calendar.DAY_OF_WEEK, 4)
                    val nextDay = calendar.get(Calendar.DAY_OF_WEEK)
                    val nexTdayOfWeek = SimpleDateFormat("EEEE", Locale.getDefault()).format(nextDay)
                    Text(text =nexTdayOfWeek, fontSize = 24.sp)
                    Text(text = "${weeklyWeatherData.first().list.first().main.temp}°C", fontSize = 24.sp)
                }
            }
        }
        Card(modifier = Modifier.fillMaxWidth(1.0f)) {
            Row {
                Image(painter = painterResource(id = R.drawable.ic_sun), contentDescription =null, modifier = Modifier.size(96.dp))
                Column {
                    calendar.add(Calendar.DAY_OF_WEEK, 5)
                    val nextDay = calendar.get(Calendar.DAY_OF_WEEK)
                    val nexTdayOfWeek = SimpleDateFormat("EEEE", Locale.getDefault()).format(nextDay)
                    Text(text =nexTdayOfWeek, fontSize = 24.sp)
                    Text(text = "${weeklyWeatherData.first().list.first().main.temp}°C", fontSize = 24.sp)
                }
            }
        }
        Card(modifier = Modifier.fillMaxWidth(1.0f)) {
            Row {
                Image(painter = painterResource(id = R.drawable.ic_sun), contentDescription =null, modifier = Modifier.size(96.dp))
                Column {
                    calendar.add(Calendar.DAY_OF_WEEK, 6)
                    val nextDay = calendar.get(Calendar.DAY_OF_WEEK)
                    val nexTdayOfWeek = SimpleDateFormat("EEEE", Locale.getDefault()).format(nextDay)
                    Text(text =nexTdayOfWeek, fontSize = 24.sp)
                    Text(text = "${weeklyWeatherData.first().list.first().main.temp}°C", fontSize = 24.sp)
                }
            }
        }
    }
}
@Preview
@Composable
fun WeeklyScreenPreview() {
    WeeklyScreen(emptyList())
}