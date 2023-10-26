package com.neo.shared.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.neo.shared.ui.home.HomeScreen
import com.neo.shared.ui.viewer.ViewerScreen

@Composable
fun App() {
    Navigator(HomeScreen)
}