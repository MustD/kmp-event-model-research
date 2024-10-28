package com.example

import com.example.event.front.ClientIntegrationGateway
import com.example.event.front.user.UserEventHandler
import com.example.event.model.user.User
import com.example.event.server.ServerIntegrationGateway
import com.example.event.ui_api.UserApi
import io.ktor.server.testing.*
import kotlinx.coroutines.delay
import java.util.UUID
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
        UserApi.createUser(
            User(Uuid.random().toString())
        )
    }
}

