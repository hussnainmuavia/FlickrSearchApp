package com.example.flickersearchapp.ui.navigation


sealed class Destination(val route: String) {
    object Splash : Destination(route = "splashScreen")
    object HomeScreen : Destination(route = "homeScreen")
}