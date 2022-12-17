package com.github.null31337.controller

import com.github.null31337.model.Duty
import com.github.null31337.model.DutyReceive
import com.github.null31337.service.DutyService
import com.github.null31337.service.UserService
import io.ktor.server.application.*
import io.ktor.server.request.*
import kotlinx.coroutines.runBlocking

class ControllerImpl(
  private val dutyService: DutyService,
  private val userService: UserService
) : Controller {
  override fun generate(userId: Long): String {
    return userService.generate(userId)
  }

  override fun login(call: ApplicationCall): Long? = runBlocking {
    val secretKey = call.receive<String>()

    userService.login(secretKey)
  }

  override fun get(userId: Long): Collection<Duty> = runBlocking {
    dutyService.all(userId)
  }


  override fun add(userId: Long, call: ApplicationCall): Long = runBlocking {
    var id: Long
    call.receive<DutyReceive>().let {
      id = dutyService.add(userId, it)
    }
    id
  }
}