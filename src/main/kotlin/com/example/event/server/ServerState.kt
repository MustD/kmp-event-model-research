package com.example.event.server

import com.example.event.model.user.User
import kotlinx.coroutines.flow.MutableStateFlow

object ServerState {
    val users = MutableStateFlow<List<User>>(emptyList())
}