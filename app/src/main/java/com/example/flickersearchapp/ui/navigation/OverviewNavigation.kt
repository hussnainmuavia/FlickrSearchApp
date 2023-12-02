package com.example.flickersearchapp.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.flickersearchapp.ui.component.Routes
import com.example.flickersearchapp.ui.screens.HomeScreen

/*
* I have added a separate navigation graph [NavGraphBuilder.overviewNavigation()] just to demonstrate the navigation graph.
* This would also better demonstrate the separation of concerns between the module level
* navigation for example if we have e.g. pre-login/post-login navigation etc,
* so that we could define our separate navigation just like this.
* */
fun NavGraphBuilder.overviewNavigation() {
    navigation(
        startDestination = Destination.HomeScreen.route, route = Routes.OVERVIEW.value
    ) {
        composable(Destination.HomeScreen.route) {
            //Route to home screen that would show the listing screen by typing search item.
            HomeScreen()
        }
    }
}