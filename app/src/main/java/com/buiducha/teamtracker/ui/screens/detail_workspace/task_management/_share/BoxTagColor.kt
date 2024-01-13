package com.buiducha.teamtracker.ui.screens.detail_workspace.task_management._share

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.buiducha.teamtracker.R
import com.buiducha.teamtracker.ui.theme.TagColor1
import com.buiducha.teamtracker.ui.theme.TagColor2
import com.buiducha.teamtracker.ui.theme.TagColor3
import com.buiducha.teamtracker.ui.theme.TagColor4
import com.buiducha.teamtracker.ui.theme.TagColor5
import com.buiducha.teamtracker.ui.theme.TagColor6

@Preview
@Composable
fun BoxTagColorPrev(){
    BoxTagColor(taskTag = 3)
}

@Composable()
fun BoxTagColor(
    taskTag:Int
){
    val color = when(taskTag) {
        1 -> TagColor1
        2 -> TagColor2
        3 -> TagColor3
        4 -> TagColor4
        5 -> TagColor5
        6 -> TagColor6
        else -> Color.Transparent
    }
    Box(
        Modifier
            .height(18.dp)
            .width(56.dp)
            .background(
                color = color,
                shape = RoundedCornerShape(2)
            )
    )
//    if(taskTag == 1){
//        Box(
//            Modifier
//                .height(30.dp)
//                .width(80.dp)
//                .background(color = Color.Blue)
//        ){}
//    }
//    else if(taskTag == 2){
//        Box(
//            Modifier
//                .height(30.dp)
//                .width(80.dp)
//                .background(color = Color.Green)
//        ){}
//    }
//    else if(taskTag == 3){
//        Box(
//            Modifier
//                .height(30.dp)
//                .width(80.dp)
//                .background(color = Color.Red)
//        ){}
//    }
//    else if(taskTag == 4){
//        Box(
//            Modifier
//                .height(30.dp)
//                .width(80.dp)
//                .background(color = colorResource(id = R.color.purple_200))
//        ){}
//    }
//    else if(taskTag == 5){
//        Box(
//            Modifier
//                .height(30.dp)
//                .width(80.dp)
//                .background(color = colorResource(id = R.color.teal_200))
//        ){}
//    }
}