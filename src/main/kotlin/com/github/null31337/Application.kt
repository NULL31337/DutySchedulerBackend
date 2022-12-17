package com.github.null31337

import com.github.null31337.plugins.configureRouting
import com.github.null31337.plugins.configureSerialization
import com.github.null31337.plugins.configureTemplating
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.core.context.GlobalContext.startKoin

fun main() {
  embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
    .start(wait = true)

}
fun Application.module() {
  startKoin {
    modules(ProdModule)
  }
//  configureSecurity()
  configureSerialization()
  configureTemplating()
  configureRouting()
}
