package com.buiducha.teamtracker.viewmodel

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.buiducha.teamtracker.data.model.project.Posts
import com.buiducha.teamtracker.data.model.project.Workspace
import com.buiducha.teamtracker.data.model.project.WorkspaceMember
import com.buiducha.teamtracker.repository.FirebaseRepository
import com.buiducha.teamtracker.ui.states.CreatePostState
import com.buiducha.teamtracker.ui.states.DetailWorkspaceState
import com.buiducha.teamtracker.ui.states.HomeState
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.SelectedWorkspaceViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.sql.Time
import java.sql.Timestamp
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Timer

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

    fun setSelectedPost(posts: Posts) {
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
        val post = Posts(
            workspaceId = selectedWorkspace.workspace.value.id,
            userId = firebaseRepository.getCurrentUser()?.uid!!,
            content = createPostState.value.content,
            timestamp = System.currentTimeMillis(),
            likesCount = 0
        )
        firebaseRepository.createPost(
            posts = post,
            onCreateSuccess = onCreateSuccess,
            onCreateFailure = onCreateFailure
        )
    }
}