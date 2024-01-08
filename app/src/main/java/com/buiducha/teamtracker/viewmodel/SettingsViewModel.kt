package com.buiducha.teamtracker.viewmodel

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.buiducha.teamtracker.repository.FirebaseRepository

class SettingsViewModel: ViewModel() {
    private val firebaseRepository = FirebaseRepository.get()

    fun uploadImage(uri: Uri, context: Context, imgUrl: MutableState<String>, oldImageUrl: String) {
        var oldImage: String = ""
        if (oldImageUrl.length > 125){
            oldImage = oldImageUrl.substring(85, 125)
        }

        firebaseRepository.uploadImageToStorage(uri, context, imgUrl, oldImage)
//        firebaseRepository.updateUserAvatar(imgUrl)
    }

    fun updateUserAvatar(imgUrl: MutableState<String>){
        firebaseRepository.updateUserAvatar(imgUrl)
    }
}