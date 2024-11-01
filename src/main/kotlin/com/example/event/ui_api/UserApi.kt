package com.example.event.ui_api

import com.example.event.front.ClientGateway
import com.example.event.front.ClientState
import com.example.event.model.user.ReadUsersEvent
import com.example.event.model.user.User
import com.example.event.model.user.create.CreateUserIntention
import kotlinx.coroutines.flow.StateFlow

object UserApi {
    val state: StateFlow<List<User>> by lazy { ClientState.users }

    private val clientEventGateway by lazy { ClientGateway }

    suspend fun createUser(user: User) {
        clientEventGateway.send(CreateUserIntention(payload = user))
    }

    suspend fun readUserList() {
        clientEventGateway.send(ReadUsersEvent())
    }

}