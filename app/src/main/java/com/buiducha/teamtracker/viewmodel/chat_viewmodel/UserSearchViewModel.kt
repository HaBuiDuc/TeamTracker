package com.buiducha.teamtracker.viewmodel.chat_viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.buiducha.teamtracker.repository.FirebaseRepository
import com.buiducha.teamtracker.repository.StreamRepository
import com.buiducha.teamtracker.ui.states.UserSearchState
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.CurrentUserInfoViewModel
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.UserInfoViewModel
import com.google.firebase.auth.userProfileChangeRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserSearchViewModel(
    private val userInfo: UserInfoViewModel,
    private val currentUserInfo: CurrentUserInfoViewModel
): ViewModel() {
    private val firebaseRepository = FirebaseRepository.get()
    private val streamRepository = StreamRepository.get()
    private val _userSearchState = MutableStateFlow(UserSearchState())
    val userSearchState: StateFlow<UserSearchState> = _userSearchState.asStateFlow()

    fun setQuery(query: String) {
        val newList = if (query.isNotEmpty()) userInfo.userInfo.value.filter { user -> user.email.contains(query) } else emptyList()
        _userSearchState.value = _userSearchState.value.copy(
            resultList = newList,
            query = query
        )
        Log.d(TAG, newList.toString())
    }

    fun createChannel(memberId: String) {
        val memberList = listOf(memberId, currentUserInfo.currentUserInfo.value.id)
        streamRepository.createChannel(
            memberList = memberList
        )
        Log.d(TAG, "createChannel: ")
    }

    companion object {
        const val TAG = "UserSearchViewModel"
    }
}