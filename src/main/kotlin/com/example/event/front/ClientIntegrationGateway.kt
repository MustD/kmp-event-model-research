package com.example.event.front

import com.example.event.Event
import com.example.event.server.ServerIntegrationGateway
import io.ktor.util.logging.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object ClientIntegrationGateway {
    private val logger = KtorSimpleLogger(this::class.java.name)

    private val channel = MutableSharedFlow<Event>()
    val incomingEvents = channel as SharedFlow<Event>

    suspend fun send(event: Event) {
        logger.info("API client - send event: $event")

        ServerIntegrationGateway.receive(Json.encodeToString(event))
    }

    suspend fun receive(event: String) {
        logger.info("API client - receive event: $event")

        channel.emit(Json.decodeFromString<Event>(event))
    }

}