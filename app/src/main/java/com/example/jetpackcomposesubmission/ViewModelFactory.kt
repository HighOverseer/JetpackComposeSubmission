package com.example.jetpackcomposesubmission

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackcomposesubmission.data.GithubUserRepository
import com.example.jetpackcomposesubmission.ui.screen.detail.DetailViewModel
import com.example.jetpackcomposesubmission.ui.screen.home.HomeViewModel

class ViewModelFactory(
    private val repository: GithubUserRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}