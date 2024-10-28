package com.example.event.server.user

import com.example.event.model.user.CreateUserEvent
import com.example.event.model.user.ReadUsersEvent
import com.example.event.server.ServerIntegrationGateway
import io.ktor.util.logging.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object UserHandler {
    private val logger = KtorSimpleLogger(this::class.java.name)
    private val integrationGateway by lazy { ServerIntegrationGateway.incomingEvents }

    fun CoroutineScope.handleUserEvents() = launch {
        logger.info("Starting")

        integrationGateway.collect {
            when (it) {
                is CreateUserEvent -> println("Init Users")
                is ReadUsersEvent -> println("Read Users")
            }
        }
    }

}