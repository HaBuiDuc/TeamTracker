package com.buiducha.teamtracker.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.buiducha.teamtracker.repository.FirebaseRepository
import com.buiducha.teamtracker.ui.states.AddMemberState
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.UserInfoViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter

class AddMemberViewModel(
    private val userInfoViewModel: UserInfoViewModel
) : ViewModel() {
    private val firebaseRepository = FirebaseRepository.get()
    private val _addMemberState = MutableStateFlow(AddMemberState())
    val addMemberState: StateFlow<AddMemberState> = _addMemberState.asStateFlow()

    init {
        Log.d(TAG, userInfoViewModel.userInfo.value.toString())
    }

    fun setQuery(query: String) {
        val newList = if (query.isEmpty()) {
            emptyList()
        } else {
            userInfoViewModel.userInfo.value.filter { user -> user.email.startsWith(query) }
        }
        Log.d(TAG, newList.size.toString())
        _addMemberState.value = _addMemberState.value.copy(
            query = query,
            resultList = newList
        )
    }
    companion object {
        const val TAG = "AddMemberViewModel"
    }
}