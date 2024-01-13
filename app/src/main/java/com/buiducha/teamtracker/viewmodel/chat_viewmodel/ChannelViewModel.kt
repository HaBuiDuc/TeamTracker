package com.buiducha.teamtracker.viewmodel.chat_viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buiducha.teamtracker.repository.FirebaseRepository
import com.buiducha.teamtracker.repository.StreamRepository
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.CurrentUserInfoViewModel
import io.getstream.chat.android.models.User
import kotlinx.coroutines.launch

class ChannelViewModel(
    private val currentUserInfo: CurrentUserInfoViewModel
) : ViewModel() {
    private val firebaseRepository = FirebaseRepository.get()
    private val streamRepository = StreamRepository.get()

    fun clientInitState() =  streamRepository.client.clientState.initializationState

    fun userInit() {
        viewModelScope.launch {
            currentUserInfo.currentUserInfo.collect {
                val user = User(
                    id = firebaseRepository.getCurrentUser()?.uid!!,
                    name = it.fullName
                )
                streamRepository.initUser(
                    user = user
                )
            }
        }
    }
}