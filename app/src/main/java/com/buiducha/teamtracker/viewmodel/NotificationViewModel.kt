package com.buiducha.teamtracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buiducha.teamtracker.repository.FirebaseRepository
import com.buiducha.teamtracker.ui.states.NotificationState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class NotificationViewModel : ViewModel() {
    private val fireBaseRepository = FirebaseRepository.get()
    private val _notificationState = MutableStateFlow(NotificationState())
    val notificationState: StateFlow<NotificationState> = _notificationState.asStateFlow()


    init {
        getNotification()
    }


    private fun getNotification() {
        fireBaseRepository.getNotifications(onGetNotificationSuccess = {
                notifications ->
            viewModelScope.launch {
                _notificationState.value = _notificationState.value.copy(
                    notificationList = notifications
                )
            }
        })
    }
}
