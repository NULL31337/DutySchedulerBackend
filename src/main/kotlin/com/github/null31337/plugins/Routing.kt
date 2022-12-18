package com.github.null31337.plugins

import com.github.null31337.controller.ControllerImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.java.KoinJavaComponent.inject

fun Application.configureRouting() {
  val controllerImpl: ControllerImpl by inject(ControllerImpl::class.java)

  routing {
    post("/login") {
      controllerImpl.login(call)?.let {
        call.respond(it)
      } ?: call.respondText("Wrong secret key", status = HttpStatusCode.OK)
    }

    route("/{userId}") {
      get("/generate") {
        call.respond(controllerImpl.generate(call.userId()))
      }

      get("") {
        call.respond(controllerImpl.get(call.userId()))
      }

      post("") {
        call.respond(controllerImpl.add(call.userId(), call))
      }
    }
  }
}

private fun ApplicationCall.userId(): Long {
  return parameters["userId"]?.toLong() ?: throw IllegalArgumentException("userId is not specified")
}