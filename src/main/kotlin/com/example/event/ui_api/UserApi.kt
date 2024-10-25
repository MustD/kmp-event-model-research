package com.example.event.ui_api

import com.example.event.front.ClientEventGateway
import com.example.event.front.ClientState
import com.example.event.model.user.CreateUserEvent
import com.example.event.model.user.ReadUsersEvent
import com.example.event.model.user.User
import kotlinx.coroutines.flow.StateFlow

object UserApi {
    val state: StateFlow<List<User>> by lazy { ClientState.users }

    private val clientEventGateway by lazy { ClientEventGateway }

    suspend fun createUser(user: User) {
        clientEventGateway.send(CreateUserEvent(user))
    }

    suspend fun readUserList() {
        clientEventGateway.send(ReadUsersEvent())
    }

}