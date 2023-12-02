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
import com.example.flickersearchapp.ui.navigation.Destination
import com.example.flickersearchapp.ui.navigation.overviewNavigation
import com.example.flickersearchapp.ui.screens.HomeScreen
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
                        //Pop out the splash route so that we wont get back here from home/overview screen
                        popUpTo(Routes.SPLASHSCREEN.value) { inclusive = true }
                    }
                }
            }
            /*
            * I have added a separate navigation graph [overviewNavigation] just to demonstrate the navigation graph.
            * This would also better demonstrate the separation of concerns between the module level
            * navigation for example if we have e.g. pre-login/post-login navigation etc,
            * so that we could define our separate navigation just like this.
            * */
            overviewNavigation()

            /** If we don't want to use separate navigation graph then we could simple draw our
            * composable as below: e.g. composable(Routes.OVERVIEW.value)
            * This is the example of the composable route if we don't use navGraph [overviewNavigation]
            **/
            /*composable(Routes.OVERVIEW.value) {
                HomeScreen()
            }*/
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