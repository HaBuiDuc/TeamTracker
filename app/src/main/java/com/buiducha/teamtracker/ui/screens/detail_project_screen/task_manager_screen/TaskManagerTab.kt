package com.buiducha.teamtracker.ui.screens.detail_project_screen.task_manager_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.buiducha.teamtracker.ui.screens.detail_project_screen.task_manager_screen.demo_data.taskData
import com.buiducha.teamtracker.ui.screens.detail_project_screen.task_manager_screen.demo_data.taskHeader

@Preview(showSystemUi = true)
@Composable
fun TaskManagerTab(){
    var AddNewTableTask = remember{(mutableStateOf(false))}
    Box(modifier = Modifier.fillMaxSize()){
        Column {
            // ForEach tableTask => hiển thị table task và các task trong table
            SimpleTable(taskHeader = taskHeader, rows = taskData) { newItem ->
                taskData.add(newItem)
            }
            //Tạo 1 table task rỗng
            if(AddNewTableTask.value){
                SimpleTable(taskHeader = taskHeader,
                    rows = taskData,
                    onAddItem =
                        { newItem -> taskData.add(newItem) })
            }
        }
        Box(modifier = Modifier
            .align(Alignment.BottomCenter)
        ){
            Button(
                onClick = {AddNewTableTask.value = true}
            ) {
                Text(text = "New Table")
            }
        }
    }

}