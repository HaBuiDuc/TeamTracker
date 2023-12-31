package com.buiducha.teamtracker.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object HomeScreen: BottomBarScreen(
        route = "home_screen",
        title = "Home",
        icon = Icons.Default.Home
    )
    object ActivityScreen: BottomBarScreen(
        route = "activity_screen",
        title = "Activity",
        icon = Icons.Default.Notifications
    )
    object SettingsScreen: BottomBarScreen(
        route = "settings",
        title = "Settings",
        icon = Icons.Default.Settings
    )
    object CalendarScreen: BottomBarScreen(
        route = "calendar_screen",
        title = "Calendar",
        icon = Icons.Default.CalendarMonth
    )
}