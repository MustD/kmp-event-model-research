package com.example.event.model.user

import com.example.event.Event

data class ReadUsersEvent(
    val from: Long = 0,
    val to: Long = 100,
) : Event
