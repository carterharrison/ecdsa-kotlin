package com.carterharrison.ecdsa.hash

import java.security.MessageDigest



object EcSha256 : EcHasher {
    override fun hash(data: ByteArray): ByteArray {
        return MessageDigest.getInstance("SHA-256").digest(data)
    }
}