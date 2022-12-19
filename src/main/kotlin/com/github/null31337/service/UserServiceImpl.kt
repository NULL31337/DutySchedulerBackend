package com.github.null31337.service

import com.google.common.cache.Cache
import com.google.common.cache.CacheBuilder
import java.util.*
import java.util.concurrent.TimeUnit

class UserServiceImpl() : UserService {
  private val cache: Cache<String, Long> =
    CacheBuilder.newBuilder().expireAfterWrite(30, TimeUnit.SECONDS).build()

  override fun login(secretCode: String): Long? {
    if (secretCode.length != 7) {
      return null
    }
    return cache.getIfPresent(secretCode.substring(1, 6))
  }
  override fun generate(id: Long) = random.nextString().also { cache.put(it, id) }

  private val random = RandomString()

  private inner class RandomString {
    private val random = Random()
    fun nextString(): String {
      val sb = StringBuilder()
      for (i in 0..4) {
        sb.append('A' + random.nextInt(26))
      }
      return sb.toString()
    }
  }
}

