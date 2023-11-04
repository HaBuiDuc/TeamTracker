package com.buiducha.teamtracker.ui.screens.detail_project_screen.team_member_detail

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buiducha.teamtracker.ui.states.fontWidth
import com.buiducha.teamtracker.ui.screens.detail_project_screen.message_screen.demo_data.messages

@Preview(showSystemUi = true)
@Composable
fun TeamMemberDetail(){
    var emailInput = remember {
        mutableStateOf("")
    }
    var accountName = remember {
        mutableStateOf("")
    }
    var phoneNumber = remember {
        mutableStateOf("")
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = "THÊM THÀNH VIÊN",
            fontSize = 30.sp,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
            ),
            modifier = Modifier)
        Spacer(modifier = Modifier.padding(10.dp))
        Text(text = "Nhập email vào ô dưới")
        Row {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                OutlinedTextField(
                    value = emailInput.value,
                    onValueChange = {},
                    placeholder = { Text(text = "Email") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.MailOutline,
                            contentDescription = ""
                        )
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f),
                )
            }
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Row {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                OutlinedTextField(
                    value = accountName.value,
                    onValueChange = {},
                    placeholder = { Text(text = "Tên tài khoản") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.MailOutline,
                            contentDescription = ""
                        )
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f),
                )
            }
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Row {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                OutlinedTextField(
                    value = phoneNumber.value,
                    onValueChange = {},
                    placeholder = { Text(text = "Số điện thoại") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.MailOutline,
                            contentDescription = ""
                        )
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f),
                )
            }
        }

        Button(onClick = { /*TODO*/ }) {
            Text(text = "+ Thêm")
        }
        Spacer(modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth()
            .height(2.dp)
            .background(Color.Black))

        Text(text = "Danh sách thành viên dự án",
            style = TextStyle(fontWeight = FontWeight.Medium))
        LazyColumn(){
            items(5){
                TeamMemberList()
            }
        }
    }
}