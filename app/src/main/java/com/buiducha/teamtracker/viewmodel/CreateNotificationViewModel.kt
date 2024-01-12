package com.buiducha.teamtracker.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.buiducha.teamtracker.data.model.Notification
import com.buiducha.teamtracker.repository.FirebaseRepository
import com.buiducha.teamtracker.ui.states.CreateNotificationState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class CreateNotificationViewModel: ViewModel() {
    private val firebaseRepository = FirebaseRepository.get()
    private val _createNotificationState = MutableStateFlow(CreateNotificationState())
    val createNotificationState: StateFlow<CreateNotificationState> = _createNotificationState.asStateFlow()


    fun setReceiverId(receiverId: String){
        _createNotificationState.value = _createNotificationState.value.copy(
            receiverId = receiverId
        )
    }


    fun setSenderId(sender: String){
        _createNotificationState.value = _createNotificationState.value.copy(
            sender = sender
        )
    }


    fun setContent(content: String){
        _createNotificationState.value = _createNotificationState.value.copy(
            content = content
        )
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun setTime(){
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val current = LocalDateTime.now().format(formatter)
        _createNotificationState.value = _createNotificationState.value.copy(
            time = current
        )
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun createNotification(){
        setTime()
        val newNotification = Notification(
            receiverId = createNotificationState.value.receiverId,
            sender = createNotificationState.value.sender,
            content = createNotificationState.value.content,
            time = createNotificationState.value.time,
            isRead = false
        )
        firebaseRepository.createNotification(notification = newNotification)
    }
}
