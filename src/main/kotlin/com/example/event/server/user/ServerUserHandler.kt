package com.example.event.server.user

import com.example.event.Event
import com.example.event.model.common.Violation
import com.example.event.model.user.ReadUsersEvent
import com.example.event.model.user.create.CreateUserIntention
import com.example.event.model.user.create.CreateUserResult
import com.example.event.server.ServerGateway
import com.example.event.server.ServerState
import io.ktor.util.logging.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object ServerUserHandler {
    private val logger = KtorSimpleLogger(this::class.java.name)

    private val storage by lazy { ServerState.users }
    private val gateway by lazy { ServerGateway }

    fun CoroutineScope.handleUserEvents() = launch {
        logger.info("Server. User event handler launched")

        gateway.events.collect {
            when (it) {
                is CreateUserIntention -> saveUser(it).let { gateway.send(it) }
                is ReadUsersEvent -> println("Read Users")
            }
        }
    }

    private suspend fun saveUser(event: CreateUserIntention): Event = runCatching {
        val currentUsers = readUsers()
        currentUsers.plus(event.payload.id to event.payload).let { storage.emit(it) }
        event.payload
    }.map {
        logger.info("User saved")
        CreateUserResult(eventId = event.eventId, payload = it)
    }.getOrElse {
        Violation(eventId = event.eventId, message = it.message.orEmpty())
    }

    private fun readUsers() = storage.value

}