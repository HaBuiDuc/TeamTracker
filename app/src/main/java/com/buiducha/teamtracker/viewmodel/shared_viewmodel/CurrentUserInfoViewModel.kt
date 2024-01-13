package com.buiducha.teamtracker.viewmodel.shared_viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.buiducha.teamtracker.data.model.user.UserData
import com.buiducha.teamtracker.repository.FirebaseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CurrentUserInfoViewModel : ViewModel() {
    private val firebaseRepository = FirebaseRepository.get()
    private val _currentUserInfo = MutableStateFlow(UserData())
    val currentUserInfo: StateFlow<UserData> = _currentUserInfo.asStateFlow()

    init {
        firebaseRepository.getUserInfo(
            userId = firebaseRepository.getCurrentUser()?.uid!!,
            onGetInfoSuccess = {userData ->
                Log.d(TAG, userData.toString())
                _currentUserInfo.value = userData
            },
            onGetInfoFailure = {
                Log.d(TAG, "get user info failure: ")
            }
        )
    }

    fun updateUserInfo() {
        firebaseRepository.getUserInfo(
            userId = firebaseRepository.getCurrentUser()?.uid!!,
            onGetInfoSuccess = {userData ->
                Log.d(TAG, userData.toString())
                _currentUserInfo.value = userData
            },
            onGetInfoFailure = {
                Log.d(TAG, "get user info failure: ")
            }
        )
    }

    fun SetUserNotiToken(token: String){
        _currentUserInfo.value = _currentUserInfo.value.copy(
            notiToken = token
        )
        //
    }

    companion object {
        const val TAG = "CurrentUserInfoViewModel"
    }
}