package com.github.null31337.controller

import com.github.null31337.model.Duty
import io.ktor.server.application.*

interface Controller {
  fun generate(userId: Long): String
  fun login(call: ApplicationCall): Long?
  fun get(userId: Long): Collection<Duty>
  fun add(userId: Long, call: ApplicationCall): Long
}
