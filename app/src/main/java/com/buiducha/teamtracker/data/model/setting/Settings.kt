package com.buiducha.teamtracker.data.model.setting

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Public
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.buiducha.teamtracker.R

enum class Settings(
    @StringRes val settingName: Int,
    val settingIcon: ImageVector,
) {
    Language(
        settingName = R.string.language,
        settingIcon = Icons.Outlined.Public,
    ),
    PrivacyPolicy(
        settingName = R.string.privacy_policy,
        settingIcon = Icons.Outlined.Lock,

    ),
    Introduce(
        settingName = R.string.introduce,
        settingIcon = Icons.Outlined.Info,

    ),
    LogOut(
        settingName = R.string.log_out,
        settingIcon = Icons.Outlined.Logout,

    )
}