package com.buiducha.teamtracker.viewmodel

import android.app.Activity
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterViewModel : ViewModel() {
    private var auth: FirebaseAuth = Firebase.auth

    fun createUser(
        activity: Activity,
        email: String,
        password: String,
        onSuccessful: () -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
//                    onSuccessful()
                    val user = auth.currentUser
                    user?.sendEmailVerification()
                    Log.d(TAG, "create user successfully")
                } else if (task.isCanceled) {
                    Log.d(TAG, "create user failure")
                }
            }
            .addOnFailureListener(activity) {e ->
                Log.e(TAG, "Can't create user", e)
            }
    }

    companion object {
        const val TAG = "RegisterViewModel"
    }
}