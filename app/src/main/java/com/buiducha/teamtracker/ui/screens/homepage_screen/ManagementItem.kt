package com.buiducha.teamtracker.ui.screens.homepage_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buiducha.teamtracker.data.model.settings.ManagementValue

@Composable
fun ManagementItem(
    setting: ManagementValue,
    onOptionSelected: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable {
                onOptionSelected()
            }
            .padding(
                vertical = 8.dp,
                horizontal = 16.dp
            )
            .fillMaxWidth()
    ){
        Icon(
            imageVector = setting.icon,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = stringResource(id = setting.label),
            fontSize = 18.sp,
            fontWeight = FontWeight(400)
        )
    }
}