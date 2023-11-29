package com.example.flickersearchapp.ui.component

import android.content.Context
import android.content.ContextWrapper
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.flickersearchapp.ui.navigation.overviewNavigation
import com.example.flickersearchapp.ui.screens.SplashScreen


@Composable
fun MainActivityComponent() {
    val navController = rememberNavController()
    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.SPLASHSCREEN.value,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Routes.SPLASHSCREEN.value) {
                SplashScreen {
                    navController.navigate(Routes.OVERVIEW.value) {
                        popUpTo(Routes.SPLASHSCREEN.value) { inclusive = true }
                    }
                }
            }
            overviewNavigation()
        }
    }
}

enum class Routes(val value: String) {
    OVERVIEW("overview"),
    SPLASHSCREEN("splashScreen")
}