package com.example.flickersearchapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.flickersearchapp.ui.theme.FlickerSearchAppTheme
import kotlinx.coroutines.delay

/**
 * SplashScreenComposable is the first composable or entry point of the app.
 * It displays the Splash screen with animation and call the HomeScreen Compose.
 */
@Composable
fun SplashScreen(onTimeout: () -> Unit = {}) {

    val splashWaitTime: Long = 500

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = "Splash Screen", modifier = Modifier)

        val currentOnTimeout by rememberUpdatedState(newValue = onTimeout)
        LaunchedEffect(Unit) {
            delay(splashWaitTime)
            currentOnTimeout()
        }
    }

}


@Preview(showBackground = true)
@Composable
fun SplashScreenContentPreview() {
    FlickerSearchAppTheme {
        SplashScreen()
    }
}