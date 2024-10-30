package com.example.event.server

import com.example.event.Event
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

object ServerGateway {
    private val channel = MutableSharedFlow<Event>()

    val events = channel as SharedFlow<Event>

    suspend fun send(event: Event) {
        channel.emit(event)
    }
}