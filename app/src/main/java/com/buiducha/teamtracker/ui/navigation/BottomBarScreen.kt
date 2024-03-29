package com.buiducha.teamtracker.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
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
        route = Screen.NotificationScreen.route,
        title = "Activity",
        icon = Icons.Default.Notifications
    )
    object SettingsScreen: BottomBarScreen(
        route = "settings",
        title = "Settings",
        icon = Icons.Default.Settings
    )
    object ChannelsScreen: BottomBarScreen(
        route = "channels_screen",
        title = "Chat",
        icon = Icons.Default.Message
    )
}