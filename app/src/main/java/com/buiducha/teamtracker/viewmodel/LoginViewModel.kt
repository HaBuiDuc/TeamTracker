package com.buiducha.teamtracker.viewmodel

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.buiducha.teamtracker.repository.FirebaseRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

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
//        firebaseRepository.isUserInfoExists(
//            userId = firebaseRepository.getCurrentUser()?.uid!!,
//            onUserExists = onUserExists,
//            onUserNotExists = onUserNotExists
//        )
    }

    fun isValueValid(
        email: String,
        password: String,
    ): Boolean = email.isNotEmpty() && password.isNotEmpty()
}