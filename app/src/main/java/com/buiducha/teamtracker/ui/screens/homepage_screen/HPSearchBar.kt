package com.buiducha.teamtracker.ui.screens.homepage_screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.buiducha.teamtracker.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HPSearchBar(
    modifier: Modifier = Modifier
) {
    var query by remember {
        mutableStateOf("")
    }

    SearchBar(
        query = query,
        onQueryChange = {
            query = it
        },
        onSearch = {

        },
        active = false,
        onActiveChange = {

        },
        placeholder = {
            Text(text = stringResource(id = R.string.find))
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = null
            )
        },
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .fillMaxWidth()
    ) {

    }
}