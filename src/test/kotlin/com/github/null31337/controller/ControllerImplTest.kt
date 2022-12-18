package com.github.null31337.controller

import com.github.null31337.model.Duty
import com.github.null31337.model.DutyReceive
import com.github.null31337.model.DutyStatus.DONE
import com.github.null31337.service.DutyService
import com.github.null31337.service.UserService
import io.ktor.server.application.*
import io.ktor.server.request.*
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class ControllerImplTest {
  private val dutyService= mock<DutyService>()
  private val userService = mock<UserService>()
  private val controller = ControllerImpl(dutyService, userService)

  @Test
  fun `controller generate should generate secret code`() {
    whenever(userService.generate(any())).thenReturn("secret")

    val secret = controller.generate(1)

    assertThat(secret).isEqualTo("secret")

    verify(userService).generate(1)
  }

  @Test
  fun `controller login should return user id`(): Unit = runBlocking {
    whenever(userService.login(any())).thenReturn(1)

    val call = mock<ApplicationCall>()


    whenever(call.receive<String>()).thenReturn("secret")

    val id = controller.login(call)

    assertThat(id).isEqualTo(1)

    verify(call).receive<String>()
    verify(userService).login("secret")
  }

  @Test
  fun `controller get should return all duties`(): Unit = runBlocking {
    whenever(dutyService.all(any())).thenReturn(listOf(Duty(1, 1, "name", "description", "deadline", DONE)))

    val duties = controller.get(1)

    assertThat(duties).hasSameElementsAs(listOf(Duty(1, 1, "name", "description", "deadline", DONE)))

    verify(dutyService).all(1)
  }

  @Test
  fun `controller add should return duty id`(): Unit = runBlocking {
    whenever(dutyService.add(any(), any())).thenReturn(1)

    val call = mock<ApplicationCall>()

    whenever(call.receive<DutyReceive>()).thenReturn(DutyReceive("name", "description", "deadline", DONE))

    val id = controller.add(1, call)

    assertThat(id).isEqualTo(1)

    verify(dutyService).add(1, DutyReceive("name", "description", "deadline", DONE))
    verify(call).receive<DutyReceive>()
  }
}