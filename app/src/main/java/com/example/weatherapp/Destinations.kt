package com.example.weatherapp

interface Destinations {
    val route: String
    val icon: Int
    val title: String
}
object TodayScreen : Destinations {
    override val route = "Today"
    override val icon = R.drawable.ic_today
    override val title = "Today"
}
object WeeklyScreen : Destinations {
    override val route = "Weekly"
    override val icon = R.drawable.ic_weekly
    override val title = "Weekly"
}