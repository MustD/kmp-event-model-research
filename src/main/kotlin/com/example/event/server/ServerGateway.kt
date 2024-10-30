package com.example.event.server

import com.example.event.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

object ServerGateway {
    private val eventStream = MutableSharedFlow<Event>()
    private val integration by lazy { ServerIntegration.incomingEvents }

    val events = eventStream as SharedFlow<Event>

    suspend fun send(event: Event) {
        eventStream.emit(event)
    }

    fun CoroutineScope.listenClientEvents() = launch {
        integration.collect { eventStream.emit(it) }
    }
}