package com.github.null31337.service

interface UserService {
  fun login(secretCode: String): Long?
  fun generate(id: Long): String
}