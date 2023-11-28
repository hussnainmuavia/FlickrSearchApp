package com.example.flickersearchapp.ui.navigation

import android.content.Context
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.flickersearchapp.ui.component.Routes
import com.example.flickersearchapp.ui.screens.HomeScreen

fun NavGraphBuilder.overviewNavigation(navController: NavController, context: Context) {
    navigation(
        startDestination = Destination.HomeScreen.route, route = Routes.OVERVIEW.value
    ) {
        composable(Destination.HomeScreen.route) {
            HomeScreen()
        }
    }
}