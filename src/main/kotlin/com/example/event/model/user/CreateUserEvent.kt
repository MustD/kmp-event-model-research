package com.example.event.model.user

import com.example.event.Event
import kotlinx.serialization.Serializable

@Serializable
data class CreateUserEvent(
    val payload: User,
) : Event()
