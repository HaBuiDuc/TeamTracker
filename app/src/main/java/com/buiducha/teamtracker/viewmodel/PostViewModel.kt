package com.buiducha.teamtracker.viewmodel

import androidx.lifecycle.ViewModel
import com.buiducha.teamtracker.repository.FirebaseRepository
import com.buiducha.teamtracker.ui.states.PostsState
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.SelectedWorkspaceViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PostViewModel(
    private val selectedWorkspace: SelectedWorkspaceViewModel
): ViewModel() {
    private val firebaseRepository = FirebaseRepository.get()
    private val _postState = MutableStateFlow(PostsState())
    val postsState: StateFlow<PostsState> = _postState.asStateFlow()

    init {
        getPosts()
    }

    fun getWorkspaceName() = selectedWorkspace.workspace.value.name

    private fun getPosts() {
        firebaseRepository.getPosts(
            workspaceId = selectedWorkspace.workspace.value.id,
            onGetPostsSuccess = {posts ->
                _postState.value = _postState.value.copy(
                    postList = posts
                )
            },
            onGetPostsFailure = {

            }
        )
    }

}