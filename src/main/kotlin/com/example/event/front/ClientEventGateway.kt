package com.example.event.front

import com.example.event.Event
import com.example.event.server.ServerEventGateway
import io.ktor.util.logging.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

object ClientEventGateway {
    private val logger = KtorSimpleLogger(this::class.java.name)

    private val channel = MutableSharedFlow<Event>()
    val events = channel as SharedFlow<Event>

    suspend fun send(event: Event) {
        logger.info("Event: $event")
        channel.emit(event)
    }

}