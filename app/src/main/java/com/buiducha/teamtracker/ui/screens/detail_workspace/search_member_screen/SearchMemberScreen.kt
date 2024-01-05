package com.buiducha.teamtracker.ui.screens.detail_workspace.search_member_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.buiducha.teamtracker.R
import com.buiducha.teamtracker.data.model.user.UserData
import com.buiducha.teamtracker.ui.theme.DarkGreen
import com.buiducha.teamtracker.viewmodel.MemberManagementViewModel
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.SelectedWorkspaceViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun SearchMemberScreen(
    selectedWorkspaceViewModel: SelectedWorkspaceViewModel,
    memberManagementViewModel: MemberManagementViewModel = viewModel {
        MemberManagementViewModel(selectedWorkspaceViewModel)
    },
    navController: NavController
) {
    val memberManagementState by memberManagementViewModel.memberManagementState.collectAsState()
    var query: MutableState<String> = remember { mutableStateOf("") }


    var memberList: MutableList<UserData> = memberManagementState.memberList

//    memberList.add(memberManagementState.workspaceOwner)
    val filteredMemberList: MutableList<UserData> = memberList.filter {
        it.fullName.contains(query.value, ignoreCase = true)
                || it.email.contains(query.value, ignoreCase = true)
    }.toMutableList()

    val ownerAsList: MutableList<UserData> = mutableListOf(memberManagementState.workspaceOwner)
    ownerAsList.filter {
        it.fullName.contains(query.value, ignoreCase = true)
                || it.email.contains(query.value, ignoreCase = true)
    }.toMutableList()
    Scaffold(
        topBar = { TopAppBarSearchMember(query = query, navController = navController) }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(top = paddingValues.calculateTopPadding())) {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(24.dp)) {
                items(filteredMemberList) { member ->
                    MemberItem(member = member, isWorkspaceOwner = false)
                }
                items(ownerAsList) {
                    MemberItem(member = it, isWorkspaceOwner = true)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarSearchMember(query: MutableState<String>,
                          modifier: Modifier = Modifier,
                          navController: NavController
){
    Row {
        IconButton(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null
            )
        }
        SearchBar(
            query = query.value,
            onQueryChange = {
                query.value = it
            },
            onSearch = {

            },
            active = false,
            onActiveChange = {

            },
            placeholder = {
                Text(text = stringResource(id = R.string.enter_member_email))
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = null
                )
            },
            shape = RoundedCornerShape(16.dp),
            modifier = modifier
                .fillMaxWidth()
        ) {}
    }

}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MemberItem(member: UserData, isWorkspaceOwner: Boolean)
{
    var isWorkspaceOwnerText: String = ""
    if(isWorkspaceOwner) {isWorkspaceOwnerText = "Owner"}
    Row {
        if(member.avatarUri == null){
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(
                        color = DarkGreen,
                        shape = CircleShape
                    )
                    .padding(10.dp)
            ) {
                Text(
                    text = if(member.fullName.length > 3) member.fullName.substring(0, 2).uppercase() else "",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
        else{
            GlideImage(
                model = member.avatarUri,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .width(45.dp)
                    .aspectRatio(1f)
            )
        }

        Column {
            Text(text = member.fullName, fontWeight = FontWeight.Bold)
            Text(text = isWorkspaceOwnerText)
        }
    }
}