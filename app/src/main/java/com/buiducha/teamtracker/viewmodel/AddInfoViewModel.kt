package com.buiducha.teamtracker.viewmodel

import androidx.lifecycle.ViewModel
import com.buiducha.teamtracker.data.model.user.UserData
import com.buiducha.teamtracker.repository.FirebaseRepository
import com.buiducha.teamtracker.ui.states.AddInfoState
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.UserInfoViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AddInfoViewModel : ViewModel() {
    private val fireBaseRepository = FirebaseRepository.get()
    private val _addInfoState = MutableStateFlow(AddInfoState())
    val addInfoState: StateFlow<AddInfoState> get() = _addInfoState

    fun setCompany(company: String) {
        _addInfoState.value = _addInfoState.value.copy(
            company = company
        )
    }

    fun setLocation(location: String) {
        _addInfoState.value = _addInfoState.value.copy(
            location = location
        )
    }

    fun setFullName(fullName: String) {
        _addInfoState.value = _addInfoState.value.copy(
            fullName = fullName
        )
    }

    fun setDateOfBirth(dob: String) {
        _addInfoState.value = _addInfoState.value.copy(
            dateOfBirth = dob
        )
    }

    fun setPhoneNumber(phone: String) {
        _addInfoState.value = _addInfoState.value.copy(
            phoneNumber = phone
        )
    }

    fun setGender(gender: Boolean) {
        _addInfoState.value = _addInfoState.value.copy(
            gender = gender
        )
    }

    fun addUserInfo(
        onAddSuccess: () -> Unit,
        onAddFailure: () -> Unit
    ) {
        val userInfo = UserData(
            id = fireBaseRepository.getCurrentUser()?.uid!!,
            fullName = addInfoState.value.fullName,
            phoneNumber = addInfoState.value.phoneNumber,
            location = addInfoState.value.location,
            company = addInfoState.value.company,
            dateOfBirth = addInfoState.value.dateOfBirth,
            email = fireBaseRepository.getCurrentUser()?.email!!
        )

        fireBaseRepository.addUserInfo(
            userData = userInfo,
            onAddSuccess = onAddSuccess,
            onAddFailure = onAddFailure
        )
    }
}