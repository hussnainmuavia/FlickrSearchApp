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
            /*
            * I have added a Splash Screen for navigation graph just to demonstrate between screens.
            * This would also better demonstration if we follow single activity architecture by using
            * compose and screen buildings.
            * */
            composable(Routes.SPLASHSCREEN.value) {
                SplashScreen {
                    navController.navigate(Routes.OVERVIEW.value) {
                        popUpTo(Routes.SPLASHSCREEN.value) { inclusive = true }
                    }
                }
            }
            /*
            * I have added a separate navigation graph just to demonstrate the navigation graph.
            * This would also better demonstrate the separation of concerns between the module level
            * navigation for example if we have pre-login/post-login navigation etc, so that we could define
            * our separate navigation just like this
            * */
            overviewNavigation()
        }
    }
}

/*
* Routes to drive the navigation through them like
* HomeScreen is being named as overview route and splash screen is as splashScreen.
* */
enum class Routes(val value: String) {
    OVERVIEW("overview"),
    SPLASHSCREEN("splashScreen")
}