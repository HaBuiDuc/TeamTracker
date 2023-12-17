package com.buiducha.teamtracker.viewmodel

import android.app.Activity
import android.util.Log
import androidx.lifecycle.ViewModel
import com.buiducha.teamtracker.repository.FirebaseRepository

class LoginViewModel : ViewModel() {
    private val firebaseRepository = FirebaseRepository.get()

    fun userLogin(
        activity: Activity,
        email: String,
        password: String,
        onLoginSuccess: () -> Unit,
        onLoginFailure: (String) -> Unit
    ) {
        firebaseRepository.userLogin(
            activity = activity,
            email = email,
            password = password,
            onLoginSuccess = onLoginSuccess,
            onLoginFailure = onLoginFailure
        )
    }

    fun onLoginSuccess(
        onUserExists: () -> Unit,
        onUserNotExists: () -> Unit
    ) {
        Log.d(TAG, "onLoginSuccess: ")
        firebaseRepository.isUserInfoExists(
            userId = firebaseRepository.getCurrentUser()?.uid!!,
            onUserExists = onUserExists,
            onUserNotExists = onUserNotExists
        )
    }

    fun isValueValid(
        email: String,
        password: String,
    ): Boolean = email.isNotEmpty() && password.isNotEmpty()

    companion object {
        const val TAG = "LoginViewModel"
    }
}