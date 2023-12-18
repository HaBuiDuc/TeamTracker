package com.buiducha.teamtracker.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buiducha.teamtracker.data.model.user.UserData
import com.buiducha.teamtracker.repository.FirebaseRepository
import com.buiducha.teamtracker.ui.states.PostsState
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.SelectedWorkspaceViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class PostViewModel(
    private val selectedWorkspace: SelectedWorkspaceViewModel
) : ViewModel() {
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
            onGetPostsSuccess = { posts ->

                viewModelScope.launch {
                    val userList = mutableListOf<UserData>()
                    posts.forEach { post ->
                        val user = suspendCoroutine { continuation ->
                            firebaseRepository.getUserInfo(
                                userId = post.userId!!,
                                onGetInfoSuccess = { user ->
                                    continuation.resume(user)
                                },
                                onGetInfoFailure = {}
                            )
                        }
                        userList += user
                    }
                    Log.d(TAG, "getPosts: ${userList.size}")
                    _postState.value = _postState.value.copy(
                        postList = posts,
                        userList = userList
                    )
                }

            },
            onGetPostsFailure = {

            }
        )

    }

    companion object {
        const val TAG = "PostViewModel"
    }
}