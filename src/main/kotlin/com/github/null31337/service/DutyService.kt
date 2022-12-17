package com.github.null31337.service

import com.github.null31337.model.Duty
import com.github.null31337.model.DutyReceive

interface DutyService {
  suspend fun all(id: Long): Collection<Duty>
  suspend fun add(id: Long, duty: DutyReceive): Long
}