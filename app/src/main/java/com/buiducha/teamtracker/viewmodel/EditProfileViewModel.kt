package com.buiducha.teamtracker.viewmodel

import androidx.lifecycle.ViewModel
import com.buiducha.teamtracker.ui.states.EditProfileState
import com.buiducha.teamtracker.repository.FirebaseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class EditProfileViewModel : ViewModel() {
    private val fireBaseRepository = FirebaseRepository.get()
    private val _editProfileState = MutableStateFlow(EditProfileState())
    val editProfileState: StateFlow<EditProfileState> = _editProfileState.asStateFlow()

    init {
       getUserInfo()
    }

    fun updateInfo() {
        fireBaseRepository.updateUserInfo(
            _editProfileState.value.userData
        )
    }

    fun setGender(gender: Boolean) {
        val userInfo = _editProfileState.value.userData.copy(
            gender = gender
        )
        _editProfileState.value = _editProfileState.value.copy(
            userData = userInfo
        )
    }

    fun setCompany(company: String) {
        val userInfo = _editProfileState.value.userData.copy(
            company = company
        )
        _editProfileState.value = _editProfileState.value.copy(
            userData = userInfo
        )
    }

    fun setLocation(location: String) {
        val userInfo = _editProfileState.value.userData.copy(
            location = location
        )
        _editProfileState.value = _editProfileState.value.copy(
            userData = userInfo
        )
    }

    fun setPhoneNumber(phoneNumber: String) {
        val userInfo = _editProfileState.value.userData.copy(
            phoneNumber = phoneNumber
        )
        _editProfileState.value = _editProfileState.value.copy(
            userData = userInfo
        )
    }

    fun setDateOfBirth(dob: Long) {
        val userInfo = _editProfileState.value.userData.copy(
            dateOfBirth = dob
        )
        _editProfileState.value = _editProfileState.value.copy(
            userData = userInfo
        )
    }

    fun setUserName(userName: String) {
        val userInfo = _editProfileState.value.userData.copy(
            fullName = userName
        )
        _editProfileState.value = _editProfileState.value.copy(
            userData = userInfo
        )
    }

    private fun getUserInfo() {
        fireBaseRepository.getUserInfo(
            userId = fireBaseRepository.getCurrentUser()?.uid!!,
            onGetInfoSuccess = {
                _editProfileState.value = _editProfileState.value.copy(
                    userData = it
                )
            },
            onGetInfoFailure = {

            }
        )
    }
}