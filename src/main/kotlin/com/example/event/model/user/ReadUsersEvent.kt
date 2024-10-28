package com.example.event.model.user

import com.example.event.Event
import kotlinx.serialization.Serializable

@Serializable
data class ReadUsersEvent(
    val from: Long = 0,
    val to: Long = 100,
) : Event()
