package com.example.jetpackcomposesubmission.di

import com.example.jetpackcomposesubmission.data.GithubUserRepository

object Injection {
    fun provideRepository(): GithubUserRepository {
        return GithubUserRepository.getInstance()
    }
}