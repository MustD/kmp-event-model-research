package com.example.event.model.user.create

import com.example.event.Event
import com.example.event.Event4Server
import com.example.event.model.user.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Serializable
@SerialName("user.create.intention")
data class CreateUserIntention(
    @OptIn(ExperimentalUuidApi::class)
    override val eventId: String = Uuid.random().toString(),
    val payload: User,
) : Event(), Event4Server
