package com.example.event.server

import com.example.event.Event
import com.example.event.front.ClientIntegrationGateway
import com.example.event.front.ClientState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

object ServerIntegrationGateway {
    private val channel = MutableSharedFlow<Event>()
    val events = channel as SharedFlow<Event>

    suspend fun send(event: Event) {
        channel.emit(event)
    }

    fun CoroutineScope.connect() = launch {
        events.collect {
            ClientIntegrationGateway.send(it)
        }
    }
}