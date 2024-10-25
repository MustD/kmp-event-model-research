package com.example.event.front

import com.example.event.Event
import com.example.event.server.ServerIntegrationGateway
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

object ClientIntegrationGateway {
    private val channel = MutableSharedFlow<Event>()
    val events = channel as SharedFlow<Event>

    suspend fun send(event: Event) {
        channel.emit(event)
    }

    fun CoroutineScope.connect() = launch {
        events.collect {
            ServerIntegrationGateway.send(it)
        }
    }
}