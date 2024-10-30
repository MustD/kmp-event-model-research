package com.example.event.model.common

import com.example.event.Event
import com.example.event.Event4Client
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Serializable
@SerialName("violation")
data class Violation(
    @OptIn(ExperimentalUuidApi::class)
    override val eventId: String = Uuid.random().toString(),

    val message: String,
) : Event(), Event4Client
