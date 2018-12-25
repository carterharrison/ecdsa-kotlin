package com.carterharrison.ecdsa

import java.math.BigInteger

object EcSign {

    fun signData (keyPair: EcKeyPair, data : ByteArray) : EcSignature {
        val n =  keyPair.publicKey.curve.n
        val dN = BigInteger(data)

        val randomK = 0.toBigInteger()
        val secretMultiplier = 0.toBigInteger()

        val k = randomK % n
        val r = ((keyPair.publicKey.curve.g * k).x) % n
        val s = k.modInverse(n) * (dN + (secretMultiplier * r) % n) % n

        return EcSignature(r, s)
    }
}