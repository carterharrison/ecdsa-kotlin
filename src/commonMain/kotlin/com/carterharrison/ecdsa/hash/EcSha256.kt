package com.carterharrison.ecdsa.hash

import org.kotlincrypto.hash.sha2.SHA256

object EcSha256 : EcHasher {
  override fun hash(data: ByteArray): ByteArray {
    return SHA256().digest(data)
  }
}