package com.buiducha.teamtracker.ui.screens.chat_screen_group.channel_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.buiducha.teamtracker.ui.theme.ShadeGray

@Composable
fun ChannelSearchBar(
    onSearchToggle: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .background(
                color = ShadeGray,
                shape = RoundedCornerShape(6)
            )
            .clickable { onSearchToggle() }
            .padding(12.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Search"
        )
    }
}