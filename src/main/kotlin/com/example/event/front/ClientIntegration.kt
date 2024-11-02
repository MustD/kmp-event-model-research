package com.example.event.front

import com.example.Configuration.json
import com.example.event.Event
import com.example.event.server.ServerIntegration
import io.ktor.util.logging.*
import kotlinx.serialization.encodeToString

object ClientIntegration {
    private val logger = KtorSimpleLogger(this::class.java.name)

    private val gateway by lazy { ClientGateway }

    suspend fun send(event: Event) {
        runCatching {
            json.encodeToString(event)
        }.onFailure {
            logger.error(it)
        }.map {
            logger.info("API client - send event: $it")
            ServerIntegration.receive(it)
        }
    }

    suspend fun receive(event: String) {
        logger.info("API client - receive event: $event")
        gateway.send(json.decodeFromString<Event>(event))
    }

}