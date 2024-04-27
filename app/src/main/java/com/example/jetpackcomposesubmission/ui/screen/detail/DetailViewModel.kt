package com.example.jetpackcomposesubmission.ui.screen.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposesubmission.SingleEvent
import com.example.jetpackcomposesubmission.data.GithubUserRepository
import com.example.jetpackcomposesubmission.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: GithubUserRepository
) : ViewModel() {

    private val _selectedUserDetail = MutableStateFlow<User?>(null)
    val selectedUserDetail: StateFlow<User?>
        get() = _selectedUserDetail

    private val _toastMessage = mutableStateOf<SingleEvent<String?>>(SingleEvent(null))
    val toastMessage: State<SingleEvent<String?>> get() = _toastMessage

    fun getUserDetailById(userId: Long) {
        _selectedUserDetail.value = repository.getUserDetailById(userId)
    }

    fun changeUserFavoriteStatus(userId: Long) {
        viewModelScope.launch {
            val newFavoriteStatus = repository.changeUserFavorite(userId)
            getUserDetailById(userId)
            val message = if (newFavoriteStatus) {
                "User Added To Favorite"
            } else "User Removed From Favorite"
            _toastMessage.value = SingleEvent(message)
        }

    }
}