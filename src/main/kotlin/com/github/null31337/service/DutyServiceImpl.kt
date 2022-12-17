package com.github.null31337.service

import com.github.null31337.model.Duty
import com.github.null31337.model.DutyReceive
import com.github.null31337.repository.DutyStorage
import kotlinx.coroutines.runBlocking

class DutyServiceImpl(private val dutyStorage: DutyStorage) : DutyService {
  override suspend fun all(id: Long): Set<Duty> = runBlocking {
      dutyStorage.all(id).toMutableSet()
  }

  override suspend fun add(id: Long, duty: DutyReceive): Long {
    dutyStorage.add(id, duty)
    return id
  }
}