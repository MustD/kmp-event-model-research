package com.example.event.server

import com.example.Configuration.json
import com.example.event.Event
import com.example.event.front.ClientIntegration
import io.ktor.util.logging.*
import kotlinx.serialization.encodeToString

object ServerIntegration {
    private val logger = KtorSimpleLogger(this::class.java.name)

    private val gateway by lazy { ServerGateway }

    suspend fun send(event: Event) {
        logger.info("API server - send event: $event")
        runCatching {
            json.encodeToString(event)
        }.onFailure {
            logger.error(it)
        }.map {
            ClientIntegration.receive(it)
        }
    }

    suspend fun receive(event: String) {
        logger.info("API server - received event: $event") //not called
        gateway.send(json.decodeFromString(event))
    }
}