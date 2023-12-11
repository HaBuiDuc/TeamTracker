package com.buiducha.teamtracker.viewmodel

import androidx.lifecycle.ViewModel
import com.buiducha.teamtracker.repository.FirebaseRepository

class HomeViewModel : ViewModel() {
    private val firebaseRepository = FirebaseRepository.get()

}