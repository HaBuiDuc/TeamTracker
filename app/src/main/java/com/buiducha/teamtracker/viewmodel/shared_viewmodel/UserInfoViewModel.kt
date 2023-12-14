package com.buiducha.teamtracker.viewmodel.shared_viewmodel

import androidx.lifecycle.ViewModel
import com.buiducha.teamtracker.data.model.user.UserData
import com.buiducha.teamtracker.repository.FirebaseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserInfoViewModel : ViewModel() {
    private val firebaseRepository = FirebaseRepository.get()
    private val _userInfo = MutableStateFlow<List<UserData>>(emptyList())
    val userInfo: StateFlow<List<UserData>> = _userInfo.asStateFlow()

    init {
        firebaseRepository.getUserInfoList(
            onGetInfoSuccess = {userList ->
                _userInfo.value = userList
            },
            onGetInfoFailure = {}
        )
    }
}