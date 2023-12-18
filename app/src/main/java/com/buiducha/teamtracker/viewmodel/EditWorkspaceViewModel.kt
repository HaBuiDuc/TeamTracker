package com.buiducha.teamtracker.viewmodel

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.buiducha.teamtracker.repository.FirebaseRepository
import com.google.firebase.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.storage
import java.util.UUID

class EditWorkspaceViewModel : ViewModel() {
    private val firebaseRepository = FirebaseRepository.get()

    companion object {
        fun uploadImageToStorage(uri: Uri, context: Context, imgUrl: MutableState<String>) {
            val storage = Firebase.storage
            var storageRef = storage.reference
            val uniqueImageName: UUID? = UUID.randomUUID()
            var spaceRef: StorageReference = storageRef.child("images/$uniqueImageName.jpg")

            val byteArray: ByteArray? = context.contentResolver
                .openInputStream(uri)
                ?.use { it.readBytes() }

            byteArray?.let {

                var uploadTask = spaceRef.putBytes(byteArray)
                uploadTask.addOnFailureListener {
                    Toast.makeText(context,"upload failed", Toast.LENGTH_SHORT).show()
                }.addOnSuccessListener {
                    imgUrl.value = it.metadata?.path.toString()
                    Toast.makeText(context, "$imgUrl", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}