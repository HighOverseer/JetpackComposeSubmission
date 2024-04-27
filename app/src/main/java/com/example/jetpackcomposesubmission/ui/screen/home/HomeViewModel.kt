package com.example.jetpackcomposesubmission.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposesubmission.SingleEvent
import com.example.jetpackcomposesubmission.data.GithubUserRepository
import com.example.jetpackcomposesubmission.model.UserPreview
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: GithubUserRepository
) : ViewModel() {
    private val _userPreviews = MutableStateFlow<List<UserPreview>>(emptyList())
    val userPreviews: StateFlow<List<UserPreview>>
        get() = _userPreviews

    private var searchJob: Job? = null
    private var getDataJob: Job? = null

    private val _query = mutableStateOf("")
    val query: State<String> = _query

    private val _toastMessage = mutableStateOf<SingleEvent<String?>>(SingleEvent(null))
    val toastMessage: State<SingleEvent<String?>> get() = _toastMessage

    fun searchUser(query: String) {
        _query.value = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch(Dispatchers.Default) {
            repository.searchUser(query).also {
                if (it.isNotEmpty()) {
                    _userPreviews.value = it
                    return@also
                }
                _toastMessage.value = SingleEvent("Maaf user yang anda cari tidak ditemukan..")
                getAllUserPreviews()
            }
        }
    }

    private fun getAllUserPreviews() {
        getDataJob?.cancel()
        getDataJob = viewModelScope.launch(Dispatchers.Default) {
            repository.getAllUserPreviews()
                .collect {
                    _userPreviews.value = it
                }
        }
    }

    init {
        getAllUserPreviews()
    }

}