package com.buiducha.teamtracker.viewmodel.shared_viewmodel

import androidx.lifecycle.ViewModel
import com.buiducha.teamtracker.data.model.project.Posts
import com.buiducha.teamtracker.repository.FirebaseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SelectedPostViewModel : ViewModel() {
    private val firebaseRepository = FirebaseRepository.get()
    private val _post = MutableStateFlow(Posts())
    val post: StateFlow<Posts> = _post.asStateFlow()

    fun postUpdate(post: Posts) {
        _post.value = post
    }
}