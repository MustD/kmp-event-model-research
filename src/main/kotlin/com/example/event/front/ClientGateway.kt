package com.example.event.front

import com.example.event.Event
import com.example.event.Event4Server
import io.ktor.util.logging.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

object ClientGateway {
    private val logger = KtorSimpleLogger(this::class.java.name)

    private val eventFlow = MutableSharedFlow<Event>()
    val events = eventFlow as SharedFlow<Event>

    val integrationGateway by lazy { ClientIntegration }

    suspend fun send(event: Event) {
        logger.info("Client event: $event")
        eventFlow.emit(event)
    }

    fun CoroutineScope.handleServerEvents() = launch {
        logger.info("Client. Server event handler launched")

        eventFlow.collect {
            when (it) {
                is Event4Server -> {
                    logger.info("Server event to send: $it")
                    integrationGateway.send(it)
                }
            }
        }
    }



}