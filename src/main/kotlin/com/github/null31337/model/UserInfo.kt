package com.github.null31337.model

import java.security.PrivateKey
import java.security.PublicKey

data class UserInfo (
  val id: Long,
  val privateKey: PrivateKey,
  val publicKey: PublicKey
)