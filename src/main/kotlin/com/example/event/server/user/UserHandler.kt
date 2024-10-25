package com.example.event.server.user

import com.example.event.model.user.CreateUserEvent
import com.example.event.model.user.ReadUsersEvent
import com.example.event.server.ServerEventGateway
import com.example.event.server.ServerIntegrationGateway
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

object UserHandler {
    private val integration by lazy { ServerIntegrationGateway.events }

    fun CoroutineScope.handleUserEvents() = launch {
        integration.collect {
            when (it) {
                is CreateUserEvent -> println("Init Users")
                is ReadUsersEvent -> println("Read Users")
            }
        }
    }

}