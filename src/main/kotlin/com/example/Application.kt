package com.example

import com.example.event.front.user.UserEventHandler
import com.example.event.server.ServerGateway
import com.example.event.server.ServerIntegration
import com.example.event.server.user.UserHandler
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

    //client
    with(UserEventHandler) { handleUserEvents() }

    //server
    with(ServerIntegration) { listenServerGateway() }
    with(ServerGateway) { listenClientEvents() }

    with(UserHandler) { handleUserEvents() }

}
