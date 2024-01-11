package com.buiducha.teamtracker.viewmodel.auth_viewmodel

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.buiducha.teamtracker.repository.FirebaseRepository

class RegisterViewModel : ViewModel() {
    private val fireBaseRepository = FirebaseRepository.get()

    fun createUser(
        activity: Activity,
        email: String,
        password: String,
        onCreateSuccess: () -> Unit,
        onCreateFailure: () -> Unit
    ) {
        fireBaseRepository.createUser(
            activity = activity,
            email = email,
            password = password,
            onCreateSuccess = onCreateSuccess,
            onCreateFailure = onCreateFailure
        )
    }

    fun isValueValid(
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean = email.isNotEmpty() && password.isNotEmpty() && password == confirmPassword
    companion object {
        const val TAG = "RegisterViewModel"
    }
}