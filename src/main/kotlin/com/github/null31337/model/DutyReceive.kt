package com.github.null31337.model

import kotlinx.serialization.Serializable

@Serializable
data class DutyReceive(
  val name: String,
  val description: String,
  val deadline: String,
  val status: DutyStatus,
)


