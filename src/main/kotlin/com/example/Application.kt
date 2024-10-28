package com.example

import com.example.event.front.ClientEventGateway
import com.example.event.front.ClientIntegrationGateway
import com.example.event.front.user.UserEventHandler
import com.example.event.server.ServerIntegrationGateway
import com.example.event.server.user.UserHandler
import com.example.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
//    configureSecurity()
//    configureSerialization()
//    configureSockets()
//    configureRouting()

    with(UserEventHandler) { handleUserEvents() }
    with(UserHandler) { handleUserEvents() }

}
