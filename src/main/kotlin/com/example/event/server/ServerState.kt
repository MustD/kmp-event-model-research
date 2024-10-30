package com.example.event.server

import com.example.event.model.user.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
object ServerState {
    val users = MutableStateFlow<List<User>>(
        listOf(
            User(id = Uuid.random().toString())
        )
    )
}