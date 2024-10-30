package com.example.event.front.user

import com.example.event.front.ClientGateway
import com.example.event.front.ClientIntegration
import com.example.event.model.user.ReadUsersEvent
import com.example.event.model.user.create.CreateUserIntention
import io.ktor.util.logging.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object UserEventHandler {
    private val logger = KtorSimpleLogger(this::class.java.name)

    private val clientGateway by lazy { ClientGateway }
    private val integrationGateway by lazy { ClientIntegration }

    fun CoroutineScope.handleUserEvents() = launch {
        logger.info("Starting")

        clientGateway.events.collect {
            when (it) {
                is CreateUserIntention, is ReadUsersEvent -> {
                    logger.info("User event handler: $it")
                    integrationGateway.send(it)
                }
            }
        }
    }

}