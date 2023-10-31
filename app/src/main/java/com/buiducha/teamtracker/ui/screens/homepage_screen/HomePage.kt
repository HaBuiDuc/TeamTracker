package com.buiducha.teamtracker.ui.screens.homepage_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun HomePagePreview() {
    HomePage()
}

@Composable
fun HomePage() {
    Scaffold(
        topBar = {
            HPTopBar()
        }
    ) {padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            HPSearchBar()
            Spacer(modifier = Modifier.height(24.dp))
            WorkSpaces()
        }
    }
}