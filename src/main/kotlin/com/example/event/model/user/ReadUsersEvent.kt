package com.example.event.model.user

import com.example.event.Event
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Serializable
@SerialName("user.read")
data class ReadUsersEvent(
    @OptIn(ExperimentalUuidApi::class)
    override val eventId: String = Uuid.random().toString(),
    val from: Long = 0,
    val to: Long = 100,
) : Event()