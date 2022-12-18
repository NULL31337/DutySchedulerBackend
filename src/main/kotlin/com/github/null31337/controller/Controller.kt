package com.github.null31337.controller

import com.github.null31337.model.Duty
import com.github.null31337.model.SecretCode
import io.ktor.server.application.*

interface Controller {
  fun generate(userId: Long): SecretCode
  fun login(call: ApplicationCall): Long?
  fun get(userId: Long): Collection<Duty>
  fun add(userId: Long, call: ApplicationCall): Long
}
