package com.example

import com.example.event.front.ClientState
import com.example.event.model.user.User
import com.example.event.server.ServerState
import com.example.event.ui_api.UserApi
import io.ktor.server.testing.*
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
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

        runTest(timeout = 5.seconds) {
            backgroundScope.launch { ClientState.users.collect { println(it) } }
            backgroundScope.launch { ServerState.users.collect { println(it) } }

            val apiResult = async {
                UserApi.state.filter { users -> users.find { it.id == givenUser.id } != null }.first()
            }
            val serverResult = async {
                ServerState.users.filter { users -> users.contains(givenUser.id) }.first()
            }
            UserApi.createUser(givenUser)
            awaitAll(apiResult, serverResult)
        }

    }
}

