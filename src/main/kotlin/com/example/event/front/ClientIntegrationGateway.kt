package com.example.event.front

import com.example.Configuration.json
import com.example.event.Event
import com.example.event.server.ServerIntegrationGateway
import io.ktor.util.logging.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object ClientIntegrationGateway {
    private val logger = KtorSimpleLogger(this::class.java.name)

    private val channel = MutableSharedFlow<Event>()
    val incomingEvents = channel as SharedFlow<Event>

    suspend fun send(event: Event) {
        runCatching {
            json.encodeToString(event)
        }.onFailure {
            logger.error(it)
        }.map {
            logger.info("API client - send event: $it")
            ServerIntegrationGateway.receive(it)
        }
    }

    suspend fun receive(event: String) {
        logger.info("API client - receive event: $event")

        channel.emit(Json.decodeFromString<Event>(event))
    }

}