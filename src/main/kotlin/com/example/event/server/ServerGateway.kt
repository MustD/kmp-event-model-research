package com.example.event.server

import com.example.event.Event
import com.example.event.Event4Client
import io.ktor.util.logging.KtorSimpleLogger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

object ServerGateway {
    private val logger = KtorSimpleLogger(this::class.java.name)

    private val eventFlow = MutableSharedFlow<Event>()
    val events = eventFlow as SharedFlow<Event>

    val integrationGateway by lazy { ServerIntegration }

    suspend fun send(event: Event) {
        eventFlow.emit(event)
    }

    fun CoroutineScope.handleClientEvents() = launch {
        logger.info("Server. Client event handler launched")

        eventFlow.collect {
            when (it) {
                is Event4Client -> {
                    logger.info("Client event to send: $it")
                    integrationGateway.send(it)
                }
            }
        }
    }
}