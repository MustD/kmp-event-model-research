package com.example.event.front.user

import com.example.event.front.ClientEventGateway
import com.example.event.front.ClientIntegrationGateway
import com.example.event.model.user.CreateUserEvent
import com.example.event.model.user.ReadUsersEvent
import io.ktor.util.logging.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object UserEventHandler {
    private val logger = KtorSimpleLogger(this::class.java.name)

    private val client by lazy { ClientEventGateway }
    private val integration by lazy { ClientIntegrationGateway }

    fun CoroutineScope.handleUserEvents() = launch {
        logger.info("Starting")

        client.events.collect {
            logger.info(it.toString())
            when (it) {
                is CreateUserEvent, is ReadUsersEvent -> integration.send(it)
            }
        }
    }

}