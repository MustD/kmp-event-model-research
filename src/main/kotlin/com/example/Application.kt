package com.example

import com.example.event.front.ClientGateway
import com.example.event.front.user.ClientUserHandler
import com.example.event.server.ServerGateway
import com.example.event.server.user.ServerUserHandler
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
    with(ClientGateway) { handleServerEvents() }
    with(ClientUserHandler) { handleUserEvents() }

    //server
    with(ServerGateway) { handleClientEvents() }
    with(ServerUserHandler) { handleUserEvents() }

}
