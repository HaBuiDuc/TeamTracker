package com.buiducha.teamtracker.viewmodel

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.buiducha.teamtracker.data.model.project.WorkspacePost
import com.buiducha.teamtracker.repository.FirebaseRepository
import com.buiducha.teamtracker.ui.states.CreatePostState
import com.buiducha.teamtracker.ui.states.DetailWorkspaceState
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.SelectedWorkspaceViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PostViewModel(
    private val selectedWorkspace: SelectedWorkspaceViewModel
): ViewModel() {
    private val firebaseRepository = FirebaseRepository.get()
    private val _detailWorkspaceState = MutableStateFlow(DetailWorkspaceState())
    private val _createPostState = MutableStateFlow(CreatePostState())
    val detailWorkspaceState: StateFlow<DetailWorkspaceState> = _detailWorkspaceState.asStateFlow()
    val createPostState: StateFlow<CreatePostState> = _createPostState.asStateFlow()

    init {
        getPosts()
    }

    fun getWorkspaceName() = selectedWorkspace.workspace.value.name

    private fun getPosts() {
        firebaseRepository.getPost(
            workspaceId = selectedWorkspace.workspace.value.id,
            onGetPostsSuccess = {post ->
                _detailWorkspaceState.value = _detailWorkspaceState.value.copy(
                    postsList = post
                )
            },
            onGetPostsFailure = {

            }
        )
    }

    fun setPostContent(content: String) {
        _createPostState.value = _createPostState.value.copy(
            content = content
        )
    }

    fun setSelectedPost(posts: WorkspacePost) {
        _detailWorkspaceState.value = _detailWorkspaceState.value.copy(
            selectedPosts = posts
        )
    }

    @SuppressLint("SuspiciousIndentation")
    @RequiresApi(Build.VERSION_CODES.O)
    fun createPost(
        onCreateSuccess: () -> Unit,
        onCreateFailure: () -> Unit
    ) {
        val post = WorkspacePost(
            workspaceId = selectedWorkspace.workspace.value.id,
            userId = firebaseRepository.getCurrentUser()?.uid!!,
            content = createPostState.value.content,
            timestamp = System.currentTimeMillis(),
            likesCount = 0
        )
        firebaseRepository.createPost(
            post = post,
            onCreateSuccess = onCreateSuccess,
            onCreateFailure = onCreateFailure
        )
    }
}