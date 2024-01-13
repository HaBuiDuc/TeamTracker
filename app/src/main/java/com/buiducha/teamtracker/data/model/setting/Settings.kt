package com.buiducha.teamtracker.data.model.setting

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password

import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Public
import androidx.compose.ui.graphics.vector.ImageVector
import com.buiducha.teamtracker.R

enum class Settings(
    @StringRes val settingName: Int,
    val settingIcon: ImageVector,
) {
    EditProfile(
        settingName = R.string.edit_profile,
        settingIcon = Icons.Outlined.Person,
    ),
    ChangePassword(
        settingName = R.string.change_password,
        settingIcon = Icons.Default.Password
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