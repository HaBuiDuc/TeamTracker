package com.buiducha.teamtracker.viewmodel

import androidx.lifecycle.ViewModel
import com.buiducha.teamtracker.repository.FirebaseRepository

class EditWorkspaceViewModel : ViewModel() {
    private val firebaseRepository = FirebaseRepository.get()
}