package com.github.null31337.repository

import com.github.null31337.model.Duty
import com.github.null31337.model.DutyReceive

interface DutyStorage {
  suspend fun all(id: Long): Collection<Duty>
  suspend fun add(id: Long, duty: DutyReceive): Long
}