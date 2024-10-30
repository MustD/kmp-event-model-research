package com.example

import com.example.event.model.user.User
import com.example.event.server.ServerState
import com.example.event.ui_api.UserApi
import io.ktor.server.testing.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withTimeout
import kotlin.test.Test
import kotlin.time.Duration.Companion.seconds
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


@OptIn(ExperimentalUuidApi::class)
class ApplicationKtTest {

    @Test
    fun test() = testApplication {
        application {
            module()
        }
        startApplication()
        delay(1.seconds)

        val givenUser = User(Uuid.random().toString())

        UserApi.createUser(givenUser)

        withTimeout(1.seconds) {
            ServerState.users.filter { it.contains(givenUser.id) }.first()
        }
    }
}

