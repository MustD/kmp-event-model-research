package com.example.event.front.user

import com.example.event.front.ClientGateway
import com.example.event.front.ClientState
import com.example.event.model.user.User
import com.example.event.model.user.create.CreateUserIntention
import com.example.event.model.user.create.CreateUserResult
import io.ktor.util.logging.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object ClientUserHandler {
    private val logger = KtorSimpleLogger(this::class.java.name)

    val state by lazy { ClientState.users }
    private val clientGateway by lazy { ClientGateway }

    suspend fun createUser(user: User) {
        clientGateway.send(CreateUserIntention(payload = user))
    }

    fun CoroutineScope.handleUserEvents() = launch {
        logger.info("Client. User event handler launched")

        clientGateway.events.collect {
            when (it) {
                is CreateUserResult -> saveUser(it.payload)
            }
        }
    }

    suspend fun saveUser(user: User) {
        logger.info("User saved")
        state.emit(state.value + user)
    }

}