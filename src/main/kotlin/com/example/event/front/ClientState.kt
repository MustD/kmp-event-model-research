package com.example.event.front

import com.example.event.model.user.User
import kotlinx.coroutines.flow.MutableStateFlow

object ClientState {
    val users = MutableStateFlow<List<User>>(emptyList())
}