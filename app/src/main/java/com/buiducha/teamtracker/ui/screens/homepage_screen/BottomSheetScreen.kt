package com.buiducha.teamtracker.ui.screens.homepage_screen

sealed class BottomSheetScreen {
    object WSListManagement: BottomSheetScreen()
    object WSManagement: BottomSheetScreen()
}