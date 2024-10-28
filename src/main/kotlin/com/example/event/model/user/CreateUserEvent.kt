package com.example.event.model.user

import com.example.event.Event
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("user.create")
data class CreateUserEvent(
    val payload: User,
) : Event()
