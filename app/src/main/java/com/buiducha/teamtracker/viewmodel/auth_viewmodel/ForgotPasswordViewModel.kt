package com.buiducha.speedyfood.viewmodel.authentication

import androidx.lifecycle.ViewModel
import com.buiducha.teamtracker.repository.FirebaseRepository

class ForgotPasswordViewModel : ViewModel() {
    private val fireBaseRepository = FirebaseRepository.get()
    fun forgotPassword(
        email: String,
        onSendEmailSuccess: () -> Unit,
        onSendEmailFailure: () -> Unit
    ) {
        fireBaseRepository.forgotPassword(
            email = email,
            onSendEmailSuccess = onSendEmailSuccess,
            onSendEmailFailure = onSendEmailFailure
        )
    }
}