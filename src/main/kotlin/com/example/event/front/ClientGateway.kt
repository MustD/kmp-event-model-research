package com.example.event.front

import com.example.event.Event
import io.ktor.util.logging.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

object ClientGateway {
    private val logger = KtorSimpleLogger(this::class.java.name)

    private val channel = MutableSharedFlow<Event>()
    val events = channel as SharedFlow<Event>

    suspend fun send(event: Event) {
        logger.info("Client event: $event")
        channel.emit(event)
    }

}