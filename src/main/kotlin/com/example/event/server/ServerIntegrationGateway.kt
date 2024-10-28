package com.example.event.server

import com.example.Configuration.json
import com.example.event.Event
import com.example.event.front.ClientIntegrationGateway
import io.ktor.util.logging.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object ServerIntegrationGateway {
    private val logger = KtorSimpleLogger(this::class.java.name)

    private val channel = MutableSharedFlow<Event>()
    val incomingEvents = channel as SharedFlow<Event>

    suspend fun send(event: Event) {
        logger.info("API server - send event: $event")

        ClientIntegrationGateway.receive(Json.encodeToString { event })
    }

    suspend fun receive(event: String) {
        logger.info("API server - received event: $event") //not called
        channel.emit(json.decodeFromString(event))
    }
}