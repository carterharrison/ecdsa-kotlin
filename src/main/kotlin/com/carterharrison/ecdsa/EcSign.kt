package com.carterharrison.ecdsa

import com.carterharrison.ecdsa.hash.EcHasher
import java.math.BigInteger
import java.security.SecureRandom

object EcSign {

    /**
     * Signs a message given a key pair, data, and a hashing algorithm to hash the message before signing it.
     *
     * @param keyPair The keypair to sign the message with
     * @param data tThe data to sign
     * @param hasher The hasher to hash the data with before signing
     * @return The signer of the data, keypair, and hasher
     */
    fun signData (keyPair: EcKeyPair, data : ByteArray, hasher : EcHasher) : EcSignature {
        val randomK = BigInteger(256, SecureRandom())
        val hash = BigInteger(hasher.hash(data))
        val g = keyPair.publicKey.curve.g
        val n = keyPair.publicKey.curve.n
        val k = randomK % n
        val p1 = g * k
        val r = p1.x

        if (r == EcConstants.ZERO) {
            signData(keyPair, data, hasher)
        }
        val s = (k.modInverse(n)) * (hash + (keyPair.privateKey * r) % n) % n

        if (s == EcConstants.ZERO) {
            signData(keyPair, data, hasher)
        }

        return EcSignature(r, s)
    }
}