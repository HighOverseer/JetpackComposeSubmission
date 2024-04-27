package com.example.jetpackcomposesubmission

class SingleEvent<T>(private val value: T) {

    @Suppress("MemberVisibilityCanBePrivate")
    var hasBeenHandled = false
        private set

    fun getValue(): T? {
        if (hasBeenHandled) return null

        hasBeenHandled = true
        return value
    }
}