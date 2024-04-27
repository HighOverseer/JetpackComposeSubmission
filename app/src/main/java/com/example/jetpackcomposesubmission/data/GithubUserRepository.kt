package com.example.jetpackcomposesubmission.data

import com.example.jetpackcomposesubmission.model.User
import com.example.jetpackcomposesubmission.model.UserPreview
import com.example.jetpackcomposesubmission.model.getUserPreviews
import com.example.jetpackcomposesubmission.model.getUsers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GithubUserRepository private constructor() {

    private val users = getUsers().toMutableList()

    private val userFavorites = mutableListOf<User>()

    private val lock = Any()

    fun getAllUserPreviews(): Flow<List<UserPreview>> {
        return flowOf(getUserPreviews())
    }

    fun getUserDetailById(userId: Long): User {
        return users.first { it.userPreview.id == userId }
    }

    fun searchUser(query: String): List<UserPreview> {
        val userPreviews = getUserPreviews()
        return userPreviews.filter { it.name.contains(query, ignoreCase = true) }
    }

    fun changeUserFavorite(userId: Long): Boolean {
        synchronized(lock) {
            val selectedUser = userFavorites.find { it.userPreview.id == userId }
            val isUserFavorite = selectedUser != null
            if (isUserFavorite) {
                selectedUser?.let {
                    removeFromUserFavorites(it)
                }
                return false
            } else {
                addToUserFavorites(userId)
                return true
            }
        }
    }

    private fun addToUserFavorites(userId: Long) {
        val indexOfSelectedUser = users.indexOfFirst { it.userPreview.id == userId }
        val selectedUser = users[indexOfSelectedUser]
        users[indexOfSelectedUser] = selectedUser.copy(isFavorite = true).also { user ->
            userFavorites.add(user)
        }
    }

    private fun removeFromUserFavorites(selectedUser: User) {
        val indexSelectedUser = users.indexOf(selectedUser)
        users[indexSelectedUser] = users[indexSelectedUser].copy(isFavorite = false)

        userFavorites.remove(selectedUser)
    }

    companion object {
        private var INSTANCE: GithubUserRepository? = null

        fun getInstance(): GithubUserRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: GithubUserRepository()
            }.also { INSTANCE = it }
        }
    }
}