package com.buiducha.teamtracker.ui.screens.task_management.table_task_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.FormatListBulleted
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.ZoomOut
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buiducha.teamtracker.R
import com.buiducha.teamtracker.ui.screens.task_management._share.BoxTagColor


@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showSystemUi = true)
@Composable
fun TableTaskScreen() {
    Scaffold(
        topBar = {
            TableTaskTopBar()
        },
        floatingActionButton = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(
                        color = colorResource(id = R.color.super_light_blue),
                        shape = CircleShape
                    )
                    .padding(12.dp)
                    .size(28.dp)
            ) {
                Icon(imageVector = Icons.Default.ZoomOut, contentDescription = "")
            }

        }
    ) {
//        Row (modifier = Modifier
//            .offset(y = 50.dp)
//            .horizontalScroll(rememberScrollState())) {
//            TaskList()
//            TaskList()
//        }
        val pagerState = rememberPagerState(pageCount = {
            10
        })
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 32.dp),
            modifier = Modifier.offset(y = 50.dp)
        ) { page ->
            TaskList()
        }
    }


}

@Composable
fun TaskList() {

    Card(
        Modifier
            .padding(5.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors()
    ) {
        Column(Modifier.padding(10.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Danh sách 1",
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = ""
                )
            }

            TaskItem()
            TaskItem()

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(Color.Transparent)
            ) {
                Text(text = "+ Thêm thẻ", color = Color.Blue)
            }
        }

    }

}

@Composable
fun TaskItem() {
    Card(
        Modifier
            .padding(5.dp)
            .shadow(
                elevation = 3.dp,
                shape = RoundedCornerShape(15.dp)
            ),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(Color.White),
    ) {
        Column(Modifier.padding(10.dp)) {
            BoxTagColor(taskTag = 2)
            Text(text = "Thẻ 1")
            Spacer(modifier = Modifier.padding(5.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Icon(imageVector = Icons.Filled.RemoveRedEye, contentDescription = "")
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Filled.AccessTime, contentDescription = "")
                    Text(text = "18/12/2023 - 18/12/2023")
                }
            }
            Spacer(modifier = Modifier.padding(5.dp))
            Row {
                Icon(imageVector = Icons.Filled.Message, contentDescription = "")
                Text(text = "11")
            }
            Spacer(modifier = Modifier.padding(5.dp))
            //danh sách các thành viên tham gia nhiệm vụ
            //===============
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .background(
                            color = colorResource(id = R.color.super_light_blue),
                            shape = CircleShape
                        )
                        .padding(12.dp)
                        .size(22.dp)
                ) {
                    Text(
                        text = "CN",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
            //===============
        }
    }
}
