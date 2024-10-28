package com.example.event.model.user

import com.example.event.Event
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("user.read")
data class ReadUsersEvent(
    val from: Long = 0,
    val to: Long = 100,
) : Event()
