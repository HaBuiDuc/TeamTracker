package com.buiducha.teamtracker.viewmodel.post_viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.buiducha.teamtracker.data.model.project.WorkspacePost
import com.buiducha.teamtracker.repository.FirebaseRepository
import com.buiducha.teamtracker.repository.StreamRepository
import com.buiducha.teamtracker.ui.states.CreatePostState
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.SelectedWorkspaceViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CreatePostViewModel(
    private val selectedWorkspace: SelectedWorkspaceViewModel
) : ViewModel() {
    private val firebaseRepository = FirebaseRepository.get()
    private val streamRepository = StreamRepository.get()
    private val _createPostState = MutableStateFlow(CreatePostState())
    val createPostState: StateFlow<CreatePostState> = _createPostState.asStateFlow()

    fun setPostContent(content: String) {
        _createPostState.value = _createPostState.value.copy(
            content = content
        )
    }

    fun setPostTitle(title: String) {
        _createPostState.value = _createPostState.value.copy(
            title = title
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createPost(
        onCreateSuccess: () -> Unit,
        onCreateFailure: () -> Unit
    ) {
        Log.d("This is a log", "createPost: ")
        val newPost = WorkspacePost(
            workspaceId = selectedWorkspace.workspace.value.id,
            userId = firebaseRepository.getCurrentUser()?.uid!!,
            title = createPostState.value.title,
            content = createPostState.value.content,
            timestamp = System.currentTimeMillis(),
            likesCount = 0
        )

        firebaseRepository.createPost(
            post = newPost,
            onCreateSuccess = {
                onCreateSuccess()
                // using for update post feature
                firebaseRepository.getWorkspaceMemberId(
                    workspaceId = newPost.workspaceId!!,
                    onGetMemberSuccess = {memberList ->
                        Log.d("This is a log", "get member id success")
                        streamRepository.createTeamChannel(
                            channelId = newPost.id,
                            channelName = newPost.title,
                            memberList = memberList,
                            onCreateSuccess = {}
                        )
                    },
                    onGetMemberFailure = {

                    }
                )

            },
            onCreateFailure = onCreateFailure
        )
    }
}