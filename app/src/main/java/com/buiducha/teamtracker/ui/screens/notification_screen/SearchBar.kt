package com.buiducha.teamtracker.ui.screens.notification_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(navController: NavController,
              searchText: MutableState<String>) {
    Column {
//        val searchText = remember { mutableStateOf("")}
        Row() {
            IconButton(
                onClick = {
                          navController.popBackStack()
                },
                content = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }
            )
            Text(text = "Activity",
                modifier = Modifier
                    .padding(0.dp)
                    .align(CenterVertically),)
        }
        Row(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
            TextField(
                shape = RoundedCornerShape(28.dp),
                value = searchText.value,
                placeholder = { Text(text = "Tìm kiếm thông báo")},
                onValueChange = {
                    searchText.value = it
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Tìm kiếm"
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                modifier = Modifier
                    .weight(1F)
                    .padding(all = 0.dp)
            )
        }


    }

}