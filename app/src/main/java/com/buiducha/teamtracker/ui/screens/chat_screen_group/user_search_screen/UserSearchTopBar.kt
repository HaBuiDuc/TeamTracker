package com.buiducha.teamtracker.ui.screens.chat_screen_group.user_search_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buiducha.teamtracker.ui.theme.ShadeGray

@Composable
fun UserSearchTopBar(
    query: String,
    modifier: Modifier = Modifier,
    onQuery: (String) -> Unit,
    onPopBack: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(RoundedCornerShape(6))
            .background(
                color = ShadeGray
            )
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        IconButton(
            onClick = onPopBack
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null
            )
        }

        BasicTextField(
            value = query,
            onValueChange = { newText ->
                onQuery(newText)
            },
//            textStyle = TextStyle(
//                fontSize = 20.sp,
//                fontWeight = FontWeight.Normal,
//                color = Color.DarkGray
//            ),
            decorationBox = { innerTextField ->
                if (query.isEmpty()) {
                    Log.d("This is a log", "is not empty ")
                    Text(
                        text = "Search",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                }
                innerTextField()
            },
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}