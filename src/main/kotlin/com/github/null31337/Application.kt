package com.github.null31337

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.github.null31337.plugins.*

fun main() {
  embeddedServer(Netty, port = 8080, host = "89.208.86.234", module = Application::module)
    .start(wait = true)
}

fun Application.module() {
//  configureSecurity()
  configureSerialization()
  configureTemplating()
  configureRouting()
}
