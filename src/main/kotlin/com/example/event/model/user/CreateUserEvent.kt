package com.example.event.model.user

import com.example.event.Event

data class CreateUserEvent(
    val payload: User,
) : Event
