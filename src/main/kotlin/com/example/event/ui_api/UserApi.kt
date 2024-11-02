package com.example.event.ui_api

import com.example.event.front.user.ClientUserHandler
import com.example.event.model.user.User
import kotlinx.coroutines.flow.StateFlow

object UserApi {
    val state: StateFlow<List<User>> by lazy { ClientUserHandler.state }

    suspend fun createUser(user: User) = ClientUserHandler.createUser(user)

}