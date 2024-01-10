package com.buiducha.teamtracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buiducha.teamtracker.repository.FirebaseRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel: ViewModel() {
    private val fireBaseRepository = FirebaseRepository.get()

    init {
        viewModelScope.launch {
        }
    }

    fun onLoginSuccess(
        onUserExists: () -> Unit,
        onUserNotExists: () -> Unit
    ) {
        fireBaseRepository.isUserInfoExists(
            userId = fireBaseRepository.getCurrentUser()?.uid!!,
            onUserExists = onUserExists,
            onUserNotExists = onUserNotExists
        )
    }

    fun checkAuthState(
        onLogged: () -> Unit,
        onNotLogged: () -> Unit
    ) {
        viewModelScope.launch {
//            delay(1000)
            if (fireBaseRepository.getCurrentUser() != null) {
                onLogged()
            } else {
                onNotLogged()
            }
        }

    }
}