package com.github.null31337

import com.github.null31337.controller.ControllerImpl
import com.github.null31337.repository.DutyStorage
import com.github.null31337.repository.DutyStorageDB
import com.github.null31337.service.DutyService
import com.github.null31337.service.DutyServiceImpl
import com.github.null31337.service.UserService
import com.github.null31337.service.UserServiceImpl
import org.koin.dsl.module
import java.sql.Connection
import java.sql.DriverManager

val ProdModule = module {
  val jdbcUrl = "jdbc:postgresql://localhost:5432/postgres"

  single<Connection> { DriverManager.getConnection(jdbcUrl, "postgres", "postgres") }
  single<DutyStorage> { DutyStorageDB(get()) }
  single<DutyService> { DutyServiceImpl(get()) }
  single<UserService> { UserServiceImpl() }
  single { ControllerImpl(get(), get()) }
}