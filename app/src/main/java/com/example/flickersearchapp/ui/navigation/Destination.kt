package com.example.flickersearchapp.ui.navigation


sealed class Destination(val route: String) {
    object HomeScreen : Destination(route = "homeScreen")
}